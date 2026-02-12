package com.atividade.ecommerce_atv2.dao;

import com.atividade.ecommerce_atv2.model.Produto;
import com.atividade.ecommerce_atv2.dao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    // SALVAR
    public void salvar(Produto p) throws SQLException {

        String sql = "INSERT INTO produtos (nome, codigo, preco, estoque) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCodigo());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getEstoque());

            stmt.executeUpdate(); // ESSENCIAL
        }
    }

    // LISTAR TODOS
    public List<Produto> listarTodos() throws SQLException {

        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getString("codigo"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));

                lista.add(p);
            }
        }

        return lista;
    }

    // ATUALIZAR
    public void atualizar(Produto p) throws SQLException {

        String sql = "UPDATE produtos SET nome=?, codigo=?, preco=?, estoque=? WHERE id=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCodigo());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getEstoque());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();
        }
    }

    // DELETAR
    public void deletar(int id) throws SQLException {

        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}