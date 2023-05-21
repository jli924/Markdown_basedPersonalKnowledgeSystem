package cs3500.pa01;

import java.util.Objects;

public class Controller {
  int questionsToStudy;
  private final Readable input;
  private final Appendable output;

  public Controller(int qts, Readable input, Appendable output) {
    this.questionsToStudy = qts;
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
  }

  public void run() {

  }
}
