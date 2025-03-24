package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
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
        // Get input values
        String name = tournamentNameField.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "No Date";
        String venue = venueField.getText();
        String sport = sportTypeBox.getValue();
        String format = matchFormatBox.getValue();

        // Validate input
        if (name.isEmpty() || venue.isEmpty() || sport == null || format == null) {
            System.out.println("Please fill all fields!");
            return;
        }

        // Save tournament and update Dashboard
        DashboardController.addTournament(name, sport, venue, date);

        // Go back to the Dashboard
        WindowHelper.openWindow("dashboard.fxml", "Dashboard", 800, 600);
    }
}
