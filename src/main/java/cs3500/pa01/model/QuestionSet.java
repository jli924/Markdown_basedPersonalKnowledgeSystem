package cs3500.pa01.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * To represent a set of questions
 */
public class QuestionSet {
  public ArrayList<Question> questions;
  Question curQuestion;
  Random rand = new Random();
  public int questionsToStudy;
  public int indexOfQuestion = 0;

  /**
   * Constructor
   */
  public QuestionSet(ArrayList<Question> questions) {
    this.questions = questions;
  }

  /**
   * Constructor with seeded random for testing
   */
  public QuestionSet(Random r, ArrayList<Question> questions) {
    this.rand = r;
    this.questions = questions;
    this.curQuestion = questions.get(indexOfQuestion);
  }

  /**
   * Converts a QuestionSet back to a string to write back to the .sr file
   *
   * @return a string with the formatted questions
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Question question : questions) {
      sb.append(question.toString());
    }
    return sb.toString();
  }

  /**
   * Sets the current question to hard
   */
  public void setQuestionToHard() {
    curQuestion.setToHard();
  }

  /**
   * Sets the current question to easy
   */
  public void setQuestionToEasy() {
    curQuestion.setToEasy();
  }

  /**
   * Gets the number of questions the user wants to study
   *
   * @return number of question to study
   */
  public int getQuestionsToStudy() {
    return questionsToStudy;
  }

  /**
   * Sets the number of questions to study based on user input
   */
  public void setQuestionsToStudy(int input) {
    questionsToStudy = input;
  }

  /**
   * Gets the number of questions
   *
   * @return the number of questions
   */
  public int numOfQuestions() {
    return questions.size();
  }

  /**
   * Counts the number of hard questions
   *
   * @return the number of hard questions
   */
  public int numOfHard() {
    int count = 0;
    for (Question question : questions) {
      if (question.isHard()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Counts the number of easy questions
   *
   * @return the number of easy questions
   */
  public int numOfEasy() {
    int count = 0;
    for (Question question : questions) {
      if (!question.isHard()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Gets the current question
   *
   * @return the current question
   */
  public Question getCurQuestion() {
    return curQuestion;
  }

  /**
   * Adds a question to the array list of questions
   *
   * @param q the question to add
   */
  public void addQuestion(Question q) {
    questions.add(q);
  }

  /**
   * Randomizes the questions in the array list of questions
   */
  public void randomizeQuestions() {
    Collections.shuffle(questions, rand);
  }

  /**
   * Gets the next question
   *
   * @return the question at that index
   */
  public Question nextQuestion() {
    if (indexOfQuestion <= questionsToStudy) {
      curQuestion = questions.get(indexOfQuestion);
      indexOfQuestion++;
    }
    return curQuestion;
  }

  /**
   * Determines the difficulty of a question
   *
   * @param difficulty the difficulty of a question represented as a string (H or E)
   *
   * @return a boolean (the difficulty)
   */
  public boolean determineDifficulty(String difficulty) {
    if (difficulty.equals("E")) {
      return false;
    } else if (difficulty.equals("H")) {
      return true;
    } else {
      throw new IllegalArgumentException("Not a valid difficulty.");
    }
  }
}
