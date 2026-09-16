package org.example.module_3_assignment_1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

        // The fields in which the user will enter the values
        private TextField tfAnnualInterestRate = new TextField();
        private TextField tfNumberOfYears = new TextField();
        private TextField tfLoanAmount = new TextField();
        private TextField tfMonthlyPayment = new TextField();
        private TextField tfTotalPayment = new TextField();
        // The fields in which the user will see the results
        @Override
        public void start(Stage primaryStage) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            // Row 0: Annual Interest Rate
            gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
            gridPane.add(tfAnnualInterestRate, 1, 0);
            tfAnnualInterestRate.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);

            // Row 1: Number of Years
            gridPane.add(new Label("Number of Years:"), 0, 1);
            gridPane.add(tfNumberOfYears, 1, 1);
            tfNumberOfYears.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);

            // Row 2: Loan Amount
            gridPane.add(new Label("Loan Amount:"), 0, 2);
            gridPane.add(tfLoanAmount, 1, 2);
            tfLoanAmount.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);

            // Row 3: Monthly Payment (output, not editable)
            gridPane.add(new Label("Monthly Payment:"), 0, 3);
            gridPane.add(tfMonthlyPayment, 1, 3);
            tfMonthlyPayment.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            tfMonthlyPayment.setEditable(false);

            // Row # 4 - Total Payment
            gridPane.add(new Label("Total Payment:"), 0, 4);
            gridPane.add(tfTotalPayment, 1, 4);
            tfTotalPayment.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            tfTotalPayment.setEditable(false);

            // Row # 5 -  Button
            Button btCalculate = new Button("Calculate");
            gridPane.add(btCalculate, 1, 5);
            GridPane.setHalignment(btCalculate, HPos.RIGHT);

            // Setting the button behavior
            btCalculate.setOnAction(e -> calculatePayment());

            // Staging the scene
            Scene scene = new Scene(gridPane, 300, 275);
            primaryStage.setTitle("Loan Payment Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        // The method that calculates the monthly payment
        private void calculatePayment() {
            try {
                double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());
                int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
                double loanAmount = Double.parseDouble(tfLoanAmount.getText());

                double monthlyInterestRate = annualInterestRate / 1200;
                int numberOfMonths = numberOfYears * 12;

                double monthlyPayment;

                if (monthlyInterestRate == 0) {
                    monthlyPayment = loanAmount / numberOfMonths;
                } else {
                    monthlyPayment = loanAmount * monthlyInterestRate /
                            (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfMonths)));
                }

                double totalPayment = monthlyPayment * numberOfMonths;

                tfMonthlyPayment.setText(String.format("$%.2f", monthlyPayment));
                tfTotalPayment.setText(String.format("$%.2f", totalPayment));
            } catch (NumberFormatException ex) {
                tfMonthlyPayment.setText("Invalid input");
                tfTotalPayment.setText("Invalid input");
            }
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
