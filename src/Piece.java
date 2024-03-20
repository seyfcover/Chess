import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    protected String color;
    protected int row;
    protected int column;

    protected Board board;

    protected boolean moveOn;

    protected boolean isThread;

    public Piece(String color, int row, int column, Board board, boolean moveOn, boolean isThread) {
        this.color = color;
        this.row = row;
        this.column = column;
        this.board = board;
        this.moveOn = moveOn;
        this.isThread = isThread;
    }

    public abstract String getSymbol();

    public abstract ArrayList<String> calculate_possible_moves();


    // Getters and setters for row and column
    public int getRow() {

        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getColor() {
        return color;
    }

    public boolean getMoveOn() {
        return moveOn;
    }

    public void setMoveOn(boolean moveOn) {
        this.moveOn = moveOn;
    }

    public boolean isValidSquare(int row, int column) {
        // Eğer satır ve sütun tahtanın dışına çıkarsa, bu kare geçersizdir.
        return row >= 0 && row < board.getSquares().length && column >= 0 && column < board.getSquares()[0].length;
    }

    public boolean isThread() {
        ArrayList<String> moves = this.calculate_possible_moves();
        for (String move : moves) {
            String[] tokens = move.split(",");
            int moveRow = Integer.parseInt(tokens[0]);
            int moveCol = Integer.parseInt(tokens[1]);
            Piece piece = board.getPieces(moveRow, moveCol);
            // Eğer bu hamlede bir taş varsa ve bu taş bir tehditse, true döndür
            if (piece != null && (Objects.equals(piece.getSymbol(), "\u2654") || Objects.equals(piece.getSymbol(), "\u265A"))) {
                System.out.println("\n I AM DANGER!");
                return true;
            }
        }
        // Hiçbir tehdit yoksa, false döndür
        return false;
    }
}
