import java.util.ArrayList;

import java.util.LinkedHashSet;

public class Queen extends Piece {
    public Queen(String color, int row, int column, Board board, boolean moveOn,boolean isThread) {
        super(color, row, column, board, moveOn,isThread);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "\u2655" : "\u265B";
    }

    @Override
    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movement;
        Bishop b1 = new Bishop(getColor(), row, column, board, getMoveOn(),false);
        movement = b1.calculate_possible_moves();
        King k1 = new King(getColor(), row, column, board, getMoveOn(),false);
        movement.addAll(k1.calculate_possible_moves());
        Rook r1 = new Rook(getColor(), row, column, board, getMoveOn(),false);
        movement.addAll(r1.calculate_possible_moves());
        LinkedHashSet<String> uniqueMovementSet = new LinkedHashSet<>(movement);
        movement = new ArrayList<>(uniqueMovementSet);

        return movement;
    }
}