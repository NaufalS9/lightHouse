package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.TeamDataManager;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class CreateTournamentController {

    @FXML
    private Button button;

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
        try {
            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/dashboard.fxml",
                    "Dashboard", 800, 600,
                    (Stage) button.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }    }

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

        TeamDataManager.getInstance().setCurrentTournament(name);
        TeamDataManager.getInstance().clearTeamsForTournament();

        DashboardController.addTournament(name, sport, venue, date, format);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Tournament added successfully!");

        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();

        try {
            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/dashboard.fxml", "Dashboard", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
