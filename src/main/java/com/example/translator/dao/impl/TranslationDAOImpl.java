package com.example.translator.dao.impl;

import com.example.translator.dao.TranslationDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import static com.example.translator.constant.SQLCodeKeeper.ADDING_REQUEST_INFO_SQL_CODE;
import static com.example.translator.constant.SQLCodeKeeper.ADDING_TRANSLATION_INFO_SQL_CODE;

@AllArgsConstructor
@RequiredArgsConstructor
public class TranslationDAOImpl implements TranslationDAO {

    private Connection connection;

    @Override
    public void addRequestInfo(Long id, Timestamp time, String inputText, String translatedText,
                               String translationRule, String ip) {

        try (PreparedStatement statement = connection.prepareStatement(ADDING_REQUEST_INFO_SQL_CODE)) {

            statement.setLong(1, id);
            statement.setTimestamp(2, time);
            statement.setString(3, inputText);
            statement.setString(4, translatedText);
            statement.setString(5, translationRule);
            statement.setString(6, ip);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public void addTranslationInfo(Long requestId, String inputWord, String translatedWord) {

        try (PreparedStatement statement = connection.prepareStatement(ADDING_TRANSLATION_INFO_SQL_CODE)) {

            statement.setLong(1, requestId);
            statement.setString(2, inputWord);
            statement.setString(3, translatedWord);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
