package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void initialize() {
        updateTournamentList();
    }

    private void updateTournamentList() {
        tournamentListBox.getChildren().clear();

        for (String[] t : tournaments) {
            VBox tournamentCard = new VBox();
            tournamentCard.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #ECF0F1;");
            tournamentCard.setSpacing(5);

            Label nameLabel = new Label(t[0]);
            Label detailsLabel = new Label(t[1] + " - " + t[2]);
            Label dateLabel = new Label(t[3]);

            tournamentCard.getChildren().addAll(nameLabel, detailsLabel, dateLabel);

            tournamentCard.setOnMouseClicked(event -> openDetailPage(t));

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
