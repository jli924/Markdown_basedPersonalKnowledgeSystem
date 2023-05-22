package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Model.UserData;
import org.junit.jupiter.api.Test;

/**
 * Tests for the UserData class
 */
public class UserDataTest {
  UserData stats = new UserData(1, 2, 3, 4, 5);
  String userStats = "> Questions answered: " + 1 + "\n"
      + "> Questions switched from easy to hard: " + 2 + "\n"
      + "> Questions switched from hard to easy: " + 3 + "\n"
      + "> Hard questions: " + 4 + "\n"
      + "> Easy questions: " + 5;


  /**
   * Tests for the toString method in UserData
   */
  @Test
  public void testToString() {
    assertEquals(userStats, stats.toString());
  }
}
