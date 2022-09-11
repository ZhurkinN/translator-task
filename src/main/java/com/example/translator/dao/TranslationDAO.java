package com.example.translator.dao;

import java.sql.Date;

public interface TranslationDAO {

    public void addRequestInfo(Long id, Date date, String inputText, String translatedText,
                               String translationRule, String ip);

    public void addTranslationInfo(Long requestId, String inputWord, String translatedWord);
}
