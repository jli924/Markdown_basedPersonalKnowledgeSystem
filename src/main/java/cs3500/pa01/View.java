package cs3500.pa01;

public class View {
  QuestionSet quesSet = new QuestionSet();

  public String welcomeUser() {
    return "Welcome!!" +
        "\n" +
        "How many questions would you like to study today?";
  }

  // REMEMBER TO DECREASE QUESTIONS TO STUDY!!!
  public String showNextQuestion(int questionsToStudy) {
    int questionsInBank = quesSet.hardquestions.size();
    if(questionsToStudy > questionsInBank) {
      questionsToStudy = questionsInBank;
    }
    // returning the "question" part of the Question object,
    // a string (see class Question for more)
    String question = (quesSet.nextQuestion(questionsToStudy)).question;
    return question;
  }

  public String showOptions() {
    return "Easy(e)  Hard(h)  Show Answer(a)  Exit(x)";
  }

  public String showStats(UserData ud) {
    return "Good Job!" + "\n"
        + "You answered " + ud.answered + " questions!" + "\n"
        + ud.easyToHard + " questions went from easy to hard." + "\n"
        + ud.hardToEasy + " questions went from hard to easy." + "\n"
        + "Current counts in question bank:" + "\n"
        + "Hard questions: " + ud.hardQs  + "\n"
        + "Easy questions: " + ud.easyQs + "\n";
  }
}
