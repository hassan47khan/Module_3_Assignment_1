module org.example.module_3_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.module_3_assignment_1 to javafx.fxml;
    exports org.example.module_3_assignment_1;
}