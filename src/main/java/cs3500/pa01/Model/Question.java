package cs3500.pa01.Model;

/**
 * To represent a question
 */
public class Question {
  public String question;
  public String answer;
  // difficulty is (H) or (E)
  String difficulty;
  boolean hard;

  /**
   * Constructor
   */
  public Question(String question, String answer, boolean hard) {
    this.question = question;
    this.answer = answer;
    this.hard = hard;
  }
}
