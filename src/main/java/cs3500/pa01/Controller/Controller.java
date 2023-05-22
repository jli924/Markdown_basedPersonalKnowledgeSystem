package cs3500.pa01.Controller;

import cs3500.pa01.Model.Model;
import cs3500.pa01.View.View;
import java.io.IOException;
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
  int questionsToStudy;
  final Readable input;
  final Appendable output;

  /**
   * Constructor
   */
  public Controller() {
    this.input = new InputStreamReader(System.in);
    this.output = new OutputStreamWriter(System.out);

  }
  public Controller(int questionsToStudy, Readable input, Appendable output) {
    this.questionsToStudy = questionsToStudy;
    this.input = new InputStreamReader(System.in);
    this.output = new OutputStreamWriter(System.out);
  }

  public void welcome() {
    try {
      output.append(view.welcomeUser());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void extractData(String path) {
    Path questionBank = Paths.get(path);
    model.organizeData(questionBank.toFile());
  }

  public void questionsToStudy(String input) {
    model.setQuestionsToStudy(Integer.parseInt(input));
  }

  /**
   * To start the study session
   */
  public void run() {
    Scanner sc = new Scanner(this.input);
    // ask user to provide file path: exception will be thrown if path is not valid
    view.provideFile();
    // read file
    // is this how to properly read user input?
    extractData(sc.next());
    // after user provides valid file, welcome them & ask for # of questions
    welcome();
    questionsToStudy(sc.next());
  }
}
