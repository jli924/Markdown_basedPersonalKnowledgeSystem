package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileFormatter class for PA02:
 * *
 * I added a method named extractQuestion which
 * would extract questions from a file the user provides
 * instead of adding it to a study guide
 * *
 * This class is to test that one method and keep it
 * separate from PA01 tests, so you don't get confused!
 */
public class FileFormatterPA02Test {
  FileFormatter fileFormatter = new FileFormatter();
  String myQuestion = "[[What is my name? ::: Jamie Li]]";
  Question myName = new Question("What is my name? ", "Jamie Li", true);

  @Test
  public void testExtractQuestion() {
    fileFormatter.extractQuestion(myQuestion);
    assertEquals(myName.question, fileFormatter.quesSet.hardquestions.get(0).question);
  }
}
