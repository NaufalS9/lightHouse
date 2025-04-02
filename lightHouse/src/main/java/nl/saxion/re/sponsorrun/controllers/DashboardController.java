package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.util.WindowHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    @FXML
    private Button button;

    @FXML
    private VBox tournamentListBox;

    private static final List<String[]> tournaments = new ArrayList<>();

    @FXML
    private Button logoutButton;

    @FXML
    private void onLogout() {
        try {
            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/login.fxml",
                    "Login", 800, 600,
                    (Stage) logoutButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTournament(String name, String sport, String venue, String date, String format) {
        tournaments.add(new String[]{name, sport, venue, date, format});
    }

    @FXML
    private ScrollPane tournamentScrollPane;

    @FXML
    public void initialize() {
        updateTournamentList();
        tournamentScrollPane.setFitToWidth(true);
    }

    private void updateTournamentList() {
        tournamentListBox.getChildren().clear();

        for (String[] t : tournaments) {
            VBox tournamentCard = new VBox();
            tournamentCard.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-padding: 15; -fx-background-color: #FFFFFF; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0.5, 0, 0);");
            tournamentCard.setSpacing(10);

            Label nameLabel = new Label(t[0]);
            nameLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
            Label sportLabel = new Label(t[1] + " - " + t[2]);
            sportLabel.setStyle("-fx-font-size: 12;");
            Label dateLabel = new Label(t[3]);
            dateLabel.setStyle("-fx-font-size: 12;");

            // Add a slight hover effect for interactivity
            tournamentCard.setOnMouseEntered(event -> tournamentCard.setStyle("-fx-border-color: #3498DB; -fx-border-width: 1; -fx-padding: 15; -fx-background-color: #ECF0F1; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0.5, 0, 0);"));
            tournamentCard.setOnMouseExited(event -> tournamentCard.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-padding: 15; -fx-background-color: #FFFFFF; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0.5, 0, 0);"));

            tournamentCard.setOnMouseClicked(event -> openDetailPage(t));

            tournamentCard.getChildren().addAll(nameLabel, sportLabel, dateLabel);
            tournamentListBox.getChildren().add(tournamentCard);
        }
    }

    private void openDetailPage(String[] tournamentDetails) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/saxion/re/sponsorrun/detail-page.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 800, 600));

            DetailPageController controller = loader.getController();
            controller.setTournamentDetails(tournamentDetails);

            stage.setTitle("Tournament Details");

            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onCreateTournament() {
        try {
            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/create-tournament.fxml",
                    "Create Tournament", 800, 600,
                    (Stage) button.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
