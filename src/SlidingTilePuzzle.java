
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SlidingTilePuzzle {

  private LinkedList<Tile> pieces = new LinkedList<>();
  private final Tile[][] board;
  private final List<Integer> range;

  SlidingTilePuzzle(int size){
    IntStream.rangeClosed(1, size*size).forEachOrdered(operand -> pieces.add(new Tile(operand)));
    board = new Tile[size][size];
    range = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList());
    build();
  }

  private void build(){
    pieces.forEach(tile -> {
      int id = (tile.id().intValue() -1 );
      int row = id/ board.length;
      int col = row > 0 ? id % (row * board[0].length) : id;
      board[row][col] = tile;
    });
  }

  Optional<Tile> getTileId(int row, int col){
    if (!range.contains(row) || !range.contains(col) ){
      return Optional.empty();
    }
    //convert to array values
    row = row -1;
    col = col -1;
    return Optional.ofNullable(board[row][col]);
  }

  private boolean canTileMoveTo(Tile tile, Tile.Position move){
    Tile.Position position = tile.position();
    if (getTileId(move).isPresent() || position.equals(move) || tile.frozen()){
      return false;
    }
    if (position.row().equals(move.row())){
      return position.col() == move.col() -1 || position.col() == move.col()+1;
    }
    if(position.col().equals(move.col())){
      return position.row() == move.row() - 1 || position.row() == move.row() + 1;
    }
    return false;
  }

  boolean moveTileTo(Tile tile, Tile.Position move){
    if (canTileMoveTo(tile, move)){
      board[tile.position().row()][tile.position().col()] = null;
      board[move.row()][move.col()] = tile;
      tile.newPosition(move);
    }
    return false;
  }

  private Optional<Tile> getTileId(Tile.Position move) {
    return getTileId(move.row(), move.col());
  }

  public static void main(String[] args){
    SlidingTilePuzzle slidingTilePuzzle = new SlidingTilePuzzle(4);
  }


  void changeState(BoardState state){

  }


  private class BoardState {

    final Tile[][] state;
    final BoardState parent;
    final BoardState[] children;
    int weight;
    boolean traversed = false;

    BoardState(BoardState parent,  Tile[][] state){
      this.state = state;
      this.parent = parent;
      children = spawn();
    }

    private BoardState[] spawn() {
      BoardState[] fetus =  gestate();
      calculateWeight(fetus);
      return fetus;
    }

    private BoardState[] gestate() {
      BoardState[] fetus = new BoardState[4];
      Tile empty = Arrays.stream(state).flatMap(Arrays::stream).filter(Tile::isEmptyTile).findFirst().get();
      Tile.Position emptyPosition = empty.position();

      if (emptyPosition.row() > 1) {
        Tile t = state[emptyPosition.row() - 1][emptyPosition.col()];
        Tile.Position tilePosition = t.position();
        t.newPosition(emptyPosition);
        empty.newPosition(tilePosition);
      }

     return new BoardState[0];
    }

    private void calculateWeight(BoardState[] fetus) {
      weight = 0;
    }

  }
}
