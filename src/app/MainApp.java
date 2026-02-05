package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        TableView<Student> table = new TableView<>();
        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(
            new javafx.scene.control.cell.PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(
            new javafx.scene.control.cell.PropertyValueFactory<>("name"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(
            new javafx.scene.control.cell.PropertyValueFactory<>("email"));

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(
            new javafx.scene.control.cell.PropertyValueFactory<>("course"));

        table.getColumns().addAll(idCol, nameCol, emailCol, courseCol);
        table.setItems(StudentController.getStudents());

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField course = new TextField();
        course.setPromptText("Course");

        Button add = new Button("Add");
        add.setOnAction(e -> {
            StudentController.insert(
                name.getText(), email.getText(), course.getText());
            table.setItems(StudentController.getStudents());
        });

        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            Student s = table.getSelectionModel().getSelectedItem();
            if (s != null) {
                StudentController.delete(s.getId());
                table.setItems(StudentController.getStudents());
            }
        });

        VBox root = new VBox(10,
            table, name, email, course, add, delete);

        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle("Student CRUD - JavaFX + MySQL");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
