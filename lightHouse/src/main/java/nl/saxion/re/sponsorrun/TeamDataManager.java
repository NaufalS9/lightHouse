package nl.saxion.re.sponsorrun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.saxion.re.sponsorrun.data.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamDataManager {
    private static TeamDataManager instance;
    private final Map<String, ObservableList<Team>> tournamentTeams;
    private String currentTournament;

    private TeamDataManager() {
        tournamentTeams = new HashMap<>();
    }

    public static TeamDataManager getInstance() {
        if (instance == null) {
            instance = new TeamDataManager();
        }
        return instance;
    }

    public void setCurrentTournament(String tournamentName) {
        this.currentTournament = tournamentName;
        tournamentTeams.putIfAbsent(tournamentName, FXCollections.observableArrayList());
    }

    public ObservableList<Team> getTeamList() {
        return tournamentTeams.getOrDefault(currentTournament, FXCollections.observableArrayList());
    }

    public void addTeam(Team team) {
        if (currentTournament != null) {
            tournamentTeams.get(currentTournament).add(team);
        }
    }

    public void removeTeam(Team team) {
        if (currentTournament != null) {
            tournamentTeams.get(currentTournament).remove(team);
        }
    }

    public void clearTeamsForTournament() {
        if (currentTournament != null) {
            tournamentTeams.put(currentTournament, FXCollections.observableArrayList());
        }
    }
}
