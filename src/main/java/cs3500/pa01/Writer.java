package cs3500.pa01;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a writer to write the output to the user
 */
public class Writer {

  private final Appendable appendable;

  /**
   * Constructor
   */
  public Writer(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * Writes a given string back to a user
   *
   * @param phrase the string to write
   */
  public void write(String phrase) {
    try {
      appendable.append(phrase);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
