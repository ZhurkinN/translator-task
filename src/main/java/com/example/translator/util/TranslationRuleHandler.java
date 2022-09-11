package com.example.translator.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TranslationRuleHandler {

    private static final int SOURCE_LANGUAGE_INDEX = 0;
    private static final int TARGET_LANGUAGE_INDEX = 1;

    public String sourceLanguage;
    public String targetLanguage;

    public TranslationRuleHandler(String translationRule) {

        handleTranslationRule(translationRule);
    }

    private void handleTranslationRule(String translationRule) {

        String[] languages = translationRule.split("-");
        setSourceLanguage(languages[SOURCE_LANGUAGE_INDEX]);
        setTargetLanguage(languages[TARGET_LANGUAGE_INDEX]);
    }
}
