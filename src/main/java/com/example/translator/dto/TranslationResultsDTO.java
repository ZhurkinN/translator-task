package com.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationResultsDTO {

    private String translatedText;
    private String[] inputWords;
    private String[] translatedWords;
}
