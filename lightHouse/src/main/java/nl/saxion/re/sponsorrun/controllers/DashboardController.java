package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class DashboardController {
    @FXML
    private void onCreateTournament() {
        WindowHelper.openWindow("another-screen.fxml", "Overview of politicians", 800, 600);

    }

    @FXML
    private void handleOptions() {
        System.out.println("Options button clicked!");
    }
}
