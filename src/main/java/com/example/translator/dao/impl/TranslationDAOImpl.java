package com.example.translator.dao.impl;

import com.example.translator.dao.TranslationDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.translator.constant.SQLCodeKeeper.ADDING_REQUEST_INFO_SQL_CODE;
import static com.example.translator.constant.SQLCodeKeeper.ADDING_TRANSLATION_INFO_SQL_CODE;

@AllArgsConstructor
@RequiredArgsConstructor
public class TranslationDAOImpl implements TranslationDAO {

    private Connection connection;

    @Override
    public void addRequestInfo(Long id, Date date, String inputText, String translatedText,
                               String translationRule, String ip) {

        try (PreparedStatement statement = connection.prepareStatement(ADDING_REQUEST_INFO_SQL_CODE)) {

            statement.setLong(1, id);
            statement.setDate(2, date);
            statement.setString(3, inputText);
            statement.setString(4, translatedText);
            statement.setString(5, translationRule);
            statement.setString(6, ip);
            statement.executeUpdate();
        } catch (SQLException e) {

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

        }
    }
}
