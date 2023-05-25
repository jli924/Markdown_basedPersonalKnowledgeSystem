package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.model.Question;
import cs3500.pa01.model.UserData;
import cs3500.pa01.view.View;
import org.junit.jupiter.api.Test;

/**
 * Tests for the View class
 */
public class ViewTest {
  Appendable brokenAppendable = new BrokenAppendable();
  View brokenView = new View(brokenAppendable);
  Appendable appendable = new StringBuilder();
  View view = new View(appendable);
  Question question =
      new Question("What is the color of the sky?", "Blue.", true);
  UserData stats = new UserData(1, 2, 3, 4, 5);
  String welcomeUser = "Welcome!!\n"
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
  public void testAskForFilePath() {
    view.askForFilePath();
    assertEquals(askForFilePath, view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.askForFilePath());
  }

  /**
   * Tests for the welcomeUser method in View
   */
  @Test
  public void testWelcomeUser() {
    view.welcomeUser();
    assertEquals(welcomeUser, view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.welcomeUser());
  }

  /**
   * Tests for the showQuestion method in View
   */
  @Test
  public void testShowQuestion() {
    view.showQuestion(question);
    assertEquals(question.question + "\n", view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.showQuestion(question));
  }

  /**
   * Tests for the showOptions method in View
   */
  @Test
  public void testShowOptions() {
    view.showOptions();
    assertEquals(options, view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.showOptions());
  }

  /**
   * Tests for the showAnswer method in View
   */
  @Test
  public void testShowAnswer() {
    view.showAnswer(question);
    assertEquals(question.answer + "\n", view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.showAnswer(question));
  }

  /**
   * Tests for the showStats method in View
   */
  @Test
  public void testShowStats() {
    view.showStats(stats);
    assertEquals(showStats, view.output.toString());
    assertThrows(RuntimeException.class, () -> brokenView.showStats(stats));
  }
}
