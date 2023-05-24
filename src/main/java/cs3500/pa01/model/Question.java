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
   */
  public Question(String question, String answer, boolean hard) {
    this.question = question;
    this.answer = answer;
    this.hard = hard;
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
