package battleship.views.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 11/24/2019
 * This class is the interpreter for the event bus and the shipselectionview.
 * This class will define the protocol behind what happens when an event is
 * meant to be thrown to the ship selection view.
 */

import battleship.tools.events.*;
import battleship.tools.Listener;
import battleship.views.ShipSelectionView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ShipSelectionViewInterpreter implements Listener {

    private final ShipSelectionView shipSelectionView;

    public ShipSelectionViewInterpreter(ShipSelectionView shipSelectionView) {
        this.shipSelectionView = shipSelectionView;
    }

    @Override
    public void catchEvent(Object _event) {

        if(_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for(Node curNode : this.shipSelectionView.getShipSelectionPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if((rowIndex == row) && (columnIndex == column)) {
                    if(!("grid".equals(newId))) {
                        this.shipSelectionView.removeShipSelectionPaneGridEvents(curNode);
                        this.shipSelectionView.setShipSelectionPaneShipEvents(curNode);
                    } else {
                        this.shipSelectionView.removeShipSelectionPaneShipEvents(curNode);
                        this.shipSelectionView.setShipSelectionPaneGridEvents(curNode);
                    }
                    curNode.setId(newId);
                    curNode.setRotate(rotation);
                    return;
                }
            }
        }

        if(_event instanceof ClearGridEvent) {
            this.shipSelectionView.getShipSelectionPane().getChildren().forEach(child -> {
                child.setId("grid");
                this.shipSelectionView.removeShipSelectionPaneShipEvents(child);
                this.shipSelectionView.setShipSelectionPaneGridEvents(child);
            });
        }
    }

}
