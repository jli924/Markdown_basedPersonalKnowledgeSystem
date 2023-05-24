package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.orderingflag.FileName;
import cs3500.pa01.orderingflag.OrderingFlag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileName class
 */
public class FileNameTest {
  OrderingFlag fn;
  File vectors;
  File arrays;
  ArrayList<File> files;
  ArrayList<File> nofiles;
  ArrayList<File> filesSorted;

  /**
   * To initialize data
   */
  @BeforeEach
  public void initData() {
    File dir = new File("src/test/resources/tempFiles");
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }
      arrays = File.createTempFile("arrays", ".md", dir);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      vectors = File.createTempFile("vectors", ".md", dir);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    fn = new FileName();
    files = new ArrayList<>(Arrays.asList(vectors, arrays));
    nofiles = new ArrayList<>();
    filesSorted = new ArrayList<>(Arrays.asList(arrays, vectors));
  }

  @Test
  public void testSort() {
    initData();
    assertEquals(nofiles, fn.sort(nofiles));
    assertEquals(filesSorted, fn.sort(files));
    assertEquals(filesSorted, fn.sort(filesSorted));
  }
}
