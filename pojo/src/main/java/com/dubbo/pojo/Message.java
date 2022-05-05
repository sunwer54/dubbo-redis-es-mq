package com.dubbo.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Message implements Serializable {
    private Object object;
    private String msg;
}
