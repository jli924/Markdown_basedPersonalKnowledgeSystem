package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Model.FileFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileFormatter class
 */
public class FileFormatterTest {
  String s1 = "[[I am important]] but I am not.";
  String t1 = "I am [[important]] but I am not [[";
  String r1 = "I am [[important]] but I am not ]]";
  String i1 = "I am not important";
  String g1 = "Oh no [[will I fail?]] I hope [[not]]";
  String s2 = "[[Hopefully]] it is [[support]]";
  FileFormatter ff = new FileFormatter();

  @Test
  public void testExtractImportantPhrase() {
    assertEquals("- I am important", ff.formatImportantPhrase(s1));
    assertEquals("- important", ff.formatImportantPhrase(t1));
    assertEquals("- important", ff.formatImportantPhrase(r1));
    assertEquals("", ff.formatImportantPhrase(i1));
    assertEquals("- will I fail?" + "\n" + "- not", ff.formatImportantPhrase(g1));
    assertEquals("- Hopefully" + "\n" + "- support", ff.formatImportantPhrase(s2));
  }

  @Test
  public void testFormatContents() {
    String h = "# Tank";
    String s = "[[Winston]]";
    String h1 = "# DPS";
    String s1 = "Genji and Hanzo [[Shimada]]";
    String h2 = "# Support";
    String s2 = "[[Ana]] and [[Kiriko]]";
    ArrayList<String> ow = new ArrayList<>(Arrays.asList(h, s, h1, s1, h2, s2));
    String hf = "# Tank";
    String sf = "- Winston";
    String h1f = "# DPS";
    String s1f = "- Shimada";
    String h2f = "# Support";
    String s2f = "- Ana" + "\n" + "- Kiriko";
    ArrayList<String> owformatted = new ArrayList<>(Arrays.asList(hf, sf, h1f, s1f, h2f, s2f));
    assertEquals(owformatted, ff.formatContents(ow));
  }
}
