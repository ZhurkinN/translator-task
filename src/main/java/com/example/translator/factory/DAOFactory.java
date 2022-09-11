package com.example.translator.factory;

import com.example.translator.dao.TranslationDAO;

public abstract class DAOFactory {

    public abstract TranslationDAO getTranslationDAO();
}
