module com.atividade.ecommerce_atv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.atividade.ecommerce_atv2 to javafx.fxml;
    exports com.atividade.ecommerce_atv2;
    exports com.atividade.ecommerce_atv2.controller;
    opens com.atividade.ecommerce_atv2.controller to javafx.fxml;
}