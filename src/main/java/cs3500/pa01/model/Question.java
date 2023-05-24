package cs3500.pa01.model;

/**
 * To represent a question
 */
public class Question {
  public String question;
  public String answer;
  // difficulty is (H) or (E) in a .sr file
  private boolean hard;

  /**
   * Constructor
   *
   * @param question the question as a string
   * @param answer the answer as a string
   * @param hard whether the question is hard
   */
  public Question(String question, String answer, boolean hard) {
    this.question = question;
    this.answer = answer;
    this.hard = hard;
  }

  /**
   * Converts a question back to a string to write back to the .sr file
   *
   * @return a string with the formatted question, including difficulty
   */
  public String toString() {
    if (hard) {
      return "[[" + question + " ::: " + answer + " (H)]]\n";
    } else {
      return "[[" + question + " ::: " + answer + " (E)]]\n";
    }
  }

  /**
   * Sets a question's difficulty to easy (false)
   */
  public void setToEasy() {
    this.hard = false;
  }

  /**
   * Sets a question's difficulty to hard (true)
   */
  public void setToHard() {
    this.hard = true;
  }

  /**
   * Returns true if a question is hard
   */
  public boolean isHard() {
    return hard;
  }
}
