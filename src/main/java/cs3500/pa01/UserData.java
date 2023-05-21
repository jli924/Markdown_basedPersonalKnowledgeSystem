package cs3500.pa01;

public class UserData {
  // how will I store UserData in my .sr file?
  // { User Stats }
  // read? write back to file?
  int answered;
  int hardQs;
  int easyQs;
  int hardToEasy;
  int easyToHard;
  public UserData(int a, int h, int e, int hte, int eth) {
    answered = a;
    hardQs = h;
    easyQs = e;
    hardToEasy = hte;
    easyToHard = eth;
  }

  public String toString() {
    return "> Questions answered: " + answered
        + "> Questions switched from hard to easy: " + hardToEasy
        + "> Questions switched from easy to hard: " + easyToHard
        + "> Hard questions: " + hardQs
        + "> Easy questions: " + easyQs;
  }
}
