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
  Model model = new Model();
  Question myName = new Question("What is my name?", "Jamie Li", false);
  Question petsHuh =
      new Question("Do I have pets?", "No, but I used to", true);
  File fake = Path.of("fake file").toFile();
  String cats = "[[Are cats or dogs better? ::: Cats]]";
  String sky = "[[What is the color of the sky? ::: Blue (E)]]";
  String neu = "[[What is the name of our university? ::: Northeastern]]";
  String future = "[[What does the future look like? ::: Nobody knows... (H)]]";
  String statsHeader = "{ User Stats }";
  String answered = "> Questions answered: 1";
  String easyToHard = "> Questions switched from easy to hard: 2";
  String hardToEasy = "> Questions switched from hard to easy: 3";
  String hard = "> Hard questions: 4";
  String easy = "> Easy questions: 5";
  ArrayList<String> contents = new ArrayList<>(Arrays.asList(cats, sky, neu, future, statsHeader,
      answered, easyToHard, hardToEasy, hard, easy));
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
  String userStats = "{ User Stats } \n"
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
    assertThrows(RuntimeException.class, () -> model.readSrFile(fake));
  }

  /**
   * To test the organizeData method in Model
   */
  @Test
  public void testOrganizeData() {
    model.fileFormatter.formatContents(contents);
    assertEquals(userStats, model.getUserData().toString());
    assertEquals(questions.toString(), model.fileFormatter.quesSet.questions.toString());
  }

  /**
   * To test the randomizeQuestions method in QuestionSet
   */
  @Test
  public void testRandomizeQuestions() {
    model.randomizeQuestions();
    assertEquals(noQuestions, model.fileFormatter.quesSet.questions);
  }

  // most of model's methods are just calling other methods, therefore there
  // is no need to test them again
}
