package cs3500.pa01;

import java.io.IOException;
import java.util.Objects;

public class Writer {

  private final Appendable appendable;

  public Writer(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  public void write(String phrase) {
    try {
      appendable.append(phrase);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
