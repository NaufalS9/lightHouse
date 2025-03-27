package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class CreateTournamentController {

    @FXML
    private TextField tournamentNameField, venueField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> sportTypeBox, matchFormatBox;

    public void initialize() {
        sportTypeBox.getItems().addAll("Football", "Basketball", "Tennis", "Badminton");
        matchFormatBox.getItems().addAll("Knockout", "Round Robin", "League");
    }

    @FXML
    private void onBackButton() {
        WindowHelper.openWindow("dashboard.fxml", "Dashboard", 800, 600);
    }

    @FXML
    private void onSubmitButton() {
        String name = tournamentNameField.getText().trim();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
        String venue = venueField.getText().trim();
        String sport = sportTypeBox.getValue();
        String format = matchFormatBox.getValue();

        if (name.isEmpty() || venue.isEmpty() || sport == null || format == null || date.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields!");
            return;
        }

        DashboardController.addTournament(name, sport, venue, date);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Tournament added successfully!");

        WindowHelper.openWindow("dashboard.fxml", "Dashboard", 800, 600);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
