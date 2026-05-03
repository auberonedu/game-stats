import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class MapGameStatsCalculatorTest {

  // gameCount tests

  @Test
  public void gameCountReturnsNumberOfGamesForPersonNupur() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Nupur");

    // Assert
    assertEquals(4, actual);
  }

  @Test
  public void gameCountThrowsNullPointerExceptionForNullPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.gameCount(null);

    // Assert
    assertThrows(NullPointerException.class, act);
  }

  @Test
  public void gameCountThrowsNoSuchElementExceptionForMissingPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.gameCount("Rohan");

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  //Custom tests for gameCount()
  
  //Can't figure this out
  /*
  @Test
  public void gameCountCustomTestEmptyString()
  {
    String scoreData = "Nupur\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";

    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    Executable act = () -> calculator.gameCount("");

    assertThrows(NoSuchElementException.class, act);
  }
  */
  // highScore tests

  @Test
  public void gameCountReturnsNumberOfGamesForPersonXinting() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Xinting");

    // Assert
    assertEquals(1, actual);
  }

  @Test
  public void gameCountReturnsNumberOfGamesForPersonBaya() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Baya");

    // Assert
    assertEquals(3, actual);
  }

  @Test
  public void highScoreReturnsHighestScoreForPersonNupur() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.highScore("Nupur");

    // Assert
    assertEquals(40, actual);
  }

  @Test
  public void highScoreThrowsNullPointerExceptionForNullPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.highScore(null);

    // Assert
    assertThrows(NullPointerException.class, act);
  }

  @Test
  public void highScoreThrowsNoSuchElementExceptionForMissingPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.highScore("Rohan");

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  //Custom tests for highScore()

  @Test
  public void highScoreReturnsHighestScoreForPersonXinting() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.highScore("Xinting");

    // Assert
    assertEquals(25, actual);
  }

  @Test
  public void highScoreAllScoresSetToZero() {
    // Arrange
    String scoreData = "Nupur 0\n"
        + "Baya 0\n"
        + "Xinting 0\n"
        + "Nupur 0\n"
        + "Baya 0\n"
        + "Nupur 0\n"
        + "Baya 0\n"
        + "Nupur 0\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.highScore("Baya");

    // Assert
    assertEquals(0, actual);
  }

  // highestScorer tests

  @Test
  public void highestScorerReturnsPersonWithHighestScore() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  @Test
  public void highestScorerThrowsNoSuchElementExceptionForEmptyScoreData() {
    // Arrange
    String scoreData = "";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.highestScorer();

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  //Custom tests for highestScorer()

  @Test
  public void highestScorerReturnsPersonWhenHighScoresTiedInAlphabeticalOrder(){
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 60\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  // getAverageScore tests

  @Test
  public void getAverageScoreReturnsAverageScoreForPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    double actual = calculator.getAverageScore("Nupur");

    // Assert
    assertEquals(25.0, actual, 0.001);
  }

  @Test
  public void getAverageScoreThrowsNullPointerExceptionForNullPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.getAverageScore(null);

    // Assert
    assertThrows(NullPointerException.class, act);
  }

  @Test
  public void getAverageScoreThrowsNoSuchElementExceptionForMissingPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.getAverageScore("Rohan");

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  // highestAverageScorer tests

  @Test
  public void highestAverageScorerReturnsPersonWithHighestAverageScore() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  @Test
  public void highestAverageScorerThrowsNoSuchElementExceptionForEmptyScoreData() {
    // Arrange
    String scoreData = "";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.highestAverageScorer();

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  //Custom tests for highestAverageScorer()

  @Test
  public void highestAverageScorerTiedScoresReturnsAlphabeticalOrder() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 10\n"
        + "Nupur 40\n"
        + "Baya 40\n"
        + "Nupur 20\n"
        + "Baya 20\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  // sortedScores tests

  @Test
  public void sortedScoresReturnsScoresInAscendingOrder() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    List<Integer> actual = calculator.sortedScores("Nupur");

    // Assert
    assertEquals(List.of(10, 20, 30, 40), actual);
  }

  @Test
  public void sortedScoresThrowsNullPointerExceptionForNullPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.sortedScores(null);

    // Assert
    assertThrows(NullPointerException.class, act);
  }

  @Test
  public void sortedScoresThrowsNoSuchElementExceptionForMissingPerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur 20\n"
        + "Baya 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    Executable act = () -> calculator.sortedScores("Rohan");

    // Assert
    assertThrows(NoSuchElementException.class, act);
  }

  // Add more tests here!
}