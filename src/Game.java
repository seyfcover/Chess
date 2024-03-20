public class Game {
    private static final Board board = new Board();

    public Game() {
        start_game();
    }

    public void start_game() {
        System.out.println("Oyun başladı!");
        setup_board();
        board.setup_board();
        board.display_board();
    }


    private void setup_board() {
        // black pieces
        board.add_piece(new Rook("black", 0, 0, board,false,false));
        board.add_piece(new Knight("black", 0, 1, board,false,false));
        board.add_piece(new Bishop("black", 0, 2, board,false,false));
        board.add_piece(new Queen("black", 0, 3, board,false,false));
        board.add_piece(new King("black", 0, 4, board,false,false));
        board.add_piece(new Bishop("black", 0, 5, board,false,false));
        board.add_piece(new Knight("black", 0, 6, board,false,false));
        board.add_piece(new Rook("black", 0, 7, board,false,false));

        for (int i = 0; i < 8; i++) {
            board.add_piece(new Pawn("black", 1, i, board,false,false));
        }
        // white pieces
        board.add_piece(new Rook("white", 7, 0, board,false,false));
        board.add_piece(new Knight("white", 7, 1, board,false,false));
        board.add_piece(new Bishop("white", 7, 2, board,false,false));
        board.add_piece(new Queen("white", 7, 3, board,false,false));
        board.add_piece(new King("white", 7, 4, board,false,false));
        board.add_piece(new Bishop("white", 7, 5, board,false,false));
        board.add_piece(new Knight("white", 7, 6, board,false,false));
        board.add_piece(new Rook("white", 7, 7, board,false,false));

        for (int i = 0; i < 8; i++) {
            board.add_piece(new Pawn("white", 6, i, board,false,false));
        }
    }

    public Board getBoard() {
        return board;
    }
}
