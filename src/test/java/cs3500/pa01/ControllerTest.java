package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.controller.Controller;
import cs3500.pa01.model.UserData;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Controller class
 */
public class ControllerTest {
  Controller controller = new Controller();
  String pathToQuestionBank = "README/QuestionBank.sr";
  UserData stats = new UserData(1, 2, 3, 4, 5);

  /**
   * To test the extractData method in Controller
   */
  @Test
  public void testExtractData() {
    controller.extractData(pathToQuestionBank);
    assertEquals(stats.toString(), controller.model.getUserData().toString());
  }

  /**
   * To test the handleUserInput method in Controller
   */
  @Test
  public void testHandleUserInput() {
    assertEquals(3, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
    assertEquals(4, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
  }

  /**
   * To test the questionsToStudy method in Controller
   */
  @Test
  public void testQuestionsToStudy() {
    controller.extractData(pathToQuestionBank);
    controller.questionsToStudy("3");
    assertEquals(3, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
    controller.questionsToStudy("5");
    assertEquals(4, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
    assertThrows(IllegalArgumentException.class, () -> controller.questionsToStudy("-1"));
  }
}
