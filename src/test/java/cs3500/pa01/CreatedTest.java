package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.OrderingFlag.Created;
import cs3500.pa01.OrderingFlag.OrderingFlag;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the Created class
 */
public class CreatedTest {
  OrderingFlag cr = new Created();
  static File arrays;
  static File vectors;
  Path fake;
  ArrayList<File> files;
  ArrayList<File> filesSorted;
  ArrayList<File> fakeFile;
  FileTime arrCrTime;
  FileTime vecCrTime;

  /**
   * To set a file's creation date (so GitHub doesn't get mad)
   */
  // I am using for testing reasons...
  public void setCreationDate(String filePath, FileTime time) throws IOException {
    BasicFileAttributeView
        attributes = Files.getFileAttributeView(Paths.get(filePath), BasicFileAttributeView.class);
    attributes.setTimes(time, time, time);
  }

  /**
   * To initialize data
   */
  @BeforeEach
  public void initData() {
    arrCrTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    vecCrTime = FileTime.from(Instant.parse("2023-05-15T12:00:00Z"));
    File dir = new File("src/test/resources/tempFiles");
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }
      arrays = File.createTempFile("arrays", ".md", dir);
      setCreationDate(arrays.getPath(), arrCrTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      vectors = File.createTempFile("vectors", ".md", dir);
      setCreationDate(vectors.getPath(), vecCrTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    fake = Path.of("I am a fake path... hehe");
    fakeFile = new ArrayList<>(List.of(fake.toFile()));
    files = new ArrayList<>(Arrays.asList(vectors, arrays));
    filesSorted = new ArrayList<>(Arrays.asList(arrays, vectors));
  }

  @Test
  public void testSort() {
    initData();
    assertEquals(filesSorted, cr.sort(files));
    assertEquals(filesSorted, cr.sort(filesSorted));
    assertThrows(RuntimeException.class,
        () -> cr.sort(fakeFile));
  }
}
