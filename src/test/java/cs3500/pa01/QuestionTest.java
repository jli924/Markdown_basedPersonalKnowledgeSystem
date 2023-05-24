package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.Question;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Question class
 */
public class QuestionTest {
  Question myName = new Question("What is my name?", "Jamie Li", false);
  Question petsHuh =
      new Question("Do I have pets?", "No, but I used to", true);

  String myNameString = "[[What is my name? ::: Jamie Li (E)]]\n";
  String petsString = "[[Do I have pets? ::: No, but I used to (H)]]\n";

  /**
   * To test the toString method in Question
   */
  @Test
  public void testToString() {
    assertEquals(myNameString, myName.toString());
    assertEquals(petsString, petsHuh.toString());
  }

  /**
   * To test the setToHard method in Question
   */
  @Test
  public void testSetQuestionToHard() {
    myName.setToHard();
    assertTrue(myName.isHard());
  }

  /**
   * To test the setToEasy method in Question
   */
  @Test
  public void testSetQuestionToEasy() {
    petsHuh.setToEasy();
    assertFalse(petsHuh.isHard());
  }

  /**
   * To test the isHard method in Question
   */
  @Test
  public void testIsHard() {
    assertTrue(petsHuh.isHard());
    assertFalse(myName.isHard());
  }
}
