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
  private Map<String, List<Integer>> allScores;
  // For some waves you will need to add more private instance variables here!



  public MapGameStatsCalculator(Scanner scoreInput) {
    gameCounts = new HashMap<>();
    Map<String, Integer> scoreMap = new HashMap<>();
    allScores = new HashMap<>();
    while(scoreInput.hasNext()) {
      
      String name = scoreInput.next();
      int score = scoreInput.nextInt();
      scoreMap.put(name, score);
      
      if(allScores.containsKey(name))
      {
        allScores.get(name).add(score);
      }
      else
      {
        allScores.put(name, new ArrayList<>(score));
        allScores.get(name).add(score);
      }

      if(gameCounts.containsKey(name))
      {
        gameCounts.put(name, gameCounts.get(name) + 1);
      }
      else
      {
        gameCounts.put(name, 1); 
      }
    }
    for(String x : allScores.keySet())
    {
      Collections.sort(allScores.get(x), Collections.reverseOrder());
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

    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    checkPerson(person);
    return allScores.get(person).get(0);

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
    Map<String, Integer> newMap = new HashMap<>();

    for(String x : allScores.keySet())
    {
      newMap.put(x, highScore(x));
    }

    int hScore = 0;
    String hName = "";

    for(String x : newMap.keySet())
    { 
      if(newMap.get(x) > hScore)
      {
        hScore = newMap.get(x);
        hName = x;
      }
      else if(newMap.get(x) == hScore)
      {
        if(x.compareTo(hName) < 0){hName = x;}
      }
    }

    return hName;
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
    List<Integer> scoresList = null;

    for(String x : allScores.keySet())
    {
      if(x.equals(person)){scoresList = allScores.get(x);}
    }

    double avg;
    int tot = 0;
    int scoreSize = scoresList.size();
    for(int i = 0; i < scoreSize; i++)
    {
      tot += scoresList.get(i);
    }

    avg = (tot * 1.0) / (scoreSize * 1.0);
    return avg;
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
    Map<String, Double> avgMap = new HashMap<>();
    for( String x : allScores.keySet() )
    {
      avgMap.put(x, getAverageScore(x));
    }

    double hScore = 0;
    String hName = "";

    for(String x : avgMap.keySet())
    { 
      if(avgMap.get(x) > hScore)
      {
        hScore = avgMap.get(x);
        hName = x;
      }
      else if(avgMap.get(x) == hScore)
      {
        if(x.compareTo(hName) < 0){hName = x;}
      }
    }

    return hName;
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
    
    checkPerson(person);
    List<Integer> tList = null;

    for( String x : allScores.keySet() )
    {
      if(x.equals(person)){tList = allScores.get(x);}
    }

    Collections.sort(tList);
    return tList;
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