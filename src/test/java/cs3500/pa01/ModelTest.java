package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.model.Model;
import cs3500.pa01.model.Question;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests for the model class
 * I only recently understood what "Model" in MVC was... forgive me for making
 * it an actual class...
 */
public class ModelTest {
  Path yourQuestionBank = Path.of("README/YourQuestionBank.sr");
  Path questionBank = Path.of("README/QuestionBank.sr");
  Path updatedSrFile = Path.of("README/UpdatedSrFile.sr");
  Path expectedSrFile = Path.of("README/UpdatedSrFileExpectedResult.sr");
  Model model = new Model();
  Question myName = new Question("What is my name?", "Jamie Li", false);
  Question petsHuh =
      new Question("Do I have pets?", "No, but I used to", true);
  File fake = Path.of("fake file").toFile();
  String cats = "[[Are cats or dogs better? ::: Cats]]\n";
  String sky = "[[What is the color of the sky? ::: Blue (E)]]\n";
  String neu = "[[What is the name of our university? ::: Northeastern]]\n";
  String future = "[[What does the future look like? ::: Nobody knows... (H)]]\n";
  String statsHeader = "{ User Stats }\n";
  String answered = "> Questions answered: 1\n";
  String easyToHard = "> Questions switched from easy to hard: 2\n";
  String hardToEasy = "> Questions switched from hard to easy: 3\n";
  String hard = "> Hard questions: 4\n";
  String easy = "> Easy questions: 5\n";
  ArrayList<String> contents = new ArrayList<>(Arrays.asList(cats, sky, neu, future, "\n",
      statsHeader, answered, easyToHard, hardToEasy, hard, easy, "\n"));
  Question catsVsDogs =
      new Question("Are cats or dogs better?", "Cats", true);
  Question skyColor =
      new Question("What is the color of the sky?", "Blue", false);
  Question northeastern = new Question("What is the name of our university?",
      "Northeastern", true);
  Question theFuture = new Question("What does the future look like?",
      "Nobody knows...", true);
  ArrayList<Question> questions = new ArrayList<>(Arrays.asList(catsVsDogs, skyColor,
      northeastern, theFuture));
  ArrayList<Question> noQuestions = new ArrayList<>();
  String userStats = "{ User Stats }\n"
      + "> Questions answered: " + 1 + "\n"
      + "> Questions switched from easy to hard: " + 2 + "\n"
      + "> Questions switched from hard to easy: " + 3 + "\n"
      + "> Hard questions: " + 4 + "\n"
      + "> Easy questions: " + 5;

  /**
   * To test the setQuestionsToStudy method in Question
   */
  @Test
  public void testSetQuestionsToStudy() {
    model.fileFormatter.quesSet.addQuestion(myName);
    model.fileFormatter.quesSet.addQuestion(petsHuh);
    model.setQuestionsToStudy(3);
    assertEquals(2, model.numOfQuestions);
    model.setQuestionsToStudy(1);
    assertEquals(1, model.numOfQuestions);
  }

  /**
   * To test the readSrFile method in Model
   * Only testing the exception because the method
   * calls a method already tested in PA01
   */
  @Test
  public void testReadSrFile() {
    assertEquals(contents, model.readSrFile(questionBank.toFile()));
    assertThrows(RuntimeException.class, () -> model.readSrFile(fake));
  }

  /**
   * To test the organizeData method in Model
   */
  @Test
  public void testOrganizeData() {
    model.organizeData(questionBank.toFile());
    assertEquals(userStats, model.getUserData().toString());
    assertEquals(questions.toString(), model.fileFormatter.quesSet.questions.toString());
  }

  /**
   * To test the randomizeQuestions method in Model
   */
  @Test
  public void testRandomizeQuestions() {
    model.randomizeQuestions();
    assertEquals(noQuestions, model.fileFormatter.quesSet.questions);
  }

  /**
   * To test the nextQuestion method in Model
   */
  @Test
  public void testNextQuestions() {
    model.organizeData(questionBank.toFile());
    assertEquals(catsVsDogs.toString(), model.nextQuestion().toString());
  }

  /**
   * To test the getCurQuestion method in Model
   */
  @Test
  public void testGetCurQuestion() {
    model.organizeData(questionBank.toFile());
    model.nextQuestion();
    assertEquals(catsVsDogs.toString(), model.getCurQuestion().toString());
  }

  /**
   * To test the getUserData method in Model
   */
  @Test
  public void testGetUserData() {
    model.organizeData(questionBank.toFile());
    assertEquals(userStats, model.getUserData().toString());
  }

  /**
   * To test the increaseAnswered method in Model
   */
  @Test
  public void testIncreaseAnswered() {
    model.increaseAnswered();
    assertEquals(1, model.getUserData().answered);
  }

  /**
   * To test the switchToEasy method in Model
   */
  @Test
  public void testSwitchToEasy() {
    model.organizeData(questionBank.toFile());
    model.nextQuestion();
    model.switchToEasy();
    assertEquals(4, model.getUserData().hardToEasy);
  }

  /**
   * To test the switchToHard method in Model
   */
  @Test
  public void testSwitchToHard() {
    model.organizeData(questionBank.toFile());
    model.nextQuestion();
    model.switchToHard();
    assertEquals(2, model.getUserData().easyToHard);
  }

  /**
   * To test the updateQuestionCount method in Model
   */
  @Test
  public void testUpdateQuestionCount() {
    model.organizeData(questionBank.toFile());
    model.updateQuestionCount();
    assertEquals(3, model.getUserData().hardQs);
    assertEquals(1, model.getUserData().easyQs);
  }

  /**
   * To test the updateSrFile method in Model
   */
  @Test
  public void testUpdateSrFile() {
    model.readSrFile(questionBank.toFile());
    model.organizeData(questionBank.toFile());
    model.updateSrFile(updatedSrFile.toFile());
    assertEquals(model.readSrFile(expectedSrFile.toFile()),
        model.readSrFile(updatedSrFile.toFile()));
  }

  /**
   * To test the generateSrFile method in Model
   */
  @Test
  public void testGenerateSrFile() {
    assertEquals(yourQuestionBank.toFile(), model.generateSrFile());
  }
}
