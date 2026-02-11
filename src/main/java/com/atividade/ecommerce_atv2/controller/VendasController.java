package com.atividade.ecommerce_atv2.controller;

import com.atividade.ecommerce_atv2.dao.VendasDAO;
import com.atividade.ecommerce_atv2.model.Venda;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class VendasController {
    @FXML private TextField txtArmamento;
    @FXML private TextField txtClientes;
    @FXML private TextField txtQuantidade;
    @FXML private TextField txtValorUnitario;
    @FXML private TextField txtValorTotal;
    @FXML private TableView<Venda> tabelaVendas;
    @FXML private TableColumn<Venda, Integer> calId;
    @FXML private TableColumn<Venda, String> calArmamento;
    @FXML private TableColumn<Venda, String> calCliente;
    @FXML private TableColumn<Venda, Integer> calQuantidade;
    @FXML private TableColumn<Venda, Double> calValorUnitario;
    @FXML private TableColumn<Venda, Double> calValorTotal;

    private VendasDAO dao = new VendasDAO();
    private Venda vendaSelecionada;

    @FXML
    public void initialize() {
        calId.setCellValueFactory(new PropertyValueFactory<>("id"));
        calArmamento.setCellValueFactory(new PropertyValueFactory<>("armamento"));
        calCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        calQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        calValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        calValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        atualizarTabela();
    }

    private void atualizarTabela() {
        try {
            tabelaVendas.setItems(FXCollections.observableArrayList(dao.listarTodos()));
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void salvarProduto() {
        try {
            if (vendaSelecionada == null) {
                dao.salvar(new Venda(txtArmamento.getText(), txtClientes.getText(), Integer.parseInt(txtQuantidade.getText()), Double.parseDouble(txtValorUnitario.getText()), Double.parseDouble(txtValorTotal.getText())));
            } else {
                vendaSelecionada.setArmamento(txtArmamento.getText());
                vendaSelecionada.setCliente(txtClientes.getText());
                vendaSelecionada.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                vendaSelecionada.setPrecoUnitario(Double.parseDouble(txtValorUnitario.getText()));
                vendaSelecionada.setValorTotal(Double.parseDouble(txtValorTotal.getText()));
                dao.atualizar(vendaSelecionada);
            }
            atualizarTabela();
            limparCampos();
        } catch (Exception e) { exibirAlerta("Erro", e.getMessage()); }
    }

    @FXML
    public void excluirProduto() {
        if (vendaSelecionada != null) {
            try {
                dao.deletar(vendaSelecionada.getId());
                atualizarTabela();
                limparCampos();
            } catch (Exception e) { exibirAlerta("Erro", e.getMessage()); }
        }
    }

    @FXML
    public void selecionarItem() {
        vendaSelecionada = tabelaVendas.getSelectionModel().getSelectedItem();
        if (vendaSelecionada != null) {
            txtArmamento.setText(vendaSelecionada.getArmamento());
            txtClientes.setText(vendaSelecionada.getCliente());
            txtQuantidade.setText(String.valueOf(vendaSelecionada.getQuantidade()));
            txtValorUnitario.setText(String.valueOf(vendaSelecionada.getPrecoUnitario()));
            txtValorTotal.setText(String.valueOf(vendaSelecionada.getValorTotal()));
        }
    }
    @FXML
    public void limparCampos() {
        txtArmamento.clear();
        txtClientes.clear();
        txtQuantidade.clear();
        txtValorUnitario.clear();
        txtValorTotal.clear();
        vendaSelecionada = null;
        tabelaVendas.getSelectionModel().clearSelection();
    }
    private void exibirAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}
