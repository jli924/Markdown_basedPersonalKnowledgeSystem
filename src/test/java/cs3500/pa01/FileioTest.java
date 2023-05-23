package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Model.FileIo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileIO class
 */
public class FileioTest {
  Driver driver = new Driver("", "filename", "");
  FileIo fio = new FileIo();
  static File vectors;
  static File arrays;
  ArrayList<String> acontents;
  ArrayList<String> vcontents;
  ArrayList<ArrayList<String>> allcontents;
  ArrayList<String> contents;

  /**
   * Initialize data
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
    acontents = new ArrayList<>(Arrays.asList("# Something really important\n",
        "- [[really important]] not important\n", "- [not important] \n",
        "- [[read me!]] but don't read me\n", "## Hey I'm also important!\n",
        "- but I'm not.\n", "- [[but I am!]]\n"));
    vcontents = new ArrayList<>(List.of("# Hello\n"));
    allcontents = new ArrayList<>(Arrays.asList(acontents, vcontents));
    contents = new ArrayList<>(Arrays.asList("# Something really important\n",
        "- [[really important]] not important\n", "\n", "- [not important] \n", "\n",
        "- [[read me!]] but don't read me\n", "\n", "\n", "## Hey I'm also important!\n",
        "- but I'm not.\n", "\n", "- [[but I am!]]\n", "\n", "\n", "# Hello\n"));
  }

  @Test
  public void testFileContents() throws FileNotFoundException {
    initData();
    fio.writeToFile(arrays, driver.listToFormattedString(allcontents));
    fio.writeToFile(vectors, driver.listToFormattedString(allcontents));
    assertEquals(contents, fio.fileContents(arrays));
    assertEquals(contents, fio.fileContents(vectors));
  }

  @Test
  public void testWriteToFile() throws FileNotFoundException {
    File file;
    try {
      file = File.createTempFile("writetome", ".md",
          new File("src/test/resources/tempFiles"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    fio.writeToFile(file, "Hi there");
    ArrayList<String> contents = new ArrayList<>(List.of("Hi there" + "\n"));
    assertEquals(contents, fio.fileContents(file));
  }
}
