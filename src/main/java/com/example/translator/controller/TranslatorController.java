package com.example.translator.controller;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.dto.RequestDTO;
import com.example.translator.dto.ResponseDTO;
import com.example.translator.dto.TranslationResultsDTO;
import com.example.translator.factory.impl.H2DBDAOFactory;
import com.example.translator.util.IdRandomizer;
import com.example.translator.util.TextTranslator;
import com.example.translator.util.TranslationRuleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TranslatorController {

    @PostMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getTranslation(@RequestBody RequestDTO dto)
            throws SQLException {

        TranslationDAOImpl dao = H2DBDAOFactory.getInstance()
                .getTranslationDAO();

        Long id = IdRandomizer.randomizeId();
        String inputText = dto.getInputText();
        String translationRule = dto.getTranslationRule();
        String sourceLanguage = new TranslationRuleHandler(translationRule).getSourceLanguage();
        String targetLanguage = new TranslationRuleHandler(translationRule).getTargetLanguage();

        TranslationResultsDTO translationResults = TextTranslator.translateSingleWords(inputText,
                sourceLanguage, targetLanguage);

        String translatedText = TextTranslator.insertAllInfo(id, new Date(12, 12, 12), inputText,
                translationRule, "aa", translationResults, dao);

        return ResponseEntity.ok(new ResponseDTO(translatedText));
    }

}
