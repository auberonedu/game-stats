import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameStatsRunner {

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);


    //added logic w1
    if (!gameCounts.containsKey(name)){
      gameCounts.put(name, 0);
    }
    gameCounts/put(name, gameCounts.get(name) + 1);

    //oberride W1
    @Override 
    public int gameCount(String person){
      checkPerson(person);
      return gameCounts.get(person);
    }




    // Ask for filenames until we successfully create a calculator.
    GameStatsCalculator calculator = makeCalculator(console);

    // Keep asking the user what they want to do until they choose to exit.
    boolean keepGoing = true;

    while (keepGoing) {
      printMenu();

      System.out.print("Enter your choice: ");
      String choice = console.nextLine();

      try {
        if (choice.equals("1")) {
          // Show how many games a person played.
          System.out.print("Enter person name: ");
          String person = console.nextLine();

          int count = calculator.gameCount(person);
          System.out.println(person + " played " + count + " games.");
        } else if (choice.equals("2")) {
          // Show a person's highest score.
          System.out.print("Enter person name: ");
          String person = console.nextLine();

          int highScore = calculator.highScore(person);
          System.out.println(person + "'s high score is " + highScore + ".");
        } else if (choice.equals("3")) {
          // Show the person with the highest single score.
          String person = calculator.highestScorer();
          System.out.println("The highest scorer is " + person + ".");
        } else if (choice.equals("4")) {
          // Show a person's average score.
          System.out.print("Enter person name: ");
          String person = console.nextLine();

          double average = calculator.getAverageScore(person);
          System.out.println(person + "'s average score is " + average + ".");
        } else if (choice.equals("5")) {
          // Show the person with the highest average score.
          String person = calculator.highestAverageScorer();
          System.out.println("The highest average scorer is " + person + ".");
        } else if (choice.equals("6")) {
          // Show a person's scores in sorted order.
          System.out.print("Enter person name: ");
          String person = console.nextLine();

          List<Integer> scores = calculator.sortedScores(person);
          System.out.println(person + "'s sorted scores are " + scores + ".");
        } else if (choice.equals("7")) {
          // Exit the loop.
          keepGoing = false;
        } else {
          // Handle menu choices that are not valid.
          System.out.println("Invalid choice. Please enter a number from 1 to 7.");
        }
      } catch (NullPointerException e) {
        // This happens if the calculator is asked about a null person.
        System.out.println(e.getMessage());
      } catch (NoSuchElementException e) {
        // This happens if the person is missing, or if there is no score data.
        System.out.println(e.getMessage());
      } catch (UnsupportedOperationException e) {
        // This happens when a method still has its starter-code exception.
        System.out.println("This functionality does not work because you have not implemented it yet.");
      }

      System.out.println();
    }

    System.out.println("Goodbye!");

    // We are done reading from the console.
    console.close();
  }

  /**
   * Prompts the user for a filename until a file with that name can be opened.
   *
   * @param console a Scanner reading from the console
   * @return a GameStatsCalculator built from the file data
   */
  private static GameStatsCalculator makeCalculator(Scanner console) {
    while (true) {
      try {
        // Ask the user for the name of the file that contains the score data.
        System.out.print("Enter score data filename: ");
        String filename = console.nextLine();

        // Create a Scanner that reads from the file.
        Scanner fileScanner = new Scanner(new File(filename));

        // Create the calculator using the file Scanner.
        GameStatsCalculator calculator = new MapGameStatsCalculator(fileScanner);

        // We are done reading from the file, so we can close this Scanner.
        fileScanner.close();

        return calculator;
      } catch (FileNotFoundException e) {
        // This happens if Java cannot find a file with the given name.
        System.out.println("File not found. Please try another filename.");
      }
    }
  }

  /**
   * Prints the menu of options for the user.
   */
  private static void printMenu() {
    System.out.println("Choose an option:");
    System.out.println("1. Get a person's game count");
    System.out.println("2. Get a person's high score");
    System.out.println("3. Get the highest scorer");
    System.out.println("4. Get a person's average score");
    System.out.println("5. Get the highest average scorer");
    System.out.println("6. Get a person's sorted scores");
    System.out.println("7. Exit");
  }
}