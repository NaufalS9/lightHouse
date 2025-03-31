package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class CreateTeamsController {

    @FXML
    private TextField schoolNameField, teamNameField, contactPersonField, phoneNumberField, addressField;

    @FXML
    private Button backButton, submitButton;

    private DetailPageController detailPageController;

    public void setDetailPageController(DetailPageController controller) {
        this.detailPageController = controller;
    }

    @FXML
    private void onBackButton() {
        try {
            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/detail-page.fxml",
                    "Detail page", 800, 600,
                    (Stage) backButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSubmitButton() {
        String schoolName = schoolNameField.getText().trim();
        String teamName = teamNameField.getText().trim();
        String contactPerson = contactPersonField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String address = addressField.getText().trim();

        // Validate all fields
        if (schoolName.isEmpty() || teamName.isEmpty() ||
                contactPerson.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields!");
            return;
        }

        // Validate phone number (optional, you can add more sophisticated validation)
        if (!phoneNumber.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid 10-digit phone number!");
            return;
        }

        if (detailPageController != null) {
            // Update the DetailPageController to accept new team details
            detailPageController.addTeam(schoolName, teamName, contactPerson, phoneNumber, address);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not add team. Detail page controller is missing.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Team registered successfully!");

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}