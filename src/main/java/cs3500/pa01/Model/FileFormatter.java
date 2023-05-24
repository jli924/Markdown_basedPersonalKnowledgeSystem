package cs3500.pa01.Model;

import java.util.ArrayList;

/**
 * To represent a file formatter, which formats file contents
 * (Deals with headers, important phrases, etc.)
 */
public class FileFormatter {
  ArrayList<Question> questions = new ArrayList<>();
  public QuestionSet quesSet = new QuestionSet(questions);
  public UserData userData = new UserData(0, 0, 0, 0, 0);

  /**
   * Constructor
   */

  public FileFormatter() {
  }

  // I WROTE THESE METHODS FOR PA02 REASONS!!!
  // * * * * * * * * * * * * * * * * * * * * *

  void setQuestionsToStudy(int i) {
    quesSet.setQuestionsToStudy(i);
  }

  int getQuestionsToStudy() {
    return quesSet.getQuestionsToStudy();
  }

  Question nextQuestion() {
    return quesSet.nextQuestion();
  }

  int numOfQuestions() {
    return quesSet.numOfQuestions();
  }

  UserData getUserData() {
    return userData;
  }

  Question getCurQuestion() {
    return quesSet.getCurQuestion();
  }

  void increaseAnswered() {
    userData.increaseAnswered();
  }

  void setQuestionToEasy() {
    if (quesSet.getCurQuestion().isHard()) {
      userData.increaseHardToEasy();
    }
    quesSet.setQuestionToEasy();
  }

  void setQuestionToHard() {
    if (!quesSet.getCurQuestion().isHard()) {
      userData.increaseEasyToHard();
    }
    quesSet.setQuestionToHard();
  }

  /**
   * Sets a user's data from a .sr file (their previous stats)
   *
   * @param s the line of stats being read
   */
  public void setUserData(String s) {
    String relevantPhrase = s.replace("\n", "");
    int data =
        Integer.parseInt(relevantPhrase.substring(relevantPhrase.indexOf(": ") + 2));
    if (s.startsWith("> Questions answered: ")) {
      userData.answered = data;
    } else if (s.startsWith("> Questions switched from hard to easy: ")) {
      userData.hardToEasy = data;
    } else if (s.startsWith("> Questions switched from easy to hard: ")) {
      userData.easyToHard = data;
    } else if (s.startsWith("> Hard questions: ")) {
      userData.hardQs = data;
    } else if (s.startsWith("> Easy questions: ")) {
      userData.easyQs = data;
    } else {
      throw new IllegalArgumentException("You didn't format that properly...");
    }
  }

  /**
   * Extracts a question, adding it to a QuestionSet
   * Needs to be public, so I can test it
   *
   * @param s the question to extract
   *
   * @return an empty string so it is not written back to the .md file
   */
  public String extractQuestion(String s) {
    String question;
    String answer;
    if (s.contains("[[") && s.contains(":::") && s.contains("]]")) {
      question = s.substring(s.indexOf("[[") + 2, s.indexOf(":::"));
      question = question.trim();
      answer = s.substring(s.indexOf(":::") + 3, s.indexOf("]]"));
      answer = answer.trim();
      if (s.contains("(") && s.contains(")")) {
        String difficulty = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
        answer = s.substring(s.indexOf(":::") + 3, s.indexOf("("));
        answer.trim();
        quesSet.addQuestion(
            new Question(question, answer, quesSet.determineDifficulty(difficulty)));
      }
      // the last boolean is whether a question is hard,
      // default value is true.
      quesSet.addQuestion(new Question(question, answer, true));
    }
    return "";
  }
  // * * * * * * * * * * * * * * * *
  // END OF METHODS WRITTEN FOR PA02 (in this PA01 file)

  // BELOW HASN'T BEEN TOUCHED SINCE PA01
  /**
   * If a String contains double brackets, format the important phrase
   * within properly: meaning, add a bullet and remove brackets
   *
   * @param s phrase that needs to have an important phrase extracted
   * @return the important phrase (without double brackets)
   */

  public String formatImportantPhrase(String s) {
    String important = "";
    String restofline;
    // added line to check for & extract questions
    extractQuestion(s);
    // if not a question, don't extract; go to formatting important info
    if (s.contains("[[") && s.contains("]]") && !s.contains(":::")) {
      important = important + "- " + s.substring(s.indexOf("[[") + 2, s.indexOf("]]"));
      restofline = s.substring(s.indexOf("]]") + 2);
      if (restofline.contains("[[") && restofline.contains("]]")) {
        important = important + "\n" + formatImportantPhrase(restofline);
      }
    }
    return important;
  }


  // THIS METHOD WAS UPDATED FOR PA02 REASONS !!!
  /**
   * Formats file contents properly: keeps headers, uses
   * formatImportantPhrase, gets rid of useless content (not either of the
   * other two)
   *
   * @param contents the contents of a file as an array list of strings
   * @return the formatted file contents as an array list of strings
   */

  public ArrayList<String> formatContents(ArrayList<String> contents) {
    // does this need to return something?

    for (int i = 0; i < contents.size(); i++) {
      if (contents.get(i).startsWith(">")) { // added this line
        setUserData(contents.get(i)); // and this line for PA02
      } else if (!contents.get(i).startsWith("#")) {
        contents.set(i, formatImportantPhrase(contents.get(i)));
      }
    }
    return contents;
  }
}
