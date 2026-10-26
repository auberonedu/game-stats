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
  public void gameCountWorksWithOnlyOnePerson() {
    // Arrange
    String scoreData = "Mia 5 \n" + "Mia 15 \n" + "Mia 25\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    // Act
    int actual = calculator.gameCount("Mia");

    // Assert
    assertEquals(3, actual);
  }

  @Test 
  public void gameCountIncludesNegativeScores() {
    // Arranges
    String scoreData = "Leo -10 \n" + "Ana 5\n" + "Leo -20\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act
    int actual = calculator.gameCount("Leo");

    // Assert
    assertEquals(2, actual);
  }


  // highScore tests
  @Test 
  public void highScoreReturnsHighestWhenAllScoresNegative() {
    // Arrange
    String scoreData = "Leo -10\n" + "Leo -3\n" + "Leo -7\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act 
    int actual = calculator.highScore("Leo");

    // Assert
    assertEquals(-3, actual);
  }

  @Test 
  public void highScoreFindsHighestInLastGame() {
    // Arranges
    String scoreData = "Mia 5\n" + "Mia 12\n" + "Mia 40\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

    // Act 
    int actual = calculator.highScore("Mia");

    // Assert
    assertEquals(40, actual);
  }

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

  // highestScorer tests
  @Test 
  public void highestScorerWorksWhenAllScoresNegative() {
    // Arrange
    String scoreData = "Leo -10\n" + "Ana -3\n" + "Mia -7\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    String actual = calculator.highestScorer();

    // Assert
    assertEquals("Ana", actual);
  }

  @Test 
  public void highestScorerWorksWithOnlyOnePerson() {
    // Arrange
    String scoreData = "Mia 5\n" + "Mia 15\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    String actual = calculator.highestScorer();

    // Assert
    assertEquals("Mia", actual);
  }

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
  public void getAverageScoreWorksWithNegativeScores() {
     // Arrange
    String scoreData = "Leo -4\n" + "Leo 10\n" + "Leo -3\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    double actual = calculator.getAverageScore("Leo");

    // Assert
    assertEquals(1.0, actual, 0.001);
  }

  @Test 
  public void getAverageScoreReturnsDecimalAverage() {
     // Arrange
    String scoreData = "Mia 10\n" + "Mia 15\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    double actual = calculator.getAverageScore("Mia");

    // Assert
    assertEquals(12.5, actual, 0.001);
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
  public void highestAverageScorerIsNotJustHighestSingleScore() {
     // Arrange
    String scoreData = "Ana 100\n" + "Ana  0\n" + "Leo 60\n" + "Leo 60\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Leo", actual);
  }

  @Test 
  public void highestAverageScorerWorksWhenAllScoresNegative() {
     // Arrange
    String scoreData = "Ana -10\n" + "Ana  -20\n" + "Leo -1\n" + "Leo -3\n";
     GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));

     // Act
    String actual = calculator.highestAverageScorer();

    // Assert
    assertEquals("Leo", actual);
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