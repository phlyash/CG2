module com.example.graphdrawer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graphdrawer to javafx.fxml;
    exports com.example.graphdrawer;
    exports com.example.interpreter;
    opens com.example.interpreter to javafx.fxml;
    exports com.example.interpreter.nodes;
    opens com.example.interpreter.nodes to javafx.fxml;
    exports com.example.graphdrawer.Menu;
    opens com.example.graphdrawer.Menu to javafx.fxml;
}