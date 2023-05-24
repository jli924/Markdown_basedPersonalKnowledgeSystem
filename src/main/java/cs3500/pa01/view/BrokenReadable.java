package cs3500.pa01.view;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * To test that an IOException is thrown
 */
public class BrokenReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("I am broken");
  }
}

