package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the View class
 */
public class ViewTest {
  View view = new View();
  UserData stats = new UserData(1, 2, 3, 4, 5);
  String welcomeUser = "Welcome!!"
      + "\n"
      + "How many questions would you like to study today?";

  String options = "Easy(e)  Hard(h)  Show Answer(a)  Exit(x)";
  String showStats = "Good Job!" + "\n"
      + "You answered " + 1 + " questions!" + "\n"
      + 2 + " questions went from easy to hard." + "\n"
      + 3 + " questions went from hard to easy." + "\n"
      + "Current counts in question bank:" + "\n"
      + "Hard questions: " + 4  + "\n"
      + "Easy questions: " + 5 + "\n";

  /**
   * Tests for the welcomeUser method in View
   */
  @Test
  public void testWelcomeUser() {
    assertEquals(view.welcomeUser(), welcomeUser);
  }

  /**
   * Tests for the showOptions method in View
   */
  @Test
  public void testShowOptions() {
    assertEquals(view.showOptions(), options);
  }

  /**
   * Tests for the showStats method in View
   */
  @Test
  public void testShowStats() {
    assertEquals(view.showStats(stats), showStats);
  }
}
