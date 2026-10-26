import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
public class MapGameStatsCalculator implements GameStatsCalculator {
	private Map<String, Integer> gameCounts;
		// For some waves you will need to add more private instance variables here!
	private Map<String, Integer> highScores;
	private Map<String, Integer> scoreTotal;
	private Map<String, List<Integer>> scoreMap;

	// <<< Constructor >>> 
	//IMPORTANT NOTE: due to ADHD, I struggle to think while trying to talk to someone.
	//For each method, we wrote pseudocode and the reconnected. 
	//In some cases, we decided to go about different paths.
	public MapGameStatsCalculator(Scanner scoreInput) {
		gameCounts = new HashMap<>();
		highScores = new HashMap<>();
		scoreTotal = new HashMap<>();
		scoreMap = new HashMap<>();

		while(scoreInput.hasNext()) {
			String name = scoreInput.next();
			int score = scoreInput.nextInt();
			if (gameCounts.containsKey(name)) {
    			gameCounts.put(name, gameCounts.get(name) + 1);
			} else {
    			gameCounts.put(name, 1);
			}

			if (!highScores.containsKey(name)) {
				highScores.put(name, score);
			} else if (highScores.get(name) < score) {
				highScores.put(name, score);
			}

			if(!scoreTotal.containsKey(name)) {
				scoreTotal.put(name, score);
			} else {
				scoreTotal.put(name, scoreTotal.get(name) + score);
			}

			if (!scoreMap.containsKey(name)) {
				List<Integer> scores = new ArrayList<>();
				scores.add(score);
				scoreMap.put(name, scores);
			} else {
				scoreMap.get(name).add(score);
			}

		}
		scoreInput.close();
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
		String highestScorer = "";
		int highestScore = -1000000000;
		boolean firstScore = false;
		for (String name : highScores.keySet()) {
    		int score = highScores.get(name);
			if (firstScore == false) {
				highestScore = score;
				highestScorer = name;
				firstScore = true;	
			} else if(highestScore < score) {
				highestScore = score;
				highestScorer = name;
			} else if (highestScore == score) {
            	if (name.compareTo(highestScorer) < 0) { 
					//seems a bit unfair... should be 1st to score it, sets the record
                highestScorer = name;
            	}
			}
		} return highestScorer;
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
		//note: partner created a new List, for highestScore()
		//after discussion, we decided to post the solution we each came up with.  
		checkPerson(person);
		return 1.0 * scoreTotal.get(person) / gameCounts.get(person);
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
		String highestAverageScorer = "";
		double avg = 0;
		double highestAvg = 0;
		boolean isFirst = false;
		for(String name : scoreTotal.keySet()) {
			avg = getAverageScore(name);
			if (isFirst == false) {
				highestAvg = getAverageScore(name);
				highestAverageScorer = name;
				isFirst = true;
			} else if (highestAvg < avg){
				highestAvg = avg;
				highestAverageScorer = name;
			} else if (highestAvg == avg) {
				if (name.compareTo(highestAverageScorer) < 0) { 
					highestAverageScorer = name;
				}
			}
		}
		return highestAverageScorer;
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
		Collections.sort(scoreMap.get(person));
		return scoreMap.get(person);
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