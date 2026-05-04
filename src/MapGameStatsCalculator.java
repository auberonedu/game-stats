import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

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
  // private Map<String, Integer> gameCounts;
  // private Map<String, Integer> highScores;
  private Map<String, ArrayList<Integer>> allMap;

  // For some waves you will need to add more private instance variables here!



  public MapGameStatsCalculator(Scanner scoreInput) {
    // gameCounts = new HashMap<>();
    // highScores = new HashMap<>();
    allMap = new HashMap<>();

    while(scoreInput.hasNext()) {
      String name = scoreInput.next();
      int score = scoreInput.nextInt();

      //logic for filling allMap
      //was struggling to figure out how to get Avgs when i was looking ahead 
      //so i paused and starting breaking down how to structure the class
      //realized that if i made a hashmap with a string and an arraylist then i could use for EVERYTHING
      //added a jpeg of my whiteboard process
      if(allMap.containsKey(name)) {
        ArrayList<Integer> list = allMap.get(name);
        list.add(score);
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(score);
        allMap.put(name, list);
      }

      // //logic for filling gamecounts
      // if(gameCounts.containsKey(name)) {
      //   int count = gameCounts.get(name);
      //   count++;
      //   gameCounts.put(name, count);
      // } else {
      //   int count = 1;
      //   gameCounts.put(name, count);
      // }

      // //logic for filling highScores
      // if(highScores.containsKey(name)) {

      // } else {
      //   int highest = highScores.get(name);
        
      // }
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

    int returnInt = allMap.get(person).size(); //the size of the list at the key
    return returnInt;

    // int returnInt = gameCounts.get(person);
    // return returnInt;
    
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
    ArrayList<Integer> list = allMap.get(person);
    Collections.sort(list); //sort ascending
    int returnInt = list.get(list.size() - 1); //grab the last value (highest)
    return returnInt;
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
    // set string variable to "";
    String winnerName = "";
    // set an int variable to JFK's birthday (my go-to spottable incorrect value)
    int winnerScore = 5291917;
    // loop through each piece of the hashmap
    for (String name : allMap.keySet()) {
      if (winnerName.equals("")) {      
        // set the int variable to highScore(person)
        winnerScore = highScore(name);
        // set the string variable to the current key/person
        winnerName = name;
      // if the string variable is not ""
      } else {      
        if (highScore(name) > winnerScore) {
          // set int variable to highScore(person)
          winnerScore = highScore(name);
          // set string variable to current key/person
          winnerName = name;
        } else if (highScore(name) == winnerScore) {// if current highScore(person) = int variable
          if (name.compareTo(winnerName) < 0 ) {
            winnerName = name; //if less than 0 then name is first
          }
        }      
      }
    }
    if (winnerScore == 5291917) {
      return "When everyone's super no one is!";
    }
    return winnerName;
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

    //locate the array by using the person as the key
    //pull in the size of the hashmap array
      //this is our divide by int
    int divideBy = gameCount(person);
    if (divideBy == 0) { //just in case somehow gameCount gives us a 0 in an edge case i can't see
      return 0;
    }

    //pull in the all the numbers
      //we take each of them and add them together in a total int
      //for each
    int total = 5291917;
    double returnAvg = total;
    List<Integer> list = allMap.get(person);
    for (int score : list) {
      if (total == 5291917) {
        total = score;
      } else {
        total += score;
      }
    }
    
    //take the total int and divide it by the divide by int
    if (total == 5291917) {
      return 0;
    } else {
      returnAvg = (double) total/divideBy;
      //looked at the testing method and there's like a "tolerance" in the ASSERT argument that helps handle the weirdness"
      //returnAvg = Math.round(returnAvg * 100.0) / 100.0;
    }

    //return this variable
    return returnAvg;
      //this gives a double that is our average
      //this will require us to convert the data type
      //and fixing the weirdness of doubles having long .0000etc strings (probably some sort of rounding)
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
    //make a string variable to hold the return bestName
    String bestName = "None";

    //make a string variable to hold the currentName
    String currentName = "None";

    //make a variable to hold the bestAvg
    double bestAvg = 0;
    //make a variable to hold the currentAvg
    double currentAvg = 0;

    for (String name : allMap.keySet()) {
      currentName = name;
      currentAvg = getAverageScore(name);

      //if the current name is None, set the best and move on 
      if (bestName.equals("None")) {
        bestName = currentName;
        bestAvg = currentAvg;
        continue;

      } else {
        //if current Avg is higher than bestAvg
        if (currentAvg > bestAvg) {
          //update currentAvg to be bestAvg
          bestAvg = currentAvg;
          //update bestName to currentName
          bestName = currentName;
          continue;
        }
        //if current is lower, continue
        if (currentAvg < bestAvg) {
          continue;
        }
        //if current is same as bestAvg
        if (currentAvg == bestAvg) {
          //compare the names to eachother
          if (currentName.compareTo(bestName) < 0) {
            //update BestName with alphabetically first name
            bestName = currentName;
            continue;
          }
          else {
            continue;
          }
        }
        continue;
      } 
    }
    //return name with highest avg
    return bestName;
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
    throw new UnsupportedOperationException("Unimplemented method 'sortedScores'");

    // Uncomment this and have it as your first line once you remove the UnsupportedOperationException
    //checkPerson(person);
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
    if(!allMap.containsKey(person)) {
      throw new NoSuchElementException("Person " + person + " does not exist in the score data");
    }
  }

  /**
   * Throws an exception if there is no score data in gameCounts. Does nothing if score data exists.
   * 
   * @throws NoSuchElementException if there is no score data
   */
  private void checkScoreData() {
    if(allMap.isEmpty()) {
      throw new NoSuchElementException("No score data parsed");
    }
  }
}