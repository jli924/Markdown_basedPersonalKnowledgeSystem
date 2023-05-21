package cs3500.pa01;

public class Question {
  String question;
  String answer;
  // (H) or (E)
  String difficulty;
  boolean hard;
  public Question(String q, String a, boolean h) {
    question = q;
    answer = a;
    hard = h;
  }
}
