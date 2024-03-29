package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/27/2019
 * BattleShipShipPiece represents a single sector/piece of a ship.
 * This class serves to hold descriptors of a ship piece.
 */

public class BattleShipShipPiece {

    private boolean hit = false;
    private int rowIndex;
    private int columnIndex;
    private int piece;
    private String id;

    public BattleShipShipPiece (int _pieceIndex, int _orientation, String _shipId) {
        this.piece = _pieceIndex;
        this.id = _shipId;
        this.rowIndex = 0;
        this.columnIndex = _pieceIndex;
    }

//*****************     GETTERS     *******************

    public boolean isHit () {
        return this.hit;
    }

    public int getRowIndex () {
        return this.rowIndex;
    }

    public int getColumnIndex () {
        return this.columnIndex;
    }

    public int getPiece () {
        return this.piece;
    }

    public Coordinate getCoordinate () {
        return new Coordinate(this.rowIndex, this.columnIndex);
    }

    public String getId () {
        return this.id;
    }

    public String getFullId () {
        return this.id + this.piece;
    }

//*****************     SETTERS     *******************

    public void setIndexes (int _rowIndex, int _columnIndex) {
        this.setRowIndex(_rowIndex);
        this.setColumnIndex(_columnIndex);
    }

    public void setHit (boolean _hit) {
        this.hit = _hit;
    }

    private void setRowIndex (int _rowIndex) {
        this.rowIndex = _rowIndex;
    }

    private void setColumnIndex (int _columnIndex) {
        this.columnIndex = _columnIndex;
    }

}