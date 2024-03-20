import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(String color, int row, int column, Board board,boolean moveOn,boolean isThread) {
        super(color, row, column, board,moveOn,isThread);
    }

    @Override
    public String getSymbol() {
        return "\u2659";
    }

    @Override
    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movement = new ArrayList<>();

        if (color.equals("black")) {
            int nextRow = getRow() + 1;
            int nextColumn = getColumn();

            // İleri hareket
            if (nextRow < board.getSquares().length && board.getPieces(nextRow, nextColumn) == null) {
                movement.add(nextRow + "," + nextColumn);

                // İlk harekette iki kare ileri gidebilme
                if (getRow() == 1 && board.getPieces(nextRow + 1, nextColumn) == null && board.getPieces(nextRow, nextColumn) == null) {
                    movement.add((nextRow + 1) + "," + nextColumn);
                }
            }

            // Çaprazda alabilme
            if (nextRow < board.getSquares().length && nextColumn + 1 < board.getSquares()[0].length &&
                    board.getPieces(nextRow, nextColumn + 1) != null) {
                movement.add(nextRow + "," + (nextColumn + 1));
            }

            if (nextRow < board.getSquares().length && nextColumn - 1 >= 0 &&
                    board.getPieces(nextRow, nextColumn - 1) != null) {
                movement.add(nextRow + "," + (nextColumn - 1));
            }
        } else if (color.equals("white")) {
            int nextRow = getRow() - 1;
            int nextColumn = getColumn();

            // İleri hareket
            if (nextRow >= 0 && board.getPieces(nextRow, nextColumn) == null) {
                movement.add(nextRow + "," + nextColumn);

                // İlk harekette iki kare ileri gidebilme
                if (getRow() == 6 && board.getPieces(nextRow - 1, nextColumn) == null && board.getPieces(nextRow, nextColumn) == null) {
                    movement.add((nextRow - 1) + "," + nextColumn);
                }
            }

            // Çaprazda alabilme
            if (nextRow >= 0 && nextColumn + 1 < board.getSquares()[0].length &&
                    board.getPieces(nextRow, nextColumn + 1) != null) {
                movement.add(nextRow + "," + (nextColumn + 1));
            }

            if (nextRow >= 0 && nextColumn - 1 >= 0 &&
                    board.getPieces(nextRow, nextColumn - 1) != null) {
                movement.add(nextRow + "," + (nextColumn - 1));
            }
        }
        return movement;
    }


}