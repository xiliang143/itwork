package com.xiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiliang.constant.GoodsConstant;
import com.xiliang.constant.MessageConstant;
import com.xiliang.constant.OrderPayStatusConstant;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Goods;
import com.xiliang.entity.OrderOut;
import com.xiliang.mapper.OrderOutMapper;
import com.xiliang.mapper.UserInAndOutMapper;
import com.xiliang.result.PageResult;
import com.xiliang.service.OrderOutService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class OrderOutServiceImpl implements OrderOutService {
    @Autowired
    private OrderOutMapper orderOutMapper;



    //订单分页查询
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());
        //查询订单
        Page<OrderOut> page=orderOutMapper.pageQuery(orderPageQueryDTO);
        long total = page.getTotal();
        List<OrderOut> records = page.getResult();
        return new PageResult(total,records);
    }
    //接出库当计算价格并打印报表
    @Transactional(rollbackFor = Exception.class)
    public void exportOutData(HttpServletResponse response, Long id) throws IOException {
        //根据出库订单id获取数据
        OrderOut orderOut = orderOutMapper.getById(id);
        //通过poi工具类，将数据写入excel文件中
        String templatePath = "E:\\Warehouse-management-system\\work-out\\work-server\\src\\main\\resources\\template\\订单出库模板.xlsx";
        FileInputStream template = new FileInputStream(templatePath);
        XSSFWorkbook excel = new XSSFWorkbook(template);
        //获取第一个sheet页
        XSSFSheet sheet = excel.getSheetAt(0);
        //从第四行开始填入数据
        int startRow=3;
        int endRow = sheet.getLastRowNum();
        //获得详细数据
        //用uuid生成一个长度为10的出库订单号
        String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String username = orderOut.getUsername();
        Integer payStatus = orderOut.getPayStatus();
        Integer goodsType = orderOut.getGoodsType();
        Integer goodsNum = orderOut.getGoodsNum();
        LocalDateTime createTime = orderOut.getCreateTime();
        //插入数据
        sheet.getRow(startRow).getCell(0).setCellValue(orderId);
        sheet.getRow(startRow).getCell(1).setCellValue(username);
        sheet.getRow(startRow).getCell(2).setCellValue(payStatus==1?"已支付":"未支付");
        //若果为1为棉花，如果为2为塑料颗粒，如果为3为玻璃纤维，若果为4为化工用品
        sheet.getRow(startRow).getCell(3).setCellValue(goodsType==1?"棉花":goodsType==2?"塑料颗粒":goodsType==3?"玻璃纤维":"化工用品");
        sheet.getRow(startRow).getCell(4).setCellValue(goodsNum);
        sheet.getRow(startRow).getCell(5).setCellValue(String.valueOf(createTime));

        //查询订单在仓库中的存入时间
        LocalDateTime inCreateTime = orderOutMapper.getcreateTime(id);
        //获取总存放天数，不足一天向上取余
        Duration duration = Duration.between(inCreateTime, createTime);
        long totalSeconds = duration.getSeconds();
        long days = (totalSeconds + 86399) / 86400;
        // 将天数转换为int类型，方便后续计算
        int daysInt = (int) days;
        //计算出库费用
        double totalAmount;
        switch (goodsType) {
            case 1:
                totalAmount = daysInt * GoodsConstant.COTTON_DAY_PRICE + goodsNum * GoodsConstant.OUTPRICE;
                break;
            case 2:
                totalAmount = daysInt * GoodsConstant.PLASTIC_DAY_PRICE + goodsNum * GoodsConstant.OUTPRICE;
                break;
            case 3:
                totalAmount = daysInt * GoodsConstant.GLASS_DAY_PRICE + goodsNum * GoodsConstant.OUTPRICE;
                break;
            case 4:
                totalAmount = daysInt * GoodsConstant.CHEMICAL_DAY_PRICE + goodsNum * GoodsConstant.OUTPRICE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + goodsType);
        }
        //插入数据
        sheet.getRow(endRow).getCell(1).setCellValue(totalAmount);
        //通过输出流，将excel下载到客户端浏览器
        ServletOutputStream out = response.getOutputStream();
        excel.write(out);
        //将excel文件保存到本地
        String localDirPath = "E:\\Warehouse-management-system\\出库报表";
        String fileName = "出库报表_" + orderId + "_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMdd-HHmmss")) + ".xlsx";
        String localFilePath = localDirPath + File.separator + fileName;
        FileOutputStream localOut = new FileOutputStream(localFilePath);
        excel.write(localOut);
        //将支付状态设置为已支付
        orderOut.setPayStatus(OrderPayStatusConstant.ALLREADY_PAY);
        orderOutMapper.updateOrder(orderOut.getPayStatus(),id);
        //删除订单表中的数据
        orderOutMapper.deleteOrder(id);
        //关闭流
        localOut.close();
        out.close();
        excel.close();
    }
}
