package cs3500.pa01;

import java.io.IOException;

/**
 * To test IOExceptions (using a broken, fake appendable)
 */
public class BrokenAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("I am broken!");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("I am broken!");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("I am broken!");
  }
}
