import java.util.ArrayList;
import java.util.Objects;

public class King extends Piece implements Castling {
    public King(String color, int row, int column, Board board, boolean moveOn, boolean isThread) {
        super(color, row, column, board, moveOn, isThread);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "\u2654" : "\u265A";
    }

    @Override
    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movements = new ArrayList<>();

        int[][] possibleMoves = {
                {1, 0}, {-1, 0},
                {1, 1}, {1, -1},
                {-1, 1}, {-1, -1},
                {0, -1}, {0, 1}
        };
        for (int[] move : possibleMoves) {
            int nextRow = getRow() + move[0];
            int nextColumn = getColumn() + move[1];

            if (isValidSquare(nextRow, nextColumn) && (board.getPieces(nextRow, nextColumn) == null || !board.getPieces(nextRow, nextColumn).getColor().equals(color))) {
                movements.add(nextRow + "," + nextColumn);
            }
        }
        if (castling() == 2 || castling() == 4) {
            movements.add(getRow() + "," + (getColumn() + 2));
        }
        if (castling() == 1 || castling() == 3) {
            movements.add(getRow() + "," + (getColumn() - 2));
        }
        return movements;
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