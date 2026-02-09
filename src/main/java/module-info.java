module com.atividade.ecommerce_atv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.atividade.ecommerce_atv2.controller to javafx.fxml;
    opens com.atividade.ecommerce_atv2 to javafx.fxml;

    exports com.atividade.ecommerce_atv2;
    exports com.atividade.ecommerce_atv2.controller;
    exports com.atividade.ecommerce_atv2.model;
    exports com.atividade.ecommerce_atv2.dao;

}