package cs3500.pa01;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a reader to read user input
 */
public class Reader {
  private final Readable readable;
  private final String escapeSequence;

  /**
   * Constructor
   */
  public Reader(Readable readable, String escapeSequence) {
    this.readable = Objects.requireNonNull(readable);
    this.escapeSequence = Objects.requireNonNull(escapeSequence);
  }

  /**
   * Reads user input
   */
  public String read() {
    Scanner scanner = new Scanner(readable);

    StringBuilder output = new StringBuilder();

    while (scanner.hasNextLine() && !scanner.hasNext(escapeSequence)) {
      output.append(scanner.nextLine());
    }

    return output.toString();
  }
}
