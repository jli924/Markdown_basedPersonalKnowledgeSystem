package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.MarkDownFileVisitor;
import cs3500.pa01.orderingflag.Created;
import cs3500.pa01.orderingflag.FileName;
import cs3500.pa01.orderingflag.Modified;
import cs3500.pa01.orderingflag.OrderingFlag;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileVisitor class
 */
public class MarkDownFileVisitorTest {
  File arrays;
  ArrayList<File> files;
  // ordering flags for each organization method
  OrderingFlag fn;
  OrderingFlag mod;
  OrderingFlag cr;
  // markdown file visitors for each type of sorting
  MarkDownFileVisitor filevisitorfn;
  MarkDownFileVisitor filevisitorm;
  MarkDownFileVisitor filevisitorc;

  /**
   * To initialize data
   */
  public void initData() {
    File dir = new File("src/test/resources/tempFiles");
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }
      arrays = File.createTempFile("arrays", ".md",
          new File("src/test/resources/tempFiles"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    files = new ArrayList<>();
    fn = new FileName();
    mod = new Modified();
    cr = new Created();
    filevisitorfn = new MarkDownFileVisitor(files, fn);
    filevisitorm = new MarkDownFileVisitor(files, mod);
    filevisitorc = new MarkDownFileVisitor(files, cr);
  }

  @Test
  public void testGetSortedList() {
    initData();
    assertEquals(files, filevisitorfn.getSortedList());
    assertEquals(files, filevisitorm.getSortedList());
    assertEquals(files, filevisitorc.getSortedList());
  }

  @Test
  public void testVisitFile() throws IOException {
    initData();
    BasicFileAttributes bfa = Files.readAttributes(arrays.toPath(), BasicFileAttributes.class);
    ArrayList<File> visitfiles = new ArrayList<>(List.of(arrays));
    filevisitorfn.visitFile(arrays.toPath(), bfa);
    assertEquals(visitfiles, filevisitorfn.files);
  }
}
