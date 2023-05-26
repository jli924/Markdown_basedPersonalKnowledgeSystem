package cs3500.pa01.model;

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
      toStudy = fileFormatter.numOfQuestions();
    }
    fileFormatter.setQuestionsToStudy(toStudy);
    numOfQuestions = fileFormatter.getQuestionsToStudy();
  }

  /**
   * Read a .sr file (using a method written in PA01)
   *
   * @param file the file to read
   *
   * @return the array list of strings of contents read
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

  /**
   * Updates the count of hard and easy questions in the user's data
   */
  public void updateQuestionCount() {
    fileFormatter.updateHardAndEasyQs();
  }

  /**
   * Updates the user's data on the .sr file and writes the questions back
   *
   * @param file the user's .sr file
   */
  public void updateSrFile(File file) {
    StringBuilder sb = new StringBuilder();
    sb.append(fileFormatter.getSrContents());
    sb.append("\n");
    sb.append(fileFormatter.getUserData().toString());
    fileIo.writeToFile(file, sb.toString());
  }

  /**
   * Generates a .sr file from a study guide
   */
  public File generateSrFile() {
    File questionBank = new File("README/YourQuestionBank.sr");
    updateSrFile(questionBank);
    return questionBank;
  }
}
