import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final String[][] squares;
    private final List<Piece> pieces;

    public Board() {
        squares = new String[8][8];
        pieces = new ArrayList<>();
    }

    public String[][] getSquares() {
        return squares;
    }


    public void setup_board() {
        // Initialize the board with empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = " ";
            }
        }
        // Place pieces on the board
        for (Piece piece : pieces) {
            squares[piece.getRow()][piece.getColumn()] = piece.getSymbol();
        }
    }

    void display_board() {
        System.out.println("    a   b   c  d   e   f  g   h");
        System.out.println("    0   1   2  3   4   5  6   7");
        for (int i = 0; i < 8; i++) {
            //System.out.print(8 - i + "  ");
            System.out.print(i + "  ");
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] == null) {
                    System.out.println(":::");
                } else {
                    System.out.print(" " + squares[i][j] + " ");
                }
            }
            System.out.println("\n");
        }
    }


    public void update_board() {
        // Clear the board
        setup_board();
        // Redraw the pieces
        for (Piece piece : pieces) {
            squares[piece.getRow()][piece.getColumn()] = piece.getSymbol();
        }
    }

    public void move(Piece piece, int targetRow, int targetColumn) {

        if (piece == null) {
            System.out.println("Burda taş yok");
            return;
        }
        // Hedef konumun geçerli olup olmadığını kontrol et
        ArrayList<String> possibleMoves = piece.calculate_possible_moves();
        for (String move : possibleMoves) {
            System.out.println(move);
        }
        String targetPosition = targetRow + "," + targetColumn;

        // Hareket edilebilir konumlar arasında hedef konumu kontrol et
        if (!possibleMoves.contains(targetPosition)) {
            System.out.println("Hedef konum geçerli değil!");
            return;
        }
        // Hedef konum oynanabilirse parçayı hareket ettir
        Piece targetPiece = getPieces(targetRow, targetColumn);
        if (targetPiece != null) {
            remove_piece(targetPiece);
            if (Objects.equals(targetPiece.getSymbol(), "\u2654") || Objects.equals(targetPiece.getSymbol(), "\u265A")) {
                endGame();
            } else {
                piece.setRow(targetRow);
                piece.setColumn(targetColumn);
                piece.setMoveOn(true);
                piece.isThread = piece.isThread();
                update_board();
                display_board();
            }
        } else {
            piece.setRow(targetRow);
            piece.setColumn(targetColumn);
            piece.setMoveOn(true);
            piece.isThread = piece.isThread();
            update_board();
            display_board();
        }
    }

    public void castling(Piece rook, Piece king, boolean position) {
        if (position) {
            //move(king, king.getRow(), (king.getColumn() - 2));
            king.setColumn(king.getColumn()-2);
            rook.setColumn(rook.getColumn()+3);
        } else {
            //move(king, king.getRow(), (king.getColumn() + 2));
            king.setColumn(king.getColumn()+2);
            rook.setColumn(rook.getColumn()-2);
        }
        king.setMoveOn(true);
        rook.setMoveOn(true);
        rook.isThread = rook.isThread();
        update_board();
        display_board();
    }


    public Piece getPieces(int row, int col) {
        for (Piece piece : pieces) {
            if (piece.getRow() == row && piece.getColumn() == col) {
                return piece;
            }
        }
        return null;
    }


    public void add_piece(Piece piece) {
        pieces.add(piece);
    }

    public void remove_piece(Piece piece) {
        pieces.remove(piece);
    }

    public void endGame() {
        System.out.println("oyun bitti");
        System.exit(1);
    }
}
