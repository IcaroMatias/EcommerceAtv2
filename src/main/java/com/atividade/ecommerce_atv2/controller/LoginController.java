package com.atividade.ecommerce_atv2.controller;

import com.atividade.ecommerce_atv2.dao.UsuariosDAO;
import com.atividade.ecommerce_atv2.dao.UsuariosDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtSenha;

    private UsuariosDAO dao = new UsuariosDAO();

    @FXML
    public void fazerLogin(ActionEvent event) {

        try {
            boolean autenticado = dao.autenticar(
                    txtUsuario.getText(),
                    txtSenha.getText()
            );

            if (autenticado) {
                abrirTelaPrincipal(event);
            } else {
                exibirAlerta("Erro", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            exibirAlerta("Erro", e.getMessage());
        }
    }

    private void abrirTelaPrincipal(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/MainLayout.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sistema E-commerce");
        stage.show();
    }

    private void exibirAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}
