import java.util.List;
import java.util.NoSuchElementException;

public interface GameStatsCalculator {

  /**
   * Returns the number of games a person has played.
   * 
   * @param person the name of the person to query
   * @return the number of games the person played
   * @throws NoSuchElementException if the person does not exist in the score data
   */
    public int gameCount(String person);

  /**
   * Returns the highest score a person has gotten.
   * 
   * @param person the name of the person to query
   * @return the highest score the person received
   @throws NoSuchElementException if the person does not exist in the score data
   */


    public int highScore(String person);

  /**
   * Returns the name of the person who has the highest score.
   * 
   * If multiple people are tied for the highest score, returns the person whose name
   * appears first lexicographically (alphabetically).
   * 
   * @return the name of the person with the highest score
   * @throws new NoSuchElementException if there is no score data
   */
    public String highestScorer();


  /**
   * Returns the average score a person has gotten.
   * 
   * @param person the name of the person to query
   * @return the average score the person received
   * @throws NoSuchElementException if the person does not exist in the score data
   */
    public double getAverageScore(String person);
  
  /**
   * Returns the name of the person who has the highest average score.
   * 
   * If multiple people are tied for the highest average score, returns the person whose name
   * appears first lexicographically (alphabetically).
   * 
   * @return the name of the person with the highest average score
   * @throws NoSuchElementException if there is no score data
   */
    public String highestAverageScorer();


  /**
   * Returns a list of the scores a person has gotten, sorted in ascending order (lowest to highest).
   * 
   * @param person the name of the person to query
   * @return a list of the scores the person received in ascending order
   * @throws NoSuchElementException if the person does not exist in the score data
   */
    public List<Integer> sortedScores(String person);
} 