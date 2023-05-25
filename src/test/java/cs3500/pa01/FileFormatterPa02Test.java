package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.FileFormatter;
import cs3500.pa01.model.Question;
import org.junit.jupiter.api.Test;

/**
 * To test the methods in the FileFormatter class for PA02:
 * Many of FileFormatter's methods have already been tested in PA01,
 * or for PA02, in ModelTest
 */
public class FileFormatterPa02Test {
  FileFormatter fileFormatter = new FileFormatter();
  String myQuestion = "[[What is my name? ::: Jamie Li]]";
  String easyQuestion = "[[What color is the sky? ::: Blue (E)]]";
  Question myName = new Question("What is my name?", "Jamie Li", true);
  Question colorOfSky = new Question("What color is the sky?", "Blue", false);
  String answered = "> Questions answered: 7";
  String hardToEasy = "> Questions switched from hard to easy: 4";
  String easyToHard = "> Questions switched from easy to hard: 1";
  String hardQs = "> Hard questions: 10";
  String easyQs = "> Easy questions: 3";

  /**
   * Tests for the extractQuestion method in FileFormatter
   */
  @Test
  public void testExtractQuestion() {
    fileFormatter.extractQuestion(myQuestion);
    fileFormatter.extractQuestion(easyQuestion);
    assertEquals(myName.question, fileFormatter.quesSet.questions.get(0).question);
    assertEquals(colorOfSky.question, fileFormatter.quesSet.questions.get(1).question);
  }

  /**
   * Tests for the setUserData method in FileFormatter
   */
  @Test
  public void testSetUserData() {
    fileFormatter.setUserData(answered);
    fileFormatter.setUserData(hardToEasy);
    fileFormatter.setUserData(easyToHard);
    fileFormatter.setUserData(hardQs);
    fileFormatter.setUserData(easyQs);
    assertEquals(fileFormatter.userData.answered, 7);
    assertEquals(fileFormatter.userData.hardToEasy, 4);
    assertEquals(fileFormatter.userData.easyToHard, 1);
    assertEquals(fileFormatter.userData.hardQs, 10);
    assertEquals(fileFormatter.userData.easyQs, 3);
  }
}
