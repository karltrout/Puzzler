import static org.junit.Assert.*;

import org.junit.Test;

public class SlidingTilePuzzleTest {

  @Test
  public void buildPuzzle(){
    SlidingTilePuzzle slidingTilePuzzle = new SlidingTilePuzzle(4);
    assertNotNull(slidingTilePuzzle);
    assertTrue(slidingTilePuzzle.getTileId(1,1).isPresent());
    assertFalse(slidingTilePuzzle.getTileId(4, 4).isPresent());
    assertTrue(slidingTilePuzzle.getTileId(4,3).isPresent());
  }
}