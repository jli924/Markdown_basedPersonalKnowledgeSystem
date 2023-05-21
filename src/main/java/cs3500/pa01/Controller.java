package cs3500.pa01;

import java.util.Objects;

/**
 * Handles user input & what to output
 */
public class Controller {
  int questionsToStudy;
  private final Readable input;
  private final Appendable output;

  /**
   * Constructor
   */
  public Controller(int qts, Readable input, Appendable output) {
    this.questionsToStudy = qts;
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
  }

  /**
   * To run the
   */
  public void run() {

  }
}
