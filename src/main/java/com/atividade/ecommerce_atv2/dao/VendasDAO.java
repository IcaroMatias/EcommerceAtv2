package com.atividade.ecommerce_atv2.dao;

import com.atividade.ecommerce_atv2.model.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO {
    private Connection connection;

    public VendasDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void salvar(Venda venda) throws SQLException {
        String sql = "INSERT INTO vendas (armamento, cliente, quantidade, precoUnitario, valorTotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, venda.getArmamento());
            stmt.setString(2,venda.getCliente());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4,venda.getPrecoUnitario());
            stmt.setDouble(5, venda.getValorTotal());
            stmt.execute();
        }
    }

    public List<Venda> listarTodos() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setArmamento(rs.getString("armamento"));
                v.setCliente(rs.getString("cliente"));
                v.setQuantidade(rs.getInt("quantidade"));
                v.setPrecoUnitario(rs.getDouble("precoUnitario"));
                v.setValorTotal(rs.getDouble("valorTotal"));
                vendas.add(v);
            }
        }
        return vendas;
    }

    public void atualizar(Venda venda) throws SQLException {
        String sql = "UPDATE vendas SET armamento = ?, cliente = ?, quantidade = ?, precoUnitario = ?, valorTotal = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, venda.getArmamento());
            stmt.setString(2, venda.getCliente());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getPrecoUnitario());
            stmt.setDouble(5, venda.getValorTotal());
            stmt.setInt(6, venda.getId());
            stmt.execute();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM vendas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
}
