package chessgame;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

interface GameObserver {
    void onPieceMoved(Position from, Position to, Piece piece);

    void onGameOver(Player winner);
}

interface GameState {
    void handleMove(Position from, Position to);

    void nextTurn();
}

class NormalPlayState implements GameState {

    @Override
    public void handleMove(Position from, Position to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMove'");
    }

    @Override
    public void nextTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextTurn'");
    }
    /* ... */ }

class CheckState implements GameState {

    @Override
    public void handleMove(Position from, Position to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMove'");
    }

    @Override
    public void nextTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextTurn'");
    }
    /* ... */ }

class CheckmateState implements GameState {

    @Override
    public void handleMove(Position from, Position to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMove'");
    }

    @Override
    public void nextTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextTurn'");
    }
    /* ... */ }

class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        board.startGame();
    }
}

class Position {
    int row;
    int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

enum Color {
    WHITE,
    BLACK;
}

interface Piece {
    // check if the move is valid
    Color getColor();

    boolean isValidMove(Position from, Position to);

    Position getPosition();

    void setPosition(Position position);
}

class Rook implements Piece {
    Position position;
    Color color;

    Rook(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return from.row == to.row || from.col == to.col;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class Knight implements Piece {
    Position position;
    Color color;

    Knight(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return (Math.abs(from.row - to.row) == 2 && Math.abs(from.col - to.col) == 1) ||
                (Math.abs(from.row - to.row) == 1 && Math.abs(from.col - to.col) == 2);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class Bishop implements Piece {
    Position position;
    Color color;

    Bishop(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return Math.abs(from.row - to.row) == Math.abs(from.col - to.col);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class Queen implements Piece {
    Position position;
    Color color;

    Queen(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return Math.abs(from.row - to.row) == Math.abs(from.col - to.col) || from.row == to.row || from.col == to.col;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class King implements Piece {
    Position position;
    Color color;

    King(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return Math.abs(from.row - to.row) <= 1 && Math.abs(from.col - to.col) <= 1;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class Pawn implements Piece {
    Position position;
    Color color;

    Pawn(Position position, Color color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return from.row == to.row && Math.abs(from.col - to.col) == 1;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}

class Player {
    String name;
    Color color;

    Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }
}

class Board {

    Map<Position, Piece> Player1Pieces, Player2Pieces;
    Deque<Player> players;
    Player winner;

    Board() {
        addPlayers();
        setupBoard();
    }

    void addPlayers() {
        players = new LinkedList<>();
        players.add(new Player(Color.WHITE, "Player1"));
        players.add(new Player(Color.BLACK, "Player2"));
    }

    void setupBoard() {
        Player1Pieces = new HashMap<>();
        Player2Pieces = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            Player1Pieces.put(new Position(0, i), new Rook(new Position(0, i), Color.WHITE));
            Player1Pieces.put(new Position(1, i), new Pawn(new Position(1, i), Color.WHITE));
            Player2Pieces.put(new Position(7, i), new Rook(new Position(7, i), Color.BLACK));
            Player2Pieces.put(new Position(6, i), new Pawn(new Position(6, i), Color.BLACK));
        }
        Player1Pieces.put(new Position(0, 2), new Bishop(new Position(0, 2), Color.WHITE));
        Player1Pieces.put(new Position(0, 5), new Bishop(new Position(0, 5), Color.WHITE));
        Player2Pieces.put(new Position(7, 2), new Bishop(new Position(7, 2), Color.BLACK));
        Player2Pieces.put(new Position(7, 5), new Bishop(new Position(7, 5), Color.BLACK));

        Player1Pieces.put(new Position(0, 1), new Knight(new Position(0, 1), Color.WHITE));
        Player1Pieces.put(new Position(0, 6), new Knight(new Position(0, 6), Color.WHITE));
        Player2Pieces.put(new Position(7, 1), new Knight(new Position(7, 1), Color.BLACK));
        Player2Pieces.put(new Position(7, 6), new Knight(new Position(7, 6), Color.BLACK));

        Player1Pieces.put(new Position(0, 3), new Queen(new Position(0, 3), Color.WHITE));
        Player2Pieces.put(new Position(7, 3), new Queen(new Position(7, 3), Color.BLACK));

        Player1Pieces.put(new Position(0, 4), new King(new Position(0, 4), Color.WHITE));
        Player2Pieces.put(new Position(7, 4), new King(new Position(7, 4), Color.BLACK));

    }

    void startGame() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Chess Game Started!");
        printBoard();

        while (winner == null) {
            Player currentPlayer = players.poll();
            System.out.println("\n" + currentPlayer.name + "'s turn (" + currentPlayer.color + ")");

            // Get the from position
            System.out.print("Enter the row and column of the piece to move (e.g., 0 3): ");
            int fromRow = scanner.nextInt();
            int fromCol = scanner.nextInt();
            Position from = new Position(fromRow, fromCol);

            // Check if the position has a piece and belongs to the current player
            if ((currentPlayer.color == Color.WHITE && !Player1Pieces.containsKey(from)) ||
                    (currentPlayer.color == Color.BLACK && !Player2Pieces.containsKey(from))) {
                System.out.println("Invalid piece selection. Try again.");
                players.addFirst(currentPlayer);
                continue;
            }

            // Get the to position
            System.out.print("Enter the row and column to move to (e.g., 2 3): ");
            int toRow = scanner.nextInt();
            int toCol = scanner.nextInt();
            Position to = new Position(toRow, toCol);

            // Attempt to move the piece
            Position oldFrom = from;
            movePiece(currentPlayer, from, to);

            // Check if the move was successful
            boolean moveMade = false;
            if (currentPlayer.color == Color.WHITE) {
                moveMade = !Player1Pieces.containsKey(oldFrom);
            } else {
                moveMade = !Player2Pieces.containsKey(oldFrom);
            }

            if (!moveMade) {
                System.out.println("Invalid move. Try again.");
                players.addFirst(currentPlayer);
                continue;
            }

            // Successful move
            printBoard();

            // Check for winner
            if (winner != null) {
                System.out.println("\nGame Over! " + winner.name + " wins!");
                break;
            }

            // Add the player back to the end of the queue
            players.addLast(currentPlayer);
        }

        scanner.close();
    }

    // Helper method to print the chess board
    private void printBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                if (Player1Pieces.containsKey(pos)) {
                    printPiece(Player1Pieces.get(pos));
                } else if (Player2Pieces.containsKey(pos)) {
                    printPiece(Player2Pieces.get(pos));
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // Helper method to print a chess piece
    private void printPiece(Piece piece) {
        char symbol = '?';

        if (piece instanceof King)
            symbol = 'K';
        else if (piece instanceof Queen)
            symbol = 'Q';
        else if (piece instanceof Rook)
            symbol = 'R';
        else if (piece instanceof Bishop)
            symbol = 'B';
        else if (piece instanceof Knight)
            symbol = 'N';
        else if (piece instanceof Pawn)
            symbol = 'P';

        if (piece.getColor() == Color.WHITE) {
            System.out.print(symbol + " ");
        } else {
            System.out.print(Character.toLowerCase(symbol) + " ");
        }
    }
}
