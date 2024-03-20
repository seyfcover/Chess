import java.util.ArrayList;
import java.util.Objects;

public class Bishop extends Piece {
    public Bishop(String color, int row, int column, Board board, boolean moveOn, boolean isThread) {
        super(color, row, column, board, moveOn, isThread);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "\u2657" : "\u265D";
    }


    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movement = new ArrayList<>();
        int newRow = row;
        int newColumn = column;

        //  çapraz1 doğru hareket
        while (isValidSquare(++newRow, ++newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!(Objects.equals(board.getPieces(newRow, newColumn).getColor(), color))) {
                movement.add(newRow + "," + newColumn);
                break; // Rakip parça varsa, o karenin ötesine gidemez.
            } else {
                break; // Kendi parçasına ulaşıldığında hareket edemez.
            }
        }

        // çapraz2 doğru hareket
        newRow = row;
        newColumn = column;
        while (isValidSquare(--newRow, --newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!(Objects.equals(board.getPieces(newRow, newColumn).getColor(), color))) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }

        // çapraz3 doğru hareket
        newRow = row;
        newColumn = column;
        while (isValidSquare(++newRow, --newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!(Objects.equals(board.getPieces(newRow, newColumn).getColor(), color))) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }

        // çapraz4 doğru hareket
        newRow = row;
        newColumn = column;
        while (isValidSquare(--newRow, ++newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!(Objects.equals(board.getPieces(newRow, newColumn).getColor(), color))) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }
        return movement;
    }
}