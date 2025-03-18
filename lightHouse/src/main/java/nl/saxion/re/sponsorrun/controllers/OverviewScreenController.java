package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import nl.saxion.re.sponsorrun.data.Data;
import nl.saxion.re.sponsorrun.data.Politician;
import nl.saxion.re.sponsorrun.util.TableViewHelper;
import nl.saxion.re.sponsorrun.util.WindowHelper;

public class OverviewScreenController {

    @FXML
    private Label label1;

    @FXML
    private TableView<String[]> table1;

    @FXML
    protected void initialize() {
        // set label to a nice text
        label1.setText("This is a very informative text");

        // prepare the columns of the table
        String[] columns = {"Firstname", "Lastname", "Party"};
        TableViewHelper.prepareTable(table1, columns);

        // add all politicians to the tableview
        for (Politician p : Data.politicians) {
            String[] rowValues = {p.firstName, p.lastName, p.politicalParty};
            table1.getItems().add(rowValues);
        }
    }

    @FXML
    protected void onButtonOkClick() {
        // find which item was selected
        int selectedIndex = table1.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            // no item was selected, show an "error" to the user
            WindowHelper.showAlert("You have to select one item");
        } else {
            // find the selected politician in the data
            Politician selectedPolitician = Data.politicians.get(selectedIndex);
            // set it in the data as public "selectedPolitician", so the edit screen knows which data to edit
            Data.selectedPolitician = selectedPolitician;
            // open the other screen.
            WindowHelper.openWindow("detail-screen.fxml", Data.selectedPolitician.firstName, 400, 300);
            // close this window
            closeWindow();
        }
    }

    @FXML
    protected void onButtonCancelClick() {
        closeWindow();
    }

    private void closeWindow() {
        WindowHelper.closeWindow(label1);
    }
}
