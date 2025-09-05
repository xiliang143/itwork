package com.xiliang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    //序列化版本号
    private static final long serialVersionUID = 1L;


    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String sex;
    private String idNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
