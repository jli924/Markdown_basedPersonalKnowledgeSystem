package cs3500.pa01.controller;

import cs3500.pa01.Model.Model;
import cs3500.pa01.View.View;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles user input & what to output
 */
public class Controller {
  Model model = new Model();
  View view = new View(System.out);
  final Readable input;
  final Appendable output;

  /**
   * Constructor
   */
  public Controller() {
    this.input = new InputStreamReader(System.in);
    this.output = new OutputStreamWriter(System.out);

  }

  public void extractData(String path) {
    Path questionBank = Paths.get(path);
    model.organizeData(questionBank.toFile());
  }

  public void questionsToStudy(String input) {
    model.setQuestionsToStudy(Integer.parseInt(input));
  }

  public boolean handleUserInput(String input) {
    if (input.equals("e")) {
      model.switchToEasy();
      return true;
    } else if (input.equals("h")) {
      model.switchToHard();
      return true;
    } else if (input.equals("a")) {
      view.showAnswer(model.getCurQuestion());
      return true;
    } else if (input.equals("x")) {
      view.showStats(model.fileFormatter.userData);
      return true;
    } else {
      System.out.println("Not a valid option. Please select again.");
      return false;
    }
  }

  /**
   * To start the study session
   */
  public void run() {
    Scanner sc = new Scanner(this.input);
    // ask user to provide file path: exception will be thrown if path is not valid
    view.askForFilePath();
    // read file & extract questions and user data
    extractData(sc.next());
    // randomize those questions now in the QuestionSet
    model.randomizeQuestions();
    // welcome user & ask for # of questions
    view.welcomeUser();
    // set the number of questions they want to study
    questionsToStudy(sc.next());
    model.updateNumOfQuestions();
    for (int i = 0; i < model.numOfQuestions; i++) {
      view.showQuestion(model.nextQuestion());
      sc.next();
      model.increaseAnswered();
      view.showOptions();
      boolean validInput = false;
      while(!validInput) {
        validInput = handleUserInput(sc.next());
      }
    }
    view.showStats(model.getUserData());
  }
}
