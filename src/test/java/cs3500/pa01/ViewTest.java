package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Model.Question;
import cs3500.pa01.Model.UserData;
import cs3500.pa01.View.View;
import org.junit.jupiter.api.Test;

/**
 * Tests for the View class
 */
public class ViewTest {
  Appendable appendable = new StringBuilder();
  View view = new View(appendable);
  Question question =
      new Question("What is the color of the sky?", "Blue.", true);
  UserData stats = new UserData(1, 2, 3, 4, 5);
  String welcomeUser = "Welcome!!"
      + "\n"
      + "How many questions would you like to study today?\n";

  String options = "Easy(e)  Hard(h)  Show Answer(a)  Exit(x)\n";
  String showStats = "Good Job!" + "\n"
      + "You answered " + 1 + " questions!" + "\n"
      + 2 + " questions went from easy to hard." + "\n"
      + 3 + " questions went from hard to easy." + "\n"
      + "Current counts in question bank:" + "\n"
      + "Hard questions: " + 4  + "\n"
      + "Easy questions: " + 5 + "\n";

  String askForFilePath = "Please provide a valid file path.\n";

  /**
   * Tests for the welcomeUser method in View
   */
  @Test
  public void testProvideFile() {
    view.askForFilePath();
    assertEquals(askForFilePath, view.output.toString());
  }

  /**
   * Tests for the welcomeUser method in View
   */
  @Test
  public void testWelcomeUser() {
    view.welcomeUser();
    assertEquals(welcomeUser, view.output.toString());
  }

  /**
   * Tests for the showOptions method in View
   */
  @Test
  public void testShowOptions() {
    view.showOptions();
    assertEquals(options, view.output.toString());
  }

  /**
   * Tests for the showAnswer method in View
   */
  @Test
  public void testShowAnswer() {
    view.showAnswer(question);
    assertEquals(question.answer, view.output.toString());
  }

  /**
   * Tests for the showStats method in View
   */
  @Test
  public void testShowStats() {
    view.showStats(stats);
    assertEquals(showStats, view.output.toString());
  }
}
