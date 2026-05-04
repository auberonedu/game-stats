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
  public void gameCountReturnsNumberOfGamesForPerson() {
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

  @Test
  public void gameCountWhatHappensWhenWeHaveOnlyOnePerson() {
    // Arrange
    String scoreData = "Nupur 10\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Nupur");

    // Assert
    assertEquals(1, actual); //one score, gamecount = 1

  }

  @Test
  public void gameCountWhatHappensWhenNupurGetsOnlyNegativeScores() {
    // Arrange
    String scoreData = "Nupur -10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur -40\n"
        + "Baya 50\n"
        + "Nupur -20\n"
        + "Baya 60\n"
        + "Nupur -30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Nupur");

    // Assert
    assertEquals(4, actual);
  }

  // highScore tests

  @Test
  public void highScoreReturnsHighestScoreForPerson() {
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

  @Test
  public void highScoreReturnsHighestValueWhenOnlyNegativeScores() {
    // Arrange
    String scoreData = "Nupur -10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur -40\n"
        + "Baya 50\n"
        + "Nupur -20\n"
        + "Baya 60\n"
        + "Nupur -30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.highScore("Nupur");

    // Assert
    assertEquals(-10, actual);
  }

  @Test
  public void highScoreReturnsHighestScoreEvenWhenOnlyOneScore() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Baya -40\n"
        + "Baya 50\n"
        + "Baya -20\n"
        + "Baya 60\n"
        + "Baya -30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.highScore("Nupur");

    // Assert
    assertEquals(10, actual);
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

  @Test
  public void getAverageScoreOnlyOnePerson() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Nupur 40\n"
        + "Nupur 20\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    double actual = calculator.getAverageScore("Nupur");

    // Assert
    assertEquals(25.0, actual, 0.001);
  }

  @Test
  public void getAverageScoreAllScoresNegative() {
    // Arrange
    String scoreData = "Nupur -10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur -40\n"
        + "Baya 50\n"
        + "Nupur -20\n"
        + "Baya 60\n"
        + "Nupur -30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    double actual = calculator.getAverageScore("Nupur");

    //Assert
    assertEquals(-25, actual, 0.001);
  }

  @Test
  public void getAverageScoreMultipleScoresAreZero() {
    // Arrange
    String scoreData = "Nupur 10\n"
        + "Baya 30\n"
        + "Xinting 25\n"
        + "Nupur 40\n"
        + "Baya 50\n"
        + "Nupur -20\n"
        + "Nupur 0\n"
        + "Nupur 0\n"
        + "Nupur 0\n"
        + "Nupur 0\n"
        + "Nupur 0\n"
        + "Baya 60\n"
        + "Nupur 60\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    double actual = calculator.getAverageScore("Nupur");

    //Assert
    assertEquals(10, actual, 0.001);
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

  @Test
  public void highestAverageScorerOnlyOnePerson() {
  // Arrange
    String scoreData = "Nupur 10\n"
        + "Nupur 30\n"
        + "Nupur 25\n"
        + "Nupur 40\n"
        + "Nupur 50\n"
        + "Nupur 20\n"
        + "Nupur 60\n"
        + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Nupur", actual);
  }

  @Test
  public void highestAverageScorerBestScoreHasOnlyNegativeScores() {
    // Arrange
    String scoreData = "Nupur -10\n"
        + "Baya -30\n"
        + "Xinting -10\n"
        + "Nupur -40\n"
        + "Baya -50\n"
        + "Nupur -20\n"
        + "Baya -60\n"
        + "Nupur -40\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Xinting", actual);
  }

  @Test
  public void highestAverageScorerTiedForHighestScoreAlphebeticalWins() {
    // Arrange
    String scoreData = "Nupur 50\n"
        + "Baya 40\n"
        + "Baya 60\n"
        + "Nupur 50\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  @Test
  public void highestAverageOneOfTheNamesOnTheListIsNoneAndItsLowerAvg() {
    // Arrange
    String scoreData = "None 50\n"
        + "Baya 60\n"
        + "Baya 60\n"
        + "Baya 60\n"
        + "None 50\n"
        + "None 50\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Baya", actual);
  }

  @Test
  public void highestAverageOneOfTheNamesOnTheListIsNoneAndItsHigherAvg() {
    // Arrange
    String scoreData = "None 70\n"
        + "Baya 60\n"
        + "Baya 60\n"
        + "Baya 60\n"
        + "None 70\n"
        + "None 70\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("None", actual);
  }

  @Test
  public void highestAverageNoneCanBeAWinner() {
    // Arrange
    String scoreData = "Zaya -70\n"
        + "Zaya -60\n"
        + "Zaya -60\n"
        + "None 60\n"
        + "Tim 50\n"
        + "Zaya 60\n"
        + "Zaya 60\n"
        + "Reggie 50\n"
        + "Nupur 50\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("None", actual);
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