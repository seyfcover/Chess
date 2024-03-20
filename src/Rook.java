import java.util.ArrayList;
import java.util.Objects;

public class Rook extends Piece implements Castling {
    public Rook(String color, int row, int column, Board board, boolean moveOn, boolean isThread) {
        super(color, row, column, board, moveOn, isThread);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "\u2656" : "\u265C";
    }


    @Override
    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movement = new ArrayList<>();
        int newRow = row;
        int newColumn = column;

        // İleri doğru hareket
        while (isValidSquare(++newRow, newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!Objects.equals(board.getPieces(newRow, newColumn).getColor(), color)) {
                movement.add(newRow + "," + newColumn);
                break; // Rakip parça varsa, o karenin ötesine gidemez.
            } else {
                break; // Kendi parçasına ulaşıldığında hareket edemez.
            }
        }

        // Geriye doğru hareket
        newRow = row;
        while (isValidSquare(--newRow, newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!Objects.equals(board.getPieces(newRow, newColumn).getColor(), color)) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }

        // Sağa doğru hareket
        newRow = row;
        while (isValidSquare(newRow, ++newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!Objects.equals(board.getPieces(newRow, newColumn).getColor(), color)) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }

        // Sola doğru hareket
        newColumn = column;
        while (isValidSquare(newRow, --newColumn)) {
            if (board.getPieces(newRow, newColumn) == null) {
                movement.add(newRow + "," + newColumn);
            } else if (!(Objects.equals(board.getPieces(newRow, newColumn).getColor(), color))) {
                movement.add(newRow + "," + newColumn);
                break;
            } else {
                break;
            }
        }
        if (castling() == 2 || castling() == 4) {
            movement.add(getRow() + "," + (getColumn() - 2));
        }
        if (castling() == 1 || castling() == 3) {
            movement.add(getRow() + "," + (getColumn() + 3));
        }
        return movement;
    }

    @Override
    public int castling() {
        Piece piece = board.getPieces(row, column);
        if (Objects.equals(piece.color, "white") && !piece.getMoveOn()) {
            if (board.getPieces(row, 1) == null && board.getPieces(row, 2) == null && board.getPieces(row, 3) == null) {
                return 1;
            } else if (board.getPieces(row, 5) == null && board.getPieces(row, 6) == null) {
                return 2;
            }
        }
        // Sağ taraftaki rook'un ve king'in varlığını kontrol et
        else if (Objects.equals(piece.color, "black") && !piece.getMoveOn()) {
            if (board.getPieces(row, 1) == null && board.getPieces(row, 2) == null && board.getPieces(row, 3) == null) {
                return 3;
            } else if (board.getPieces(row, 5) == null && board.getPieces(row, 6) == null) {
                return 4;
            }
        }
        return 0;
    }

}