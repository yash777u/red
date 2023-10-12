package com.example.red;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class HelloController {
    @FXML
    private TextField firstName,lastName,accountBalance;

    @FXML
    private Label label;

    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField password;


    @FXML
    protected int account_bal;

    DatabaseConnection connection = new DatabaseConnection();
    Connection connectionDB = connection.getConnection();

    @FXML
    protected void onClickCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void insertUser() {
        try {
            String insertQuery = "INSERT INTO `bank`.`UserAccounts` (Firstname, Lastname, Password, Balance, AccountNumber) VALUES ('" + firstName.getText() + "', '" + lastName.getText() + "', '" + password.getText() + "', " + account_bal + ", LPAD(FLOOR(RAND() * 100000), 5, '0'));";
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(insertQuery);

            // Retrieve the account number of the registered user
            String selectQuery = "SELECT AccountNumber FROM `bank`.`UserAccounts` WHERE UserID = LAST_INSERT_ID();";
            statement = connectionDB.createStatement();
            statement.execute(selectQuery);

            // Fetch the result
            String accountNumber = "";
            while (statement.getResultSet().next()) {
                accountNumber = statement.getResultSet().getString("AccountNumber");
            }

            // Display the result on the label
            label.setText("Registered Successfully! Your Account Number is: " + accountNumber);
        } catch (Exception p) {
            p.printStackTrace();
        }
    }
    @FXML
    protected  void onClickRegister(ActionEvent e)
    {
        account_bal = Integer.parseInt(accountBalance.getText());
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || (password != null && password.getText().isEmpty()) || account_bal == 0 )

        {
            label.setText("Don't leave any feild Blank !!");
        }

        else
        {
            try
            {
                insertUser();
            }

            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

}