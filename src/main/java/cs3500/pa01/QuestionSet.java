package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionSet {
  ArrayList<Question> hardquestions = new ArrayList<>();
  Random rand = new Random();

  public QuestionSet() {

  }

  public QuestionSet(Random r) {
    rand = r;
  }

  public void addQuestion(Question q) {
    hardquestions.add(q);
  }

  public void randomizeQuestions() {
    Collections.shuffle(hardquestions, rand);
  }

  public Question nextQuestion(int idx) {
    return hardquestions.get(idx);
  }

  public boolean determineDifficulty(String difficulty) {
    if(difficulty.equals("E")) {
      return false;
    }
    else if(difficulty.equals("H")) {
      return true;
    }
    else {
      throw new IllegalArgumentException("Not a valid difficulty.");
    }
  }
}
