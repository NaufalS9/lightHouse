package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.data.Team;
import nl.saxion.re.sponsorrun.TeamDataManager;
import nl.saxion.re.sponsorrun.util.WindowHelper;

import java.io.IOException;

public class DetailPageController {

    @FXML
    private Label tournamentNameLabel, sportTypeLabel, matchFormatLabel, dateLabel, venueLabel;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private Button onBackButton;

    @FXML
    private TableColumn<Team, String> schoolColumn, teamColumn, contactColumn, phoneColumn, addressColumn;

    @FXML
    public void initialize() {
        schoolColumn.setCellValueFactory(new PropertyValueFactory<>("school"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public void setTournamentDetails(String[] details) {
        String tournamentName = details.length > 0 ? details[0] : "Unknown Tournament";
        tournamentNameLabel.setText(tournamentName);
        sportTypeLabel.setText(details.length > 1 ? details[1] : "Unknown Sport");
        venueLabel.setText(details.length > 2 ? details[2] : "Unknown Venue");
        dateLabel.setText(details.length > 3 ? details[3] : "Unknown Date");
        matchFormatLabel.setText(details.length > 4 ? details[4] : "Unknown Format");

        TeamDataManager.getInstance().setCurrentTournament(tournamentName);  // Set current tournament
        teamsTable.setItems(TeamDataManager.getInstance().getTeamList());    // Load teams for this tournament
    }


    @FXML
    private void onAddTeam() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/saxion/re/sponsorrun/create-teams.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 800, 600));

            CreateTeamsController controller = loader.getController();
            controller.setDetailPageController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackButton() {
        try {
            Stage currentStage = (Stage) onBackButton.getScene().getWindow();
            currentStage.close();

            WindowHelper.openWindow("/nl/saxion/re/sponsorrun/dashboard.fxml", "Dashboard", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
