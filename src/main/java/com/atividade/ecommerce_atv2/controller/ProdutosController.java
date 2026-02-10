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
    @FXML private TextField txtPreco;
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto,Integer> colId;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;

    private ProdutosDAO dao = new ProdutosDAO();
    private Produto produtoSelecionado;

    @FXML
    public void  initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
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
                dao.salvar(new Produto(txtNome.getText(),Double.parseDouble(txtPreco.getText())));
            } else {
                produtoSelecionado.setNome(txtPreco.getText());
                produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
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
            txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
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

