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

  public void setToEasy() {
    this.hard = false;
  }

  public void setToHard() {
    this.hard = true;
  }

  public boolean isHard() {
    return hard;
  }
}
