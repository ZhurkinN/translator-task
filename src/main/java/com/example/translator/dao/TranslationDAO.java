package com.example.translator.dao;

import java.sql.Timestamp;

public interface TranslationDAO {

    void addRequestInfo(Long id, Timestamp time, String inputText, String translatedText,
                               String translationRule, String ip);

    void addTranslationInfo(Long requestId, String inputWord, String translatedWord);
}
