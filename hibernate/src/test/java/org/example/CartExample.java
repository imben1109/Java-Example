package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hibernate.cfg.JdbcSettings.PASS;
import static org.hibernate.cfg.JdbcSettings.URL;
import static org.hibernate.cfg.JdbcSettings.USER;

public class CartExample {

    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Cart.class)
            .setProperty(URL, "jdbc:h2:mem:db1")
            .setProperty(USER, "sa")
            .setProperty(PASS, "")
            .setProperty("hibernate.hbm2ddl.auto", "create-drop")
            .buildSessionFactory();

    @Test
    @DisplayName("can save")
    public void testSave(){
        sessionFactory.inTransaction(session -> {
            session.persist(new Cart());
        });
    }
}
