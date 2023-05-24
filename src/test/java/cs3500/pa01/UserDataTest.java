package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.UserData;
import org.junit.jupiter.api.Test;

/**
 * Tests for the UserData class
 */
public class UserDataTest {
  UserData stats = new UserData(1, 2, 3, 4, 5);
  String userStats = "{ User Stats } \n"
      + "> Questions answered: " + 1 + "\n"
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

  /**
   * Tests for the updateEasyQs method in UserData
   */
  @Test
  public void testUpdateEasyQs() {
    stats.updateEasyQs(3);
    assertEquals(3, stats.easyQs);
  }

  /**
   * Tests for the updateHardQs method in UserData
   */
  @Test
  public void testUpdateHardQs() {
    stats.updateHardQs(3);
    assertEquals(3, stats.hardQs);
  }

  /**
   * Tests for the increaseHardToEasy method in UserData
   */
  @Test
  public void testIncreaseHardToEasy() {
    stats.increaseHardToEasy();
    assertEquals(4, stats.hardToEasy);
  }

  /**
   * Tests for the increaseEasyToHard method in UserData
   */
  @Test
  public void testIncreaseEasyToHard() {
    stats.increaseEasyToHard();
    assertEquals(3, stats.easyToHard);
  }

  /**
   * Tests for the increaseAnswered method in UserData
   */
  @Test
  public void testIncreaseAnswered() {
    stats.increaseAnswered();
    assertEquals(2, stats.answered);
  }
}
