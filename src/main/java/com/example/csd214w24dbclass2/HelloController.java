package com.example.csd214w24dbclass2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {

    @FXML
    private TableView<User> mytable;
    @FXML
    private TableColumn<User,Integer > id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User,String> email;
    @FXML
    private TableColumn<User,Integer> salary;

    @FXML
    private TableColumn<User,String> password;
    ObservableList<User> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<User,Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<User,String>("name"));
        email.setCellValueFactory(new
                PropertyValueFactory<User,String>("email"));
        salary.setCellValueFactory(new
                PropertyValueFactory<User,Integer>("salary"));
        password.setCellValueFactory(new
                PropertyValueFactory<User,String>("password"));
        mytable.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_demo";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_user";




            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int salary = resultSet.getInt("salary");
                String password = resultSet.getString("password");
                mytable.getItems().add(new User(id, name, email,
                        salary,password));
            }
        } catch (SQLException e) {




            e.printStackTrace();
        }
    }

}