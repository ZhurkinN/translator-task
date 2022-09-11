package com.example.translator.constant;

public class SQLCodeKeeper {

    public static final String ADDING_REQUEST_INFO_SQL_CODE =
            "INSERT INTO request_info(id, date, input_text, translated_text, translation_rule, ip) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String ADDING_TRANSLATION_INFO_SQL_CODE =
            "INSERT INTO translation_info(request_id, input_word, translated_word) VALUES(?, ?, ?)";
}
