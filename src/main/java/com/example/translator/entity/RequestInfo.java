package com.example.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
public class RequestInfo {

    private Long Id;
    private String inputText;
    private String translatedText;
    private Date date;
    private String translationRule;
    private String ip;
}
