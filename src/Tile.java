public class Tile {

  private final Number id;
  private boolean frozen;
  private Position position;

  Tile (int id){
    this.id = id;
    frozen = false;
    position = new Position(0 , 0);
  }

  Number id(){
    return id;
  }

  boolean frozen(){
    return frozen;
  }

  public void freeze(){
    frozen = true;
  }

  public Position position(){
    return position;
  }

  public void newPosition(Position position){
    this.position = position;
  }

  boolean isEmptyTile(){
    return id.intValue() == 16;
  }

  class Position {
    final Integer Row;
    final Integer Col;

    Position(Integer t, Integer t1) {
      Row = t;
      Col = t1;
    }

    Integer row(){
      return Row;
    }

    Integer col(){
      return Col;
    }


    @Override
    public boolean equals(Object object){
      if (!(object instanceof Position)) return false;
      Position a = (Position) object;
      return (this.row().intValue() == a.row().intValue() && this.col().intValue() == a.col().intValue());
    }

  }
}
