package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.data.Team;
import nl.saxion.re.sponsorrun.TeamDataManager;

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

        if (schoolName.isEmpty() || teamName.isEmpty() || contactPerson.isEmpty() ||
                phoneNumber.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields!");
            return;
        }

        if (!phoneNumber.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid 10-digit phone number!");
            return;
        }

        Team team = new Team(schoolName, teamName, contactPerson, phoneNumber, address);
        TeamDataManager.getInstance().addTeam(team); // Add team to singleton

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
