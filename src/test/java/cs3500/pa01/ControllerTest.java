package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.controller.Controller;
import cs3500.pa01.model.UserData;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Controller class
 */
public class ControllerTest {
  Controller controller;
  Path yourQuestionBank;
  String pathToQuestionBank;
  UserData stats;

  /**
   * Initialize data
   */
  @BeforeEach
  public void initData() {
    controller = new Controller();
    yourQuestionBank = Path.of("README/YourQuestionBank.sr");
    pathToQuestionBank = "README/QuestionBank.sr";
    stats = new UserData(1, 2, 3, 4, 5);
  }

  /**
   * To test the generateSrFile method in Controller
   */
  @Test
  public void testGenerateSrFile() {
    initData();
    assertEquals(yourQuestionBank.toFile(), controller.generateSrFile());
  }

  /**
   * To test the extractData method in Controller
   */
  @Test
  public void testExtractData() {
    initData();
    controller.extractData(pathToQuestionBank);
    assertEquals(stats.toString(), controller.model.getUserData().toString());
  }

  /**
   * To test the questionsToStudy method in Controller
   */
  @Test
  public void testQuestionsToStudy() {
    initData();
    controller.extractData(pathToQuestionBank);
    controller.questionsToStudy("3");
    assertEquals(3, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
    controller.questionsToStudy("5");
    assertEquals(4, controller.model.fileFormatter.quesSet.getQuestionsToStudy());
    assertThrows(IllegalArgumentException.class, () -> controller.questionsToStudy("-1"));
  }

  /**
   * To test the handleUserInput method in Controller
   */
  @Test
  public void testHandleUserInput() {
    initData();
    controller.extractData(pathToQuestionBank);
    controller.model.nextQuestion();
    // true will not prompt the user to answer again, false will
    assertTrue(controller.handleUserInput("e"));
    assertTrue(controller.handleUserInput("h"));
    assertTrue(controller.handleUserInput("a"));
    assertTrue(controller.handleUserInput("x"));
    assertFalse(controller.handleUserInput("k"));
    assertFalse(controller.handleUserInput("hello"));
  }
}
