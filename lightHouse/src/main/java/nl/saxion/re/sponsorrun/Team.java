package nl.saxion.re.sponsorrun;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team {
    private final StringProperty school;
    private final StringProperty teamName;

    public Team(String school, String teamName) {
        this.school = new SimpleStringProperty(school);
        this.teamName = new SimpleStringProperty(teamName);
    }

    public StringProperty schoolProperty() {
        return school;
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }
}
