package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ViewTest {
  View view = new View();
  String welcomeUser = "Welcome!!" +
      "\n" +
      "How many questions would you like to study today?";

  @Test
  public void testWelcomeUser() {
    assertEquals(view.welcomeUser(), welcomeUser);
  }
}
