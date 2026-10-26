import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MapGameStatsCalculator implements GameStatsCalculator {

  /**
   * A map of names to # of games completed.
   * 
   * For example if Nupur completed 3 games, Baya completed 5 games, and Xinting completed one game,
   * the map would look something like:
   * 
   * {
   *  "Nupur": 3,
   *  "Baya": 5,
   *  "Xinting": 1
   * }
   */
  private Map<String, Integer> gameCounts;
  private Map<String, Integer> highScores;
  private Map<String, Integer> totalScores;
  private Map<String, List<Integer>> userScoresLists;

  // For some waves you will need to add more private instance variables here!



  public MapGameStatsCalculator(Scanner scoreInput) {
    gameCounts = new HashMap<>();
    highScores = new HashMap<>();
    totalScores = new HashMap<>();
    userScoresLists = new HashMap<>();

    while(scoreInput.hasNext()) {
      String name = scoreInput.next();
      int score = scoreInput.nextInt();

    /// TODO: add logic here to use the name and score to fill your map(s)!
      if (!highScores.containsKey(name) || score > highScores.get(name)) {
        highScores.put(name, score);
      }
      gameCounts.put(name, gameCounts.getOrDefault(name, 0) + 1);
      totalScores.put(name, totalScores.getOrDefault(name, 0) + score);

      if (!userScoresLists.containsKey(name)) {
        userScoresLists.put(name, new ArrayList<>());
      }
      userScoresLists.get(name).add(score);
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
    // TODO: remove this exception once you have implemented your method!
   checkPerson(person);
   return gameCounts.get(person);
    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
  
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
    // TODO: remove this exception once you have implemented your method!
    checkPerson(person);
    return highScores.get(person);

    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
  
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
    // TODO: remove this exception once you have implemented your method!
  // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    checkScoreData();

    String bestUser = null;
    int maxScore = Integer.MIN_VALUE;

    for (String person : highScores.keySet()) {
      int currentScore = highScores.get(person);

      if (currentScore > maxScore) {
        maxScore = currentScore;
        bestUser = person;
      } else if (currentScore == maxScore) {
        if (bestUser == null || person.compareTo(bestUser) < 0) {
          bestUser = person;
        }
      }
    }
    return bestUser;
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
    // TODO: remove this exception once you have implemented your method!
    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    checkPerson(person);
    return (double) totalScores.get(person) / gameCount(person);
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
    // TODO: remove this exception once you have implemented your method!
    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    checkScoreData();

    String bestUser = null;
    double maxAverage = -Double.MAX_VALUE;
    for (String person : gameCounts.keySet()) {
      double currentAverage = getAverageScore(person);

      if(currentAverage > maxAverage) {
        maxAverage = currentAverage;
        bestUser = person;
      } else if (currentAverage == maxAverage) {
        if (bestUser == null || person.compareTo(bestUser) < 0) {
          bestUser = person;
        }
      }
    }
    return bestUser;
  }

  /**
   * Returns a list of the scores a person has gotten, sorted in ascending order (lowest to highest).
   * 
   * @param person the name of the person to query
   * @return a list of the scores the person received in ascending order
   * @throws NoSuchElementException if the person does not exist in the score data
   */
  @Override
  public List<Integer> sortedScores(String person) {
    // TODO: remove this exception once you have implemented your method!
    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    checkPerson(person);
    List<Integer> sortedList = new ArrayList<>(userScoresLists.get(person));
    java.util.Collections.sort(sortedList);
    return sortedList;
  }
  
  /**
   * Throws an exception if the given person is null or was not present in the score data.
   * Does nothing if the person is present.
   * 
   * @param person the person to check
   * @throws NullPointerException if the person String is null
   * @throws NoSuchElementException if the person does not appear in the gameCounts
   */
  private void checkPerson(String person) {
    if(person == null) {
      throw new NullPointerException("Cannot query for null person");
    }
    if(!gameCounts.containsKey(person)) {
      throw new NoSuchElementException("Person " + person + " does not exist in the score data");
    }
  }

  /**
   * Throws an exception if there is no score data in gameCounts. Does nothing if score data exists.
   * 
   * @throws NoSuchElementException if there is no score data
   */
  private void checkScoreData() {
    if(gameCounts.isEmpty()) {
      throw new NoSuchElementException("No score data parsed");
    }
  }
}