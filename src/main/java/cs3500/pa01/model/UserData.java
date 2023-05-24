package cs3500.pa01.model;

/**
 * Represents a user's data
 */
public class UserData {
  // how will I store UserData in my .sr file?
  // { User Stats }
  // read? write back to file?
  public int answered;
  public int easyToHard;
  public int hardToEasy;
  public int hardQs;
  public int easyQs;

  /**
   * Constructor
   */
  public UserData(int answered, int easyToHard, int hardToEasy, int hardQs, int easyQs) {
    this.answered = answered;
    this.easyToHard = easyToHard;
    this.hardToEasy = hardToEasy;
    this.hardQs = hardQs;
    this.easyQs = easyQs;
  }

  public void increaseEasyToHard() {
    easyToHard++;
  }

  public void increaseHardToEasy() {
    hardToEasy++;
  }

  public void increaseAnswered() {
    answered++;
  }

  /**
   * Converts a user's data back to a string to write to the .sr file
   *
   * @return a string of the data
   */
  public String toString() {
    return "> Questions answered: " + answered + "\n"
        + "> Questions switched from easy to hard: " + easyToHard + "\n"
        + "> Questions switched from hard to easy: " + hardToEasy + "\n"
        + "> Hard questions: " + hardQs + "\n"
        + "> Easy questions: " + easyQs;
  }
}
