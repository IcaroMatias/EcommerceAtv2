package com.atividade.ecommerce_atv2.dao;

import java.sql.*;

public class UsuariosDAO {

    private Connection connection = new Conexao().getConnection();

    public boolean autenticar(String username, String senha) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // se encontrou, login válido
        }
    }
}
