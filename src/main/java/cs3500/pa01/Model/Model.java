package cs3500.pa01.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Handles functionality
 */
public class Model {
  FileIo fileIo;
  public FileFormatter fileFormatter;
  public int numOfQuestions;

  /**
   * Constructor
   */
  public Model() {
    fileIo = new FileIo();
    fileFormatter = new FileFormatter();
  }

  /**
   * Set the number of questions the user wants to study
   *
   * @param toStudy the number of questions the user wants to study
   */
  public void setQuestionsToStudy(int toStudy) {
    if (toStudy > fileFormatter.numOfQuestions()) {
      toStudy = fileFormatter.numOfQuestions() - 1;
    }
    fileFormatter.setQuestionsToStudy(toStudy);
    numOfQuestions = fileFormatter.getQuestionsToStudy();
  }

  /**
   * Read a .sr file (using a method written in PA01)
   *
   * @param file the file to read
   *
   * @return the string of contents read
   */
  public ArrayList<String> readSrFile(File file) {
    try {
      return fileIo.fileContents(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Formats file contents, allocating data to the right places:
   * Questions and user data
   *
   * @param file the file to read & format
   */
  public void organizeData(File file) {
    ArrayList<String> contents = readSrFile(file);
    fileFormatter.formatContents(contents);
  }

  /**
   * Randomize the questions!
   */
  public void randomizeQuestions() {
    fileFormatter.quesSet.randomizeQuestions();
  }

  /**
   * Get the next question
   *
   * @return the question
   */
  public Question nextQuestion() {
    return fileFormatter.nextQuestion();
  }

  /**
   * Get the current question
   *
   * @return the current question
   */
  public Question getCurQuestion() {
    return fileFormatter.getCurQuestion();
  }

  /**
   * Get the user's data/stats
   *
   * @return the user's data
   */
  public UserData getUserData() {
    return fileFormatter.getUserData();
  }

  /**
   * Increase the amount of questions answered
   */
  public void increaseAnswered() {
    fileFormatter.increaseAnswered();
  }

  /**
   * Switch a question to easy
   */
  public void switchToEasy() {
    fileFormatter.setQuestionToEasy();
  }

  /**
   * Switch a question to hard
   */
  public void switchToHard() {
    fileFormatter.setQuestionToHard();
  }
}
