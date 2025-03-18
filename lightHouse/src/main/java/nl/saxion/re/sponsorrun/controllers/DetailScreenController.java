package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nl.saxion.re.sponsorrun.data.Data;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class DetailScreenController {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtParty;

    @FXML
    protected void initialize() {
        txtFirstName.setText(Data.selectedPolitician.firstName);
        txtLastName.setText(Data.selectedPolitician.lastName);
        txtParty.setText(Data.selectedPolitician.politicalParty);
    }
    @FXML
    protected void onCancelClick() {
        WindowHelper.closeWindow(txtFirstName);
    }

    @FXML
    protected void onOkClick() {
        // update fields of the selected politician
        Data.selectedPolitician.firstName = txtFirstName.getText();
        Data.selectedPolitician.lastName = txtLastName.getText();
        Data.selectedPolitician.politicalParty = txtParty.getText();

        WindowHelper.closeWindow(txtFirstName);
    }
}
