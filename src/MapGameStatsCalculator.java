import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MapGameStatsCalculator implements GameStatsCalculator {

  /**
   * A map of names to # of games completed.
   */
  private Map<String, Integer> gameCounts;

  /**
   * A map of names to their highest score.
   */
  private Map<String, Integer> highScores;

  /**
   * A map of names to their total score.
   */
  private Map<String, Integer> totalScores;

  /**
   * A map of names to all of their scores.
   */
  private Map<String, List<Integer>> scoresByPerson;

  public MapGameStatsCalculator(Scanner scoreInput) {
    gameCounts = new HashMap<>();
    highScores = new HashMap<>();
    totalScores = new HashMap<>();
    scoresByPerson = new HashMap<>();

    while (scoreInput.hasNext()) {
      String name = scoreInput.next();
      int score = scoreInput.nextInt();

      // Count how many games this person has played.
      if (!gameCounts.containsKey(name)) {
        gameCounts.put(name, 0);
      }
      gameCounts.put(name, gameCounts.get(name) + 1);

      // Track this person's highest score.
      if (!highScores.containsKey(name) || score > highScores.get(name)) {
        highScores.put(name, score);
      }

      // Track this person's total score.
      if (!totalScores.containsKey(name)) {
        totalScores.put(name, 0);
      }
      totalScores.put(name, totalScores.get(name) + score);

      // Track all scores for this person.
      if (!scoresByPerson.containsKey(name)) {
        scoresByPerson.put(name, new ArrayList<>());
      }
      scoresByPerson.get(name).add(score);
    }
  }

  /**
   * Returns the number of games a person has played.
   * 
   * @param person the name of the person to query
   * @return the number of games the person played
   * @throws NoSuchElementException if the person does not exist in the score data
   */
  @Override
  public int gameCount(String person) {
    checkPerson(person);
    return gameCounts.get(person);
  }

  /**
   * Returns the highest score a person has gotten.
   * 
   * @param person the name of the person to query
   * @return the highest score the person received
   * @throws NoSuchElementException if the person does not exist in the score data
   */
  @Override
  public int highScore(String person) {
    checkPerson(person);
    return highScores.get(person);
  }

  /**
   * Returns the name of the person who has the highest score.
   * 
   * If multiple people are tied for the highest score, returns the person whose name
   * appears first lexicographically (alphabetically).
   * 
   * @return the name of the person with the highest score
   * @throws NoSuchElementException if there is no score data
   */
  @Override
  public String highestScorer() {
    checkScoreData();

    String bestPerson = null;
    int bestScore = Integer.MIN_VALUE;

    for (String person : highScores.keySet()) {
      int score = highScores.get(person);

      if (bestPerson == null ||
          score > bestScore ||
          score == bestScore && person.compareTo(bestPerson) < 0) {
        bestPerson = person;
        bestScore = score;
      }
    }

    return bestPerson;
  }

  /**
   * Returns the average score a person has gotten.
   * 
   * @param person the name of the person to query
   * @return the average score the person received
   * @throws NoSuchElementException if the person does not exist in the score data
   */
  @Override
  public double getAverageScore(String person) {
    checkPerson(person);

    int total = totalScores.get(person);
    int count = gameCounts.get(person);

    return (double) total / count;
  }

  /**
   * Returns the name of the person who has the highest average score.
   * 
   * If multiple people are tied for the highest average score, returns the person whose name
   * appears first lexicographically (alphabetically).
   * 
   * @return the name of the person with the highest average score
   * @throws NoSuchElementException if there is no score data
   */
  @Override
  public String highestAverageScorer() {
    checkScoreData();

    String bestPerson = null;
    double bestAverage = Double.NEGATIVE_INFINITY;

    for (String person : gameCounts.keySet()) {
      double average = getAverageScore(person);

      if (bestPerson == null ||
          average > bestAverage ||
          average == bestAverage && person.compareTo(bestPerson) < 0) {
        bestPerson = person;
        bestAverage = average;
      }
    }

    return bestPerson;
  }

  /**
   * Returns a list of the scores a person has gotten, sorted in ascending order
   * from lowest to highest.
   * 
   * @param person the name of the person to query
   * @return a list of the scores the person received in ascending order
   * @throws NoSuchElementException if the person does not exist in the score data
   */
  @Override
  public List<Integer> sortedScores(String person) {
    checkPerson(person);

    List<Integer> scoresCopy = new ArrayList<>(scoresByPerson.get(person));
    Collections.sort(scoresCopy);

    return scoresCopy;
  }

  /**
   * Throws an exception if the given person is null or was not present in the score data.
   * Does nothing if the person is present.
   * 
   * @param person the person to check
   * @throws NullPointerException if the person String is null
   * @throws NoSuchElementException if the person does not appear in gameCounts
   */
  private void checkPerson(String person) {
    if (person == null) {
      throw new NullPointerException("Cannot query for null person");
    }
    if (!gameCounts.containsKey(person)) {
      throw new NoSuchElementException("Person " + person + " does not exist in the score data");
    }
  }

  /**
   * Throws an exception if there is no score data in gameCounts.
   * Does nothing if score data exists.
   * 
   * @throws NoSuchElementException if there is no score data
   */
  private void checkScoreData() {
    if (gameCounts.isEmpty()) {
      throw new NoSuchElementException("No score data parsed");
    }
  }
}