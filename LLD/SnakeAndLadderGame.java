import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

// player
class Player{

    String name;
    private int position = 0;
    public Player(String name) {
        this.name = name;
        this.position = 0;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
}

// dice
class Dice{
    int diceCount = 1; 
    int total = 0;
    final int DICE_MAX = 6;
    final int DICE_MIN = 1;
    public Dice(int count) {
        this.diceCount = count;
    }

    int roll(){
        for (int i = 0; i < diceCount; i++) {
            int value = ThreadLocalRandom.current().nextInt(DICE_MIN, DICE_MAX+1);
                        total += value;
        }
        return total;
    }
}

// cell
class Cell{
    private Jump jump;
    public void setJump(Jump jump) {
        this.jump = jump;
    }
    public Jump getJump() {
        return jump;
    }
}



class Jump{
    /* for ladder start < end & for snake start > end */
    int start;
    int end;
    EntityType type;
    public Jump(int start, int end, EntityType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }
}

// board
class Board{
    Cell[][] cells;

    

    void setupBoard(int size, int numSnakes, int numLadders){
        cells = new Cell[size][size];
        // loop to create cells
        for(int i=0; i< size;i++){
            for(int j=0; j<size;j++){
                cells[i][j] = new Cell();
            }
        }
        addSnakes(size, numSnakes);
        addLadders(size, numLadders);
    }

    private void addSnakes(int size,int numSnakes){
        while(numSnakes>0){
            int start = ThreadLocalRandom.current().nextInt(size*size-1);
            int end = ThreadLocalRandom.current().nextInt(size*size-1);
            if (start<=end) continue;
            getCell(start).setJump(new Jump(start, end, EntityType.SNAKE));
            numSnakes--;
        }
    }

    private void addLadders(int size, int numLadders){
        while(numLadders>0){
            int start = ThreadLocalRandom.current().nextInt(size*size-1);
            int end = ThreadLocalRandom.current().nextInt(size*size-1);
            if (start>=end) continue;
            getCell(start).setJump(new Jump(start, end, EntityType.LADDER));
            numLadders--;
        }
    }
    
    public Cell getCell(int startPosition){
        int row = startPosition/10;
        int col = startPosition%10;
        return cells[row][col];
    }

}
// game
class SnakeAndLadderGame{

    Player winner;
    Deque<Player> players;
    Board board;
    Dice dice;


    public SnakeAndLadderGame() {
        players = new LinkedList<>();
        board = new Board();
    }

    public void init(int boardSize, int playerCount, int numSnakes, int numLadders, int diceCount){
        board.setupBoard(boardSize, numSnakes, numLadders);
        dice = new Dice(diceCount);
        addPlayers(playerCount);
    }

    private void addPlayers(int playerCount){
        for(int i=1; i<=playerCount; i++){
            players.add(new Player("PLAYER "+i));
        }
    }

    public void start(){
        while(winner == null){
            Player player = getNextPlayer();
            System.out.println(player.name+" playing at position "+player.getPosition());
            int diceValue = dice.roll();
            System.out.println(player.name+" rolled "+diceValue);
            
            // move player
            int newPosition = player.getPosition() + diceValue;
            newPosition = moveAndCheck(newPosition);
            player.setPosition(newPosition);
            
            renderBoard();
            if (newPosition >= board.cells.length*board.cells.length){
                winner = player;
                System.out.println(player.name+" won the game");
            }
        }
    }

    private int moveAndCheck(int playerPosition){
        if(playerPosition >= board.cells.length*board.cells.length){
            return playerPosition;
        }
        Cell cell = board.getCell(playerPosition);
        if (cell.getJump() != null && cell.getJump().start == playerPosition){
            Jump jump = cell.getJump();
            if (jump.type == EntityType.LADDER){
                System.out.println(players.getLast().name+" FOUND LADDER "+jump.start+" to "+jump.end);
            }else{
                System.out.println(players.getLast().name+" SNAKE BITE "+jump.start+" to "+jump.end);
            }
            return jump.end;
        }
        return playerPosition;
    }

    private Player getNextPlayer(){
        Player player = players.removeFirst();
        players.addLast(player);
        return player;
    }

    public void renderBoard(){
        // render in a 2D grid using Unicode characters
        for(int i=0; i<board.cells.length; i++){
            for(int j=0; j<board.cells.length; j++){
                Cell cell = board.cells[i][j];
                // show player as [1] or [2] or [3] and ladder as [L] and snake as [S] and empty cell as [ ]
                if (cell.getJump() != null){
                    if (cell.getJump().type == EntityType.LADDER){
                        System.out.print("[L]");
                    }else{
                        System.out.print("[S]");
                    }
                }else{
                    System.out.print("[ ]");
                }
                

            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        SnakeAndLadderGame game = new SnakeAndLadderGame();
        game.init(10, 2,5, 10, 1);
        game.start();
    }
    
}

