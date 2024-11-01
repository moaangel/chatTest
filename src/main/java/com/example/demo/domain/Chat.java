package com.example.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Chat {

    private String senderName;
    private String text;
    private Timestamp createdTime;
}
