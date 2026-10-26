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

 @Test
  public void gameCountReturnsOneForPersonWithSingleGame() {
    String scoreData = "Nupur 10\n" + "Baya 30\n" + "Xinting 25\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    int actual = calculator.gameCount("Xinting");
    assertEquals(1, actual);
  }

  @Test
  public void gameCountWorksWithOnlyOnePerson() {
    String scoreData = "Rohan 5\n" + "Rohan 7\n" + "Rohan 9\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    int actual = calculator.gameCount("Rohan");
    assertEquals(3, actual);
  }

  @Test
  public void gameCountCountsGamesWithNegativeScores() {
    String scoreData = "Rohan -5\n" + "Rohan -10\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    int actual = calculator.gameCount("Rohan");
    assertEquals(2, actual);
  }

  @Test
  public void gameCountIsCaseSensitive() {
    String scoreData = "Nupur 10\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    Executable act = () -> calculator.gameCount("nupur");
    assertThrows(NoSuchElementException.class, act);
  }

  @Test
  public void gameCountThrowsNoSuchElementExceptionForEmptyScoreData() {
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(""));
    Executable act = () -> calculator.gameCount("Nupur");
    assertThrows(NoSuchElementException.class, act);
  }

  @Test
  public void highScoreReturnsOnlyScoreForPersonWithSingleGame() {
    String scoreData = "Nupur 10\n" + "Xinting 25\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(25, calculator.highScore("Xinting"));
  }

  @Test
  public void highScoreHandlesAllNegativeScores() {
    String scoreData = "Rohan -50\n" + "Rohan -3\n" + "Rohan -20\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(-3, calculator.highScore("Rohan"));
  }

  @Test
  public void highScoreHandlesMixOfNegativeAndPositiveScores() {
    String scoreData = "Rohan -50\n" + "Rohan 0\n" + "Rohan -20\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(0, calculator.highScore("Rohan"));
  }

  @Test
  public void highScoreFindsHighestWhenItIsTheFirstGame() {
    String scoreData = "Baya 99\n" + "Baya 1\n" + "Baya 50\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(99, calculator.highScore("Baya"));
  }

  @Test
  public void highScoreIsTrackedSeparatelyPerPerson() {
    String scoreData = "Nupur 10\n" + "Baya 100\n" + "Nupur 20\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(20, calculator.highScore("Nupur"));
  }
 
  @Test
  public void highestScorerReturnsOnlyPersonWhenOnePerson() {
    String scoreData = "Rohan 5\n" + "Rohan 7\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Rohan", calculator.highestScorer());
  }

  @Test
  public void highestScorerBreaksTiesAlphabetically() {
    String scoreData = "Xinting 50\n" + "Baya 50\n" + "Nupur 50\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Baya", calculator.highestScorer());
  }

  @Test
  public void highestScorerWorksWithAllNegativeScores() {
    String scoreData = "Nupur -10\n" + "Baya -30\n" + "Xinting -5\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Xinting", calculator.highestScorer());
  }

  @Test
  public void highestScorerUsesSingleBestGameNotTotal() {
    String scoreData = "Nupur 40\n" + "Nupur 40\n" + "Nupur 40\n" + "Baya 45\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Baya", calculator.highestScorer());
  }

   @Test
  public void getAverageScoreReturnsScoreForSingleGame() {
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner("Xinting 25\n"));
    assertEquals(25.0, calculator.getAverageScore("Xinting"), 0.001);
  }

  @Test
  public void getAverageScoreDoesNotUseIntegerDivision() {
    String scoreData = "Rohan 3\n" + "Rohan 4\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(3.5, calculator.getAverageScore("Rohan"), 0.001);
  }

  @Test
  public void getAverageScoreHandlesNegativeScores() {
    String scoreData = "Rohan -10\n" + "Rohan -20\n" + "Rohan 0\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(-10.0, calculator.getAverageScore("Rohan"), 0.001);
  }

  @Test
  public void getAverageScoreHandlesNonTerminatingDecimal() {
    String scoreData = "Baya 1\n" + "Baya 1\n" + "Baya 2\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals(4.0 / 3.0, calculator.getAverageScore("Baya"), 0.001);
  }
  
  @Test
  public void highestAverageScorerReturnsOnlyPersonWhenOnePerson() {
    String scoreData = "Rohan 5\n" + "Rohan 15\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Rohan", calculator.highestAverageScorer());
  }

  @Test
  public void highestAverageScorerBreaksTiesAlphabetically() {
    String scoreData = "Xinting 20\n" + "Nupur 10\n" + "Nupur 30\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Nupur", calculator.highestAverageScorer());
  }

  @Test
  public void highestAverageScorerWorksWithAllNegativeAverages() {
    String scoreData = "Nupur -10\n" + "Nupur -20\n" + "Baya -5\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Baya", calculator.highestAverageScorer());
  }

  @Test
  public void highestAverageScorerCanDifferFromHighestScorer() {
    String scoreData = "Baya 100\n" + "Baya 0\n" + "Nupur 60\n" + "Nupur 60\n";
    GameStatsCalculator calculator = new MapGameStatsCalculator(new Scanner(scoreData));
    assertEquals("Nupur", calculator.highestAverageScorer());
  }
}