package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
public class FXMLPlayController implements Initializable {

    public FXMLPlayController () throws FileNotFoundException {
        this.playControllerLogic = new PlayControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.playControllerLogic.initializeController();
    }

    PlayControllerLogic playControllerLogic;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane menuGridPane;
    @FXML
    private Button singlePlayer;
    @FXML
    private Button mutliPlayer;
    @FXML
    private Button main;

//*****************     SETTERS     *******************

    @FXML
    private void setButtonState(ActionEvent _event) {
        this.playControllerLogic.setButtonState(_event);
    }

//*****************     GETTERS     *******************

    public AnchorPane getMainPane(){
        return this.anchorPane;
    }

    public GridPane getMenuPane() {
        return this.menuGridPane;
    }

    public PlayControllerLogic getLogic(){
        return this.playControllerLogic;
    }

    public Button getSinglePlayerButton () {
        return this.singlePlayer;
    }
    public Button getMainButton () {
        return this.main;
    }

}

