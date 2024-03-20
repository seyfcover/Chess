import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {

        Game game = new Game();
        Board board = game.getBoard(); // Game sınıfından tahtayı al
        while (true) {
            keepOn(board);
        }
    }

    public static void keepOn(Board board) {
        Scanner scan = new Scanner(System.in);
        int col, row, descol, destrow;
        // Parçanın mevcut konumunu al
        System.out.println("Parçanın mevcut konumunu seçin (satır sütun):");
        row = scan.nextInt();
        col = scan.nextInt();
        Piece selectedPiece = board.getPieces(row, col);
        if(castle(selectedPiece, board))
            keepOn(board);
        // Parçanın hedef konumunu al
        System.out.println("Hedef konumu seçin (satır sütun):");
        destrow = scan.nextInt();
        descol = scan.nextInt();
        board.move(board.getPieces(row, col), destrow, descol);
    }

    public static boolean castle(Piece selectedPiece, Board board) {
        if (selectedPiece instanceof Rook) {
            Rook rook = (Rook) selectedPiece; // Rook nesnesine dönüştür
            Scanner scan = new Scanner(System.in);
            if (rook.castling() != 0) {
                System.out.println("Caslting ?");
                boolean answer = scan.nextBoolean();
                if (answer)
                    switch (rook.castling()) {
                        case 1 -> {
                            board.castling(selectedPiece, board.getPieces(7, 4), true);
                            return true;
                        }
                        case 2 -> {
                            board.castling(selectedPiece, board.getPieces(7, 4), false);
                            return  true;
                        }
                        case 3 -> {
                            board.castling(selectedPiece, board.getPieces(0, 4), true);
                            return true;
                        }
                        case 4 -> {
                            board.castling(selectedPiece, board.getPieces(0, 4), false);
                            return true;
                        }
                        default -> {
                        }
                    }
            }
        }
        return false;
    }
}
