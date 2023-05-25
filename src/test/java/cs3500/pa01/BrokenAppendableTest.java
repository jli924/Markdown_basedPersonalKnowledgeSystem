package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Tests for the BrokenAppendable class (which was made for testing),
 * but I need jacoco coverage...
 */
public class BrokenAppendableTest {
  Appendable brokenAppendable = new BrokenAppendable();

  /**
   * Tests for the methods append(char) and append(char, int, int)
   * which were never used in my code, but need to test for jacoco.
   */
  @Test
  public void testAppendChar() {
    assertThrows(IOException.class, () -> brokenAppendable.append("b"));
    assertThrows(IOException.class, () -> brokenAppendable.append("hello", 1, 1));
  }
}
