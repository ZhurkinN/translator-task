package com.example.translator.factory;

import com.example.translator.dao.TranslationDAO;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DAOFactory factory) {
        return factory;
    }

    public abstract TranslationDAO getTranslationDAO();
}
