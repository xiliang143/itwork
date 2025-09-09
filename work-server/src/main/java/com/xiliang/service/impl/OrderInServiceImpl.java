package com.xiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiliang.constant.GoodsConstant;
import com.xiliang.constant.OrderPayStatusConstant;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.mapper.OrderInMapper;
import com.xiliang.result.PageResult;
import com.xiliang.service.OrderInService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class OrderInServiceImpl implements OrderInService {
    @Autowired
    private OrderInMapper orderMapper;



    //订单分页查询
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        //查询订单
        Page<Order> page=orderMapper.pageQuery(orderPageQueryDTO);
        long total = page.getTotal();
        List<Order> records = page.getResult();
        return new PageResult(total,records);

    }
    //根据id删除订单
    public void deleteOrder(long id) {
        orderMapper.deleteOrder(id);
    }
    //根据id查询订单
    public Order getById(long id) {
        //如果id在数据库表中不存在，则抛出异常
        if (orderMapper.getById(id)==null){
            throw new RuntimeException("订单不存在");
        }
        Order order=orderMapper.getById(id);
        return order;

    }
    //打印入库报表
    public void exportInData(HttpServletResponse response,Long id) throws IOException {
        //根据订单id查询数据库，获取数据
        Order order = orderMapper.getById(id);
        //设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String(("订单入库数据_" + order.getOrderId() + ".xlsx").getBytes("UTF-8"), "ISO-8859-1"));
        //通过poi工具类，将数据写入excel文件中
        String templatePath = "E:\\Warehouse-management-system\\work-out\\work-server\\src\\main\\resources\\template\\订单入库模板.xlsx";
        FileInputStream template = new FileInputStream(templatePath);
        XSSFWorkbook excel = new XSSFWorkbook(template);
        //获取第一个sheet页
        XSSFSheet sheet = excel.getSheetAt(0);
        //从第四行开始填入数据
        int startRow=3;
        int endRow = sheet.getLastRowNum();
        //获取详细数据
        String orderId = order.getOrderId();
        String username = order.getUsername();
        Integer payStatus = order.getPayStatus();
        Integer goodsType = order.getGoodsType();
        Integer goodsNum = order.getGoodsNum();
        LocalDateTime createTime = order.getCreateTime();
        //插入数据
        sheet.getRow(startRow).getCell(0).setCellValue(orderId);
        sheet.getRow(startRow).getCell(1).setCellValue(username);
        sheet.getRow(startRow).getCell(2).setCellValue(payStatus==1?"已支付":"未支付");
        //若果为1为棉花，如果为2为塑料颗粒，如果为3为玻璃纤维，若果为4为化工用品
        sheet.getRow(startRow).getCell(3).setCellValue(goodsType==1?"棉花":goodsType==2?"塑料颗粒":goodsType==3?"玻璃纤维":"化工用品");
        sheet.getRow(startRow).getCell(4).setCellValue(goodsNum);
        sheet.getRow(startRow).getCell(5).setCellValue(String.valueOf(createTime));
        //计算总金额
        double totalAmount=goodsNum* GoodsConstant.INPRICE;
        sheet.getRow(endRow).getCell(1).setCellValue(totalAmount);
        //通过输出流，将excel下载到客户端浏览器
        ServletOutputStream out = response.getOutputStream();
        excel.write(out);

        //将excel文件保存到本地
        String localDirPath = "E:\\Warehouse-management-system\\入库报表";
        String fileName = "入库报表_" + orderId + "_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMdd-HHmmss")) + ".xlsx";
        String localFilePath = localDirPath + File.separator + fileName;
        FileOutputStream localOut = new FileOutputStream(localFilePath);
        excel.write(localOut);
        //将支付状态设置为已支付
        order.setPayStatus(OrderPayStatusConstant.ALLREADY_PAY);

        orderMapper.update(order.getPayStatus(),id);
        //关闭资源
        localOut.close();
        out.close();
        excel.close();



    }
}
