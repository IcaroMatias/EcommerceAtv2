package com.atividade.ecommerce_atv2.dao;

import com.atividade.ecommerce_atv2.model.Cliente;
import com.atividade.ecommerce_atv2.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    private Connection connection;

    public ClientesDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void salvar(Cliente cliente) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3,cliente.getTelefone());
            stmt.setString(4,cliente.getEmail());
            stmt.setString(5,cliente.getEndereco());
            stmt.execute();
        }
    }
    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                Cliente c = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );

                c.setId(rs.getInt("id"));
                clientes.add(c);
            }
        }
        return clientes;
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, telefone = ?, email = ?, endereco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId());
            stmt.execute();
        }
    }

    public void deletar(int id) throws  SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
}
