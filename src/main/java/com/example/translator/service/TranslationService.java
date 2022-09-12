package com.example.translator.service;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.dto.RequestDTO;
import com.example.translator.dto.TranslationResultsDTO;
import com.example.translator.factory.impl.H2DBDAOFactory;
import com.example.translator.util.IdRandomizer;
import com.example.translator.util.TextTranslator;
import com.example.translator.util.TranslationRuleHandler;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;

@NoArgsConstructor
@Service
public class TranslationService {

    public String translateText(RequestDTO dto, HttpServletRequest request) throws SQLException {

        TranslationDAOImpl dao = H2DBDAOFactory.getInstance()
                .getTranslationDAO();
        Long id = IdRandomizer.randomizeId();
        String inputText = dto.getInputText();
        String translationRule = dto.getTranslationRule();
        String sourceLanguage = new TranslationRuleHandler(translationRule).getSourceLanguage();
        String targetLanguage = new TranslationRuleHandler(translationRule).getTargetLanguage();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String ip = request.getRemoteAddr();

        TranslationResultsDTO translationResults = TextTranslator.translateSingleWords(inputText,
                sourceLanguage, targetLanguage);

        return TextTranslator.insertAllInfo(id, time, inputText,
                translationRule, ip, translationResults, dao);
    }
}
