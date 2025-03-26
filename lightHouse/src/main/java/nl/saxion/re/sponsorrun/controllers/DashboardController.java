package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nl.saxion.re.sponsorrun.util.WindowHelper;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    @FXML
    private VBox tournamentListBox;

    private static final List<String[]> tournaments = new ArrayList<>();

    public static void addTournament(String name, String sport, String venue, String date) {
        tournaments.add(new String[]{name, sport, venue, date});
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

            tournamentCard.getChildren().addAll(
                    new Label(t[0]),
                    new Label(t[1] + " - " + t[2]),
                    new Label(t[3])
            );

            tournamentListBox.getChildren().add(tournamentCard);
        }
    }

    @FXML
    private void onCreateTournament() {
        WindowHelper.openWindow("create-tournament.fxml", "Create tournament", 800, 600);
    }

    @FXML
    private void handleOptions() {
        System.out.println("Options button clicked!");
    }
}
