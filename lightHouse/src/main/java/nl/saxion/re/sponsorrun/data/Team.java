package nl.saxion.re.sponsorrun.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team {
    private final StringProperty school;
    private final StringProperty teamName;
    private final StringProperty contactPerson;
    private final StringProperty phoneNumber;
    private final StringProperty address;

    public Team(String school, String teamName, String contactPerson, String phoneNumber, String address) {
        this.school = new SimpleStringProperty(school);
        this.teamName = new SimpleStringProperty(teamName);
        this.contactPerson = new SimpleStringProperty(contactPerson);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
    }

    public String getSchool() {
        return school.get();
    }

    public String getTeamName() {
        return teamName.get();
    }

    public String getContactPerson() {
        return contactPerson.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty schoolProperty() {
        return school;
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public StringProperty contactPersonProperty() {
        return contactPerson;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty addressProperty() {
        return address;
    }
}
