module helloworld.schedulem {
    requires javafx.controls;
    requires javafx.fxml;


    opens helloworld.schedulem to javafx.fxml;
    exports helloworld.schedulem;
}