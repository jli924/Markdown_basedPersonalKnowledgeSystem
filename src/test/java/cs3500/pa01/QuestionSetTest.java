package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.Question;
import cs3500.pa01.model.QuestionSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Tests for the QuestionSet class
 */
public class QuestionSetTest {
  Random rand = new Random(69);
  Question myName = new Question("What is my name?", "Jamie Li", false);
  Question petsHuh =
      new Question("Do I have pets?", "No, but I used to", true);
  Question hello = new Question("Hello World?", "Hello", false);
  ArrayList<Question> questions = new ArrayList<>(Arrays.asList(myName, petsHuh));
  QuestionSet questionSet = new QuestionSet(rand, questions);
  String questionsAsString = "[[What is my name? ::: Jamie Li (E)]]\n"
      + "[[Do I have pets? ::: No, but I used to (H)]]\n";

  /**
   * To test the toString method in QuestionSet
   */
  @Test
  public void testToString() {
    assertEquals(questionsAsString, questionSet.toString());
  }

  /**
   * To test the setQuestionToHard method in QuestionSet
   */
  @Test
  public void testSetQuestionToHard() {
    questionSet.setQuestionToHard();
    assertTrue(myName.isHard());
  }

  /**
   * To test the setQuestionToEasy method in QuestionSet
   */
  @Test
  public void testSetQuestionToEasy() {
    questionSet.setQuestionToEasy();
    assertFalse(myName.isHard());
  }

  /**
   * To test the setQuestionsToStudy method in QuestionSet
   */
  @Test
  public void testSetQuestionsToStudy() {
    questionSet.setQuestionsToStudy(2);
    assertEquals(2, questionSet.getQuestionsToStudy());
  }

  /**
   * To test the getQuestionsToStudy method in QuestionSet
   */
  @Test
  public void testGetQuestionsToStudy() {
    questionSet.setQuestionsToStudy(68);
    assertEquals(68, questionSet.getQuestionsToStudy());
  }

  /**
   * To test the numOfQuestions method in QuestionSet
   */
  @Test
  public void testNumOfQuestions() {
    assertEquals(2, questionSet.numOfQuestions());
  }

  /**
   * To test the numOfHard method in QuestionSet
   */
  @Test
  public void testNumOfHard() {
    assertEquals(1, questionSet.numOfHard());
  }

  /**
   * To test the numOfEasy method in QuestionSet
   */
  @Test
  public void testNumOfEasy() {
    assertEquals(1, questionSet.numOfEasy());
  }

  /**
   * To test the getCurQuestion method in QuestionSet
   */
  @Test
  public void testGetCurQuestion() {
    assertEquals(myName, questionSet.getCurQuestion());
  }

  /**
   * To test the addQuestion method in QuestionSet
   */
  @Test
  public void testAddQuestion() {
    questionSet.addQuestion(hello);
    assertEquals(hello, questionSet.questions.get(2));
  }

  /**
   * To test the randomizeQuestions method in QuestionSet
   */
  @Test
  public void testRandomizeQuestions() {
    questionSet.randomizeQuestions();
    assertEquals(questions, questionSet.questions);
  }

  /**
   * To test the nextQuestion method in QuestionSet
   */
  @Test
  public void testNextQuestion() {
    questionSet.indexOfQuestion = 1;
    questionSet.questionsToStudy = 2;
    assertEquals(questions.get(1), questionSet.nextQuestion());
  }

  /**
   * To test the determineDifficulty method in QuestionSet
   */
  @Test
  public void testDetermineDifficulty() {
    assertTrue(questionSet.determineDifficulty("H"));
    assertFalse(questionSet.determineDifficulty("E"));
    assertThrows(IllegalArgumentException.class,
        () -> questionSet.determineDifficulty("i"));
  }
}
