package cs3500.pa01;

/**
 * Handles what to show the user
 */
public class View {
  QuestionSet quesSet = new QuestionSet();

  /**
   * Constructor
   */
  public View() {

  }

  /**
   * Welcomes the user
   *
   * @return a string, welcoming the user
   */
  public String welcomeUser() {
    return "Welcome!!"
        + "\n"
        + "How many questions would you like to study today?";
  }

  /**
   * Shows the user the next question
   *
   * @param questionsToStudy the number of questions the user wants to study
   *
   * @return the question, as a string
   */
  // REMEMBER TO DECREASE QUESTIONS TO STUDY!!!
  public String showNextQuestion(int questionsToStudy) {
    int questionsInBank = quesSet.questions.size();
    if (questionsToStudy > questionsInBank) {
      questionsToStudy = questionsInBank;
    }
    // returning the "question" part of the Question object,
    // a string (see class Question for more)
    String question = (quesSet.nextQuestion(questionsToStudy)).question;
    return question;
  }

  /**
   * Shows the user options
   *
   * @return a string of the options
   */
  public String showOptions() {
    return "Easy(e)  Hard(h)  Show Answer(a)  Exit(x)";
  }

  /**
   * Shows the user the answer to a question if they so choose
   *
   * @param question the question they want the answer to
   *
   * @return the answer as a string
   */
  public String showAnswer(Question question) {
    return question.answer;
  }

  /**
   * Shows the user their stats at the end of the study session
   *
   * @param stats their stats
   *
   * @return their stats as a string
   */
  public String showStats(UserData stats) {
    return "Good Job!" + "\n"
        + "You answered " + stats.answered + " questions!" + "\n"
        + stats.easyToHard + " questions went from easy to hard." + "\n"
        + stats.hardToEasy + " questions went from hard to easy." + "\n"
        + "Current counts in question bank:" + "\n"
        + "Hard questions: " + stats.hardQs  + "\n"
        + "Easy questions: " + stats.easyQs + "\n";
  }
}
