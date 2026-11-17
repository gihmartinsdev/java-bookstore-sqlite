package br.com.bookstore.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    private static final String URL = "jdbc:sqlite:libraria.db";

    private ConexaoFactory() {
        // Construtor privado para evitar instanciação.
    }

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do SQLite não localizado no classpath.", e);
        }
    }
}
