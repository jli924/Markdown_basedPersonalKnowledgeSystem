package cs3500.pa01.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * To represent a set of questions
 */
public class QuestionSet {
  public ArrayList<Question> questions = new ArrayList<>();
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
   * Constructor with seeded random
   */
  public QuestionSet(Random r) {
    rand = r;
  }

  public int setQuestionsToStudy(int i) {
    return questionsToStudy = i;
  }

  public int getQuestionsToStudy() {
    return questions.size();
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
   * Randomizes the questions in the array list
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
