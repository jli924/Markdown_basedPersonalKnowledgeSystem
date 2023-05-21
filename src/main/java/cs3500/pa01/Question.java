package cs3500.pa01;

/**
 * To represent a question
 */
public class Question {
  String question;
  String answer;
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
