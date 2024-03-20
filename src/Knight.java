import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(String color, int row, int column, Board board, boolean moveOn,boolean isThread) {
        super(color, row, column, board, moveOn,isThread);
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "\u2658" : "\u265E";
    }


    @Override
    public ArrayList<String> calculate_possible_moves() {
        ArrayList<String> movements = new ArrayList<>();

        int[][] possibleMoves = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] move : possibleMoves) {
            int nextRow = getRow() + move[0];
            int nextColumn = getColumn() + move[1];

            if (isValidSquare(nextRow, nextColumn) && (board.getPieces(nextRow, nextColumn) == null || !board.getPieces(nextRow, nextColumn).getColor().equals(color))) {
                movements.add(nextRow + "," + nextColumn);
            }
        }

        return movements;
    }
}
