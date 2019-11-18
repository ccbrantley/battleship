package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/11/2019
 * This class is the interpreter for the event bus and the shipselectionview.
 * This class will define the protocol behind what happens when an event is
 * meant to be thrown to the ship selection view.
 */

import battleship.tools.events.*;
import battleship.views.BattleShipGameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BattleShipGameViewInterpreter {
    public BattleShipGameViewInterpreter(BattleShipGameView battleShipGameView) {
        this.battleShipGameView = battleShipGameView;
    }
    private final BattleShipGameView battleShipGameView;

    public void catchEvent(Object _event) {
        if(_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for(Node curNode : this.battleShipGameView.getShipPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if((rowIndex == row) && (columnIndex == column)) {
                    Button newButton = ViewAssets.createGridButton(newId, rotation, "");
                    if(!("grid".equals(newId))) {
                    } else {
                    }
                    this.battleShipGameView.getShipPane().getChildren().remove(curNode);
                     this.battleShipGameView.getShipPane().add(newButton, columnIndex, rowIndex);
                    return;
                }
            }
        }

        if(_event instanceof ClearGridEvent) {
             this.battleShipGameView.getShipPane().getChildren().forEach((curNode) -> {
                Button gridButton = new Button();
                gridButton.setId("grid");
                curNode = gridButton;
            });
        }

        if(_event instanceof RemoveAllRedLedEvent) {
             this.battleShipGameView.getPinPane().getChildren().forEach((curNode) -> {
                 if(curNode.getId().equals("redActive"))
                curNode.setId("blue");
            });
        }
    }

}