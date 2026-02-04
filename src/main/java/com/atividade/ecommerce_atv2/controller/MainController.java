package com.atividade.ecommerce_atv2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    public void abrirHome() {
        carregarTela("Home.fxml");
    }

    @FXML
    public void abrirProdutos() {
        carregarTela("Produtos.fxlm");
    }

    @FXML
    public void abrirClientes() {
        carregarTela("Clientes.fxml");
    }

    private void carregarTela(String fxml) {
        try {
            root.setCenter(
                    FXMLLoader.load((getClass().getResource("/fxml/" + fxml)))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
