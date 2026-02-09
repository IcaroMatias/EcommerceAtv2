package com.atividade.ecommerce_atv2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost: 3306/airdefense";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,USER, PASSWORD);
        }catch (Exception e) {
            throw new RuntimeException("Erro ao conectar: "+e.getMessage());
        }
    }

}
