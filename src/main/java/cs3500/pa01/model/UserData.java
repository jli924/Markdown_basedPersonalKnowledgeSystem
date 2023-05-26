package cs3500.pa01.model;

/**
 * Represents a user's data
 */
public class UserData {
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

  /**
   * Updates the number of easy questions
   */
  public void updateEasyQs(int count) {
    easyQs = count;
  }

  /**
   * Updates the number of hard questions
   */
  public void updateHardQs(int count) {
    hardQs = count;
  }

  /**
   * Increases count of the number of questions switched from easy to hard
   */
  public void increaseEasyToHard() {
    easyToHard++;
  }

  /**
   * Increases count of the number of questions switched from hard to easy
   */
  public void increaseHardToEasy() {
    hardToEasy++;
  }

  /**
   * Increases count of the number of questions answered
   */
  public void increaseAnswered() {
    answered++;
  }

  /**
   * Converts a user's data back to a string to write to the .sr file
   *
   * @return a string of the data
   */
  public String toString() {
    return "{ User Stats }\n"
        + "> Questions answered: " + answered + "\n"
        + "> Questions switched from easy to hard: " + easyToHard + "\n"
        + "> Questions switched from hard to easy: " + hardToEasy + "\n"
        + "> Hard questions: " + hardQs + "\n"
        + "> Easy questions: " + easyQs;
  }
}
