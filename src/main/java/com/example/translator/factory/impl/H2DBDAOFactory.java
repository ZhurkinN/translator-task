package com.example.translator.factory.impl;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.factory.DAOFactory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
@PropertySource("classpath:application.properties")
public class H2DBDAOFactory extends DAOFactory {

    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String user;
    @Value(value = "${spring.datasource.password}")
    private String password;

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

        connection = DriverManager.getConnection("jdbc:h2:mem:DB", "sa", "");
    }

    @Override
    public TranslationDAOImpl getTranslationDAO() {
        return new TranslationDAOImpl(connection);
    }
}
