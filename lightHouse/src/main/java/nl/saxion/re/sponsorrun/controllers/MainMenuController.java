package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class MainMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onFirstButtonClick() {
        WindowHelper.openWindow("overview-screen.fxml", "Overview of politicians", 800, 600);
    }

    @FXML
    protected void onSecondButtonClick() {
        WindowHelper.openWindow("another-screen.fxml", "Wow, such empty", 800, 600);
    }
}