package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.model.FileIo;
import cs3500.pa01.model.MarkDownFileVisitor;
import cs3500.pa01.orderingflag.Modified;
import cs3500.pa01.orderingflag.OrderingFlag;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the Driver class
 */
class DriverTest {
  OrderingFlag of;
  Path dir;
  File output;
  Path yourQuestionBank;
  ArrayList<File> files;
  MarkDownFileVisitor mdfv;
  Driver driver;
  FileIo fio;
  ArrayList<String> mtOneFileContents;
  ArrayList<ArrayList<String>> mtcontent;
  ArrayList<String> acontents;
  ArrayList<String> formattedAcontents;
  ArrayList<String> vcontents;
  ArrayList<ArrayList<String>> filecontents;
  ArrayList<ArrayList<String>> formattedcontents;
  String formattedstring;

  @BeforeEach
  public void initData() {
    File dir1 = new File("src/test/resources/tempFiles");
    try {
      if (!dir1.exists()) {
        dir1.mkdirs();
      }
      output = File.createTempFile("writetome", ".md", dir1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    yourQuestionBank = Path.of("README/YourQuestionBank.sr");
    dir = Path.of("src/test/resources/sampleFiles");
    of = new Modified();
    mdfv = new MarkDownFileVisitor(files, of);
    driver = new Driver(dir, of, output.toPath());
    fio = new FileIo();
    files = new ArrayList<>();
    mtOneFileContents = new ArrayList<>();
    mtcontent = new ArrayList<>();
    acontents = new ArrayList<>(Arrays.asList("# Something really important\n",
        "- [[really important]] not important\n", "- [not important] \n",
        "- [[read me!]] but don't read me\n", "## Hey I'm also important!\n",
        "- but I'm not.\n", "- [[but I am!]]\n"));
    formattedAcontents =
        new ArrayList<>(Arrays.asList("# Something really important\n", "- really important", "",
            "- read me!", "## Hey I'm also important!\n", "", "- but I am!"));
    vcontents = new ArrayList<>(List.of("# Hello\n"));
    filecontents = new ArrayList<>(Arrays.asList(acontents, vcontents));
    formattedcontents = new ArrayList<>(Arrays.asList(formattedAcontents, vcontents));
    formattedstring = "# Something really important\n"
        + "- really important\n"
        + "- read me!\n"
        + "\n"
        + "## Hey I'm also important!\n"
        + "- but I am!\n"
        + "\n"
        + "# Hello\n";
  }

  /**
   * To test the generateSrFile method in Driver
   */
  @Test
  public void testGenerateSrFile() {
    initData();
    assertEquals(yourQuestionBank.toFile(), driver.generateSrFile());
  }

  @Test
  public void testToOrderingFlag() {
    initData();
    assertEquals(driver.mod, driver.toOrderingFlag("modified"));
    assertEquals(driver.fn, driver.toOrderingFlag("filename"));
    assertEquals(driver.cr, driver.toOrderingFlag("created"));
    assertThrows(IllegalArgumentException.class,
        () -> driver.toOrderingFlag("i am an ordering flag"));
  }

  @Test
  public void testWalkFileTree() {
    initData();
    Path fake = Path.of("fake path... hehehe");
    driver.walkFileTree(dir);
    assertEquals(files, mdfv.files);
    assertThrows(RuntimeException.class,
        () -> driver.walkFileTree(fake));

  }

  @Test
  public void testListOfFileContents() throws FileNotFoundException {
    initData();
    assertEquals(mtcontent, driver.listOfFileContents());
  }

  @Test
  public void testFormatFilesContents() {
    initData();
    assertEquals(formattedcontents, driver.formatFilesContents(filecontents));
  }

  @Test
  public void testListToString() {
    initData();
    assertEquals(formattedstring, driver.listToFormattedString(formattedcontents));
  }

  @Test
  public void testCreateFinalStudyGuide() throws FileNotFoundException {
    initData();
    driver.createFinalStudyGuide(output.toPath());
    assertEquals(mtOneFileContents, fio.fileContents(output));
  }
}