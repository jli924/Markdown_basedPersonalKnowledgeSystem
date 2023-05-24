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

  public Model() {
    fileIo = new FileIo();
    fileFormatter = new FileFormatter();
    numOfQuestions = fileFormatter.quesSet.questionsToStudy + 1;
  }

  /**
   * Set the number of questions the user wants to study
   *
   * @param toStudy the number of questions the user wants to study
   */
  public void setQuestionsToStudy(int toStudy) {
    if (toStudy > fileFormatter.getNumOfQuestions()) {
      toStudy = fileFormatter.getNumOfQuestions() - 1;
    }
    fileFormatter.setQuestionsToStudy(toStudy);
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
    return fileFormatter.quesSet.nextQuestion();
  }

  /**
   * Switch a question to easy
   */
  public void switchToEasy() {
    fileFormatter.quesSet.curQuestion.setToEasy();
  }

  /**
   * Switch a question to hard
   */
  public void switchToHard() {
    fileFormatter.quesSet.curQuestion.setToHard();
  }

  /**
   * Get the current question
   */
  public Question getCurQuestion() {
    return fileFormatter.getCurQuestion();
  }

  public UserData getUserData() {
    return fileFormatter.getUserData();
  }

  public void updateNumOfQuestions() {
    numOfQuestions = fileFormatter.getNumOfQuestions();
  }

  public void increaseAnswered() {
    fileFormatter.increaseAnswered();
  }

  public void increaseEasyToHard() {
    fileFormatter.increaseEasyToHard();
  }

  public void increaseHardToEasy() {
    fileFormatter.increaseHardToEasy();
  }
}
