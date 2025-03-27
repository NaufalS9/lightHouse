package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailPageController {

    @FXML
    private Label tournamentNameLabel, sportTypeLabel, matchFormatLabel, dateLabel, venueLabel;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableColumn<Team, String> schoolColumn;

    @FXML
    private TableColumn<Team, String> teamColumn;

    @FXML
    private TableColumn<Team, String> contactColumn;

    @FXML
    private TableColumn<Team, String> phoneColumn;

    @FXML
    private TableColumn<Team, String> addressColumn;

    private final List<Team> teams = new ArrayList<>();

    private String[] tournamentDetails;

    @FXML
    public void initialize() {
        // Initialize the table columns
        schoolColumn.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public void setTournamentDetails(String[] details) {
        this.tournamentDetails = details;

        // Safely set label values
        tournamentNameLabel.setText(details.length > 0 ? details[0] : "Unknown Tournament");
        sportTypeLabel.setText(details.length > 1 ? details[1] : "Unknown Sport");
        venueLabel.setText(details.length > 2 ? details[2] : "Unknown Venue");
        dateLabel.setText(details.length > 3 ? details[3] : "Unknown Date");
        matchFormatLabel.setText(details.length > 4 ? details[4] : "Unknown Format");
    }

    @FXML
    private void onAddTeam() {
        try {
            // Load the Create Team view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/saxion/re/sponsorrun/create-teams.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Create Team");
            stage.show();

            // Pass this controller to the CreateTeamsController
            CreateTeamsController controller = loader.getController();
            controller.setDetailPageController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTeam(String school, String teamName, String contactPerson, String phoneNumber, String address) {
        // Create a new Team object
        Team team = new Team(school, teamName, contactPerson, phoneNumber, address);

        // Add the team to the list
        teams.add(team);

        // Update the TableView
        teamsTable.getItems().add(team);
    }

    // Inner class to represent a Team
    public static class Team {
        private String schoolName;
        private String teamName;
        private String contactPerson;
        private String phoneNumber;
        private String address;

        public Team(String schoolName, String teamName, String contactPerson, String phoneNumber, String address) {
            this.schoolName = schoolName;
            this.teamName = teamName;
            this.contactPerson = contactPerson;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        // Getters
        public String getSchoolName() {
            return schoolName;
        }

        public String getTeamName() {
            return teamName;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddress() {
            return address;
        }
    }
}