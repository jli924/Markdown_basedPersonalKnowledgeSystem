package cs3500.pa01.View;

import cs3500.pa01.Model.Question;
import cs3500.pa01.Model.UserData;
import java.io.IOException;

/**
 * Handles what to show the user
 */
public class View {
  public Appendable output;

  /**
   * Constructor
   */
  public View(Appendable output) {
    this.output = output;
  }

  public void askForFilePath() {
    try {
      output.append("Please provide a valid file path.\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Welcomes the user
   */
  public void welcomeUser() {
    try {
      output.append("Welcome!!"
          + "\n"
          + "How many questions would you like to study today?\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Shows the user a question
   */
  public void showQuestion(Question question) {
    try {
      output.append(question.question + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Shows the user options
   */
  public void showOptions() {
    try {
      output.append("Easy(e)  Hard(h)  Show Answer(a)  Exit(x)\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Shows the user the answer to a question if they so choose
   *
   * @param question the question they want the answer to
   */
  public void showAnswer(Question question) {
    try {
      output.append(question.answer + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Shows the user their stats at the end of the study session
   *
   * @param stats their stats
   */
  public void showStats(UserData stats) {
    try {
      output.append("Good Job!" + "\n"
          + "You answered " + stats.answered + " questions!" + "\n"
          + stats.easyToHard + " questions went from easy to hard." + "\n"
          + stats.hardToEasy + " questions went from hard to easy." + "\n"
          + "Current counts in question bank:" + "\n"
          + "Hard questions: " + stats.hardQs  + "\n"
          + "Easy questions: " + stats.easyQs + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
