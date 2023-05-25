package cs3500.pa01.controller;

import cs3500.pa01.model.Model;
import cs3500.pa01.view.View;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles user input and what to output
 */
public class Controller {
  // sadly I had to make this public for testing.
  public Model model;
  View view;
  final Readable input;
  File srFile;

  /**
   * Constructor
   */
  public Controller() {
    this.model = new Model();
    this.view = new View(System.out);
    this.input = new InputStreamReader(System.in);
  }

  /**
   * Generates a .sr file from a study guide
   */
  public File generateSrFile() {
    return model.generateSrFile();
  }

  /**
   * Extracts data from the .sr file
   * Data being: the user's data/stats and questions
   *
   * @param path to the .sr file
   */
  public void extractData(String path) {
    Path questionBank = Paths.get(path);
    srFile = questionBank.toFile();
    model.organizeData(questionBank.toFile());
  }

  /**
   * To set the number of questions the user wants to study
   * Throws an exception if input is not a valid number
   *
   * @param input the user's input (how many questions they want to study)
   */
  public void questionsToStudy(String input) {
    if (Integer.parseInt(input) < 0) {
      throw new IllegalArgumentException("You cannot study this number of questions.");
    } else {
      model.setQuestionsToStudy(Integer.parseInt(input));
    }
  }

  /**
   * To handle user input (of options)
   *
   * @param input the user's input
   * @return whether the user's input is valid
   */
  public boolean handleUserInput(String input) {
    if (input.equals("e")) {
      // switches a question to easy
      model.switchToEasy();
      return true;
    } else if (input.equals("h")) {
      // switches a question to hard
      model.switchToHard();
      return true;
    } else if (input.equals("a")) {
      // shows the answer to a question
      view.showAnswer(model.getCurQuestion());
      return true;
    } else if (input.equals("x")) {
      // exits the study session, shows the stats
      view.showStats(model.getUserData());
      return true;
    } else {
      System.out.println("Not a valid option. Please select again.");
      return false;
    }
  }

  /**
   * To run the study session
   */
  public void run() {
    Scanner sc = new Scanner(this.input);
    // ask user to provide file path: exception will be thrown if path is not valid
    view.askForFilePath();
    // read file & extract questions and user data
    extractData(sc.nextLine());
    // randomize those questions now in the QuestionSet
    model.randomizeQuestions();
    // welcome user & ask for # of questions
    view.welcomeUser();
    // set the number of questions they want to study
    questionsToStudy(sc.nextLine());
    // this loop, for the number of questions the user chooses...
    for (int i = 0; i < model.numOfQuestions; i++) {
      // will show the question
      view.showQuestion(model.nextQuestion());
      // user, you must answer!
      sc.nextLine();
      // increase stat: answered questions
      model.increaseAnswered();
      // show options after user answers
      view.showOptions();
      // this loop handles the input after the options are shown
      boolean validInput = false;
      while (!validInput) {
        validInput = handleUserInput(sc.nextLine());
      }
    }
    // update the number of hard and easy questions after a user has studied
    model.updateQuestionCount();
    // shows the stats after the user has finished studying
    view.showStats(model.getUserData());
    // !!! UNCOMMENT BEFORE SUBMITTING !!!
    //model.updateSrFile(srFile);
  }
}
