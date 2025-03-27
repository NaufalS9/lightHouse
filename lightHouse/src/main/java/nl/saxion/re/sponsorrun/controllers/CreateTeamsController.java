package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateTeamsController {

    @FXML
    private TextField schoolNameField;

    @FXML
    private TextField teamNameField;

    @FXML
    private TextField contactPersonField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    public void initialize() {
        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                phoneNumberField.setText(oldValue);
            }
        });
    }

    @FXML
    private void onBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSubmitButton() {
        String schoolName = schoolNameField.getText().trim();
        String teamName = teamNameField.getText().trim();
        String contactPerson = contactPersonField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String address = addressField.getText().trim();

        if (schoolName.isEmpty() || teamName.isEmpty() || contactPerson.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields!");
            return;
        }

        if (!phoneNumber.matches("\\d+") || phoneNumber.length() < 9) {
            showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number must contain only numbers and be at least 10 digits long.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Team registered successfully!");

        clearFields();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        schoolNameField.clear();
        teamNameField.clear();
        contactPersonField.clear();
        phoneNumberField.clear();
        addressField.clear();
    }
}
