package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void onLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if ("admin".equals(username) && "SUCCESSefforts".equals(password)) {
            try {
                WindowHelper.openWindow("/nl/saxion/re/sponsorrun/dashboard.fxml",
                        "Dashboard", 800, 600,
                        (Stage) loginButton.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Login Failed", "Wrong username or password");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
