package com.atividade.ecommerce_atv2.controller;

import com.atividade.ecommerce_atv2.dao.ProdutosDAO;
import com.atividade.ecommerce_atv2.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;

public class ProdutosController {
    @FXML private TextField txtNome;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtPreco;
    @FXML private TextField txtEstoque;
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto,Integer> calId;
    @FXML private TableColumn<Produto, String> calNome;
    @FXML private TableColumn<Produto, String> calCodigo;
    @FXML private TableColumn<Produto, Double> calPreco;
    @FXML private TableColumn<Produto, Integer> calEstoque;

    private ProdutosDAO dao = new ProdutosDAO();
    private Produto produtoSelecionado;

    @FXML
    public void  initialize() {
        calId.setCellValueFactory(new PropertyValueFactory<>("id"));
        calNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        calCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        calPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        calEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        atualizarTabela();
    }

    private void atualizarTabela() {
        try {
            tabelaProdutos.setItems(FXCollections.observableArrayList(dao.listarTodos()));
        } catch (Exception e) { e.printStackTrace();}
    }

    @FXML
    public void salvarProduto() {
        try {
            if (produtoSelecionado == null) {
                dao.salvar(new Produto(txtNome.getText(), txtCodigo.getText(), Double.parseDouble(txtPreco.getText()), Integer.parseInt(txtEstoque.getText())));
            } else {
                produtoSelecionado.setNome(txtNome.getText());
                produtoSelecionado.setCodigo(txtCodigo.getText());
                produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                produtoSelecionado.setEstoque(Integer.parseInt(txtEstoque.getText()));
                dao.atualizar(produtoSelecionado);
            }
            atualizarTabela();
            limparCampos();
        } catch (Exception e) { exibirAlerta("Erro", e.getMessage());}
    }

    @FXML
    public  void excluirProduto() {
        if (produtoSelecionado != null) {
            try {
                dao.deletar(produtoSelecionado.getId());
                atualizarTabela();
                limparCampos();
            } catch (Exception e) { exibirAlerta("Erro", e.getMessage());}
        }
    }

    @FXML
    public void selecionarItem() {
        produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            txtNome.setText(produtoSelecionado.getNome());
            txtCodigo.setText(produtoSelecionado.getCodigo());
            txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
            txtEstoque.setText(String.valueOf(produtoSelecionado.getEstoque()));
        }
    }

    @FXML
    public void limparCampos() {
        txtNome.clear();
        txtPreco.clear();
        produtoSelecionado = null;
        tabelaProdutos.getSelectionModel().clearSelection();
    }
    private void exibirAlerta( String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}

