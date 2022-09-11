package com.example.translator.factory.impl;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.factory.DAOFactory;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.translator.constant.JDBCConnectionParametersKeeper.*;

@NoArgsConstructor
@PropertySource("classpath:application.properties")
public class H2DBDAOFactory extends DAOFactory {

    private static volatile H2DBDAOFactory instance;
    private Connection connection;

    public static H2DBDAOFactory getInstance() throws SQLException {
        H2DBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (H2DBDAOFactory.class) {
                instance = factory = new H2DBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() throws SQLException {

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public TranslationDAOImpl getTranslationDAO() {
        return new TranslationDAOImpl(connection);
    }
}
