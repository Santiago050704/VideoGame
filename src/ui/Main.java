package ui;

import java.util.Random;
import java.util.Scanner;

import model.Enemy;
import model.EnemyType;
import model.Treasure;
import model.VideoGame;
import model.Player;

/**
 * This class allows executing the program and contains all the view methods with which the user will interact.
 */
public class Main{

  private Scanner input;
  private VideoGame videoGame;
  private Random random;

  /**
   * Main: This constructor method initializes the objects of the Scanner, VideoGame and Random classes.
   */
  public Main(){
    input = new Scanner(System.in);
    videoGame = new VideoGame();
    random = new Random();
  }


  public static void main(String[] args) {
    Main main = new Main();
    int option = 0;

    int resolution = main.chooseResolution();
    main.videoGame.setResolution(resolution);

    do {

      option = main.getOptionShowMenu();
      main.executeOption(option);

    } while (option != 0);

    main.input.close();
  }

  /**
   * chooseResolution: This method displays and allows to choose the resolution that the video game will have.
   * @return resolution: int: Option as an integer that represents the resolution that the video game will have.
   */
  public int chooseResolution() {
    int resolution = 0;
    System.out.println("Choose a resolution to run the game.");
    System.out.println(
    "1. Standart Definition (SD) \n" + 
    "2. Quarter of High Definition (QHD) \n" + 
    "3. High Definition (HD) \n" + 
    "4. Full High Definition (Full HD) \n" +
    "5. Quad High Definition (2K) \n" + 
    "6. Ultra High Definition (4K) \n" + 
    "7. Ultra High Definition 8K (8K)");
    resolution = input.nextInt();
    return resolution;
  }
  
  /**
   * registerPlayer: This method asks for the data needed to create a player.
   */
  public void registerPlayer() {
    String nickname;
    String name;

    System.out.println("Enter the nickname of the player.");
    nickname = input.next();
    System.out.println("Enter the name of the player.");
    name = input.next();      
    Player player = new Player(nickname, name, videoGame.getLevels()[0]);
    System.out.println(videoGame.addPlayer(player));  
  }

  /**
   * registerEnemyToALevel: This method asks for the information needed to create and add an enemy to a level.
   */
  public void registerEnemyToALevel() {
    String name;
    String typeStr;
    EnemyType type = null;
    int scoreToDecrease;
    int scoreToIncrease;
    int levelId;

    System.out.println("Enter the name of the enemy.");
    name = input.next();
    System.out.println("Enter the type of the enemy: OGRE, ABSTRACT, BOSS and MAGIC.");
    typeStr = input.next();
    
    
    System.out.println("Enter the score that the enemy decreases.");
    scoreToDecrease = input.nextInt();
    System.out.println("Enter the score that the enemy increases.");
    scoreToIncrease = input.nextInt();

    if (videoGame.confirmTypeOfEnemy(typeStr)) {
      type = EnemyType.valueOf(typeStr.toUpperCase());

      Enemy enemy = new Enemy(name, type, scoreToDecrease, scoreToIncrease, random.nextInt(videoGame.getWidth()), random.nextInt(videoGame.getLength()));
      System.out.println("Enter the Id of the level to which the enemy will be added.");
      levelId = input.nextInt();
      System.out.println(videoGame.addEnemyToALevel(enemy, levelId));
    } else {
      System.out.println("The enemy type does not exist.");
    }

     
  }

  /**
   * registerTreasureToALevel: This method asks for the information needed to create and add a treasure to a level.
   */
  public void registerTreasureToALevel() {
    String name;
    String url;
    int scoreToIncrease;
    int levelId;
    int numberOfTreasures;

    System.out.println("Enter the name of the treasure.");
    name = input.next();
    System.out.println("Enter the URL of the treasure image.");
    url = input.next();
    System.out.println("Enter the score that the treasure increases.");
    scoreToIncrease = input.nextInt();

    Treasure treasure = new Treasure(name, url, scoreToIncrease, random.nextInt(videoGame.getWidth()), random.nextInt(videoGame.getLength()));
    System.out.println("Enter the Id of the level to which the treasure will be added.");
    levelId = input.nextInt();
    System.out.println("Enter the amount you want to add of this treasure.");
    numberOfTreasures = input.nextInt();
    System.out.println(videoGame.addTreasureToALevel(treasure, levelId, numberOfTreasures)); 
  }

  /**
   * modifyScoreOfPlayer: This method asks for the necessary information to be able to modify a player's score.
   */
  public void modifyScoreOfPlayer() {
    String nickname;
    int score;

    System.out.println("Enter the player's nickname to change the score.");
    nickname = input.next();
    System.out.println("Enter the new score.");
    score = input.nextInt();
    System.out.println(videoGame.setScoreOfPlayer(nickname, score));
  }
  
  /**
   * uiLevelUpForPlayer: This method asks for the necessary information to level up a player.
   */
  public void uiLevelUpForPlayer() {
    String nickname;
    int levelId;

    System.out.println("Enter the nickname of the player to level up.");
    nickname = input.next();
    System.out.println("Enter the Id of the level to raise the player.");
    levelId = input.nextInt();
    System.out.println(videoGame.levelUpForPlayer(nickname, levelId));
  }
  
  /**
   * uiReportTreasureAndEnemies: This method asks for the necessary information to display the treasures and enemies within a level.
   */
  public void uiReportTreasuresAndEnemies() {
    int levelId = 0;
    System.out.println("Enter the level to display its treasures and enemies.");
    levelId = input.nextInt();

    System.out.println(videoGame.reportTreasuresAndEnemies(levelId));
  }
  
  /**
   * uiReportQuantityOfATreasure: This method asks for the necessary information to display the amount of treasure found within the video 
   * game.
   */
  public void uiReportQuantityOfATreasure() {
    String treasureName = "";
    int quantityOfTreasure;

    System.out.println("Enter the name of the treasure to know its amount in the video game.");
    treasureName = input.next();

    quantityOfTreasure = videoGame.reportQuantityOfATreasure(treasureName);

    System.out.println("The amount of the treasure " + treasureName + " is " + quantityOfTreasure);
  }

  /**
   * uiReportQuantityOfAnEnemy: This method asks for the necessary information to display the amount of an enemy encountered within the 
   * video game.
   */
  public void uiReportQuantityOfAnEnemy() {
    String enemyType = "";
    int quantityOfEnemy;

    System.out.println("Enter the type of the enemy to know its amount in the video game.");
    enemyType = input.next();

    quantityOfEnemy = videoGame.reportQuantityOfAnEnemy(enemyType);

    System.out.println("The amount of enemy type " + enemyType + " is " + quantityOfEnemy);
  }

  /**
   * uiReportMostRepeatedTreasure: This method prints on the screen which is the most repeated treasure within the video game.
   */
  public void uiReportMostRepeatedTreasure() {
    System.out.println("The most repeated treasure in the video game is " + videoGame.reportMostRepeatedTreasure());
  }

  /**
   * uiReportEnemyWithTheHighestScore: This method prints on the screen the content of the method that reports the highest scoring enemy 
   * and its level.
   */
  public void uiReportEnemyWithTheHighestScore() {
    System.out.println(videoGame.reportEnemyWithTheHighestScore());
  }

  /**
   * uiCalculateNumberOfConsonantsInEnemyNames: This method prints on the screen the content of the method that reports the number of 
   * consonants of the enemies' names in the video game.
   */
  public void uiCalculateNumberOfConsonantsInEnemyNames() {
    System.out.println(videoGame.calculateNumberOfConsonantsInEnemyNames());
  }

  /**
   * uiTopPlayers: This method prints on the screen the content of the method that reports the top 5 players of the video game.
   */
  public void uiTopPlayers() {
    System.out.println(videoGame.topPlayers());
  }

  /**
   * getOptionShowMenu: This method displays and allows to choose the option that the game will execute.
   * @return option: int: Integer representing the option chosen by the user.
   */  
  public int getOptionShowMenu() {
      int option = 0;
      System.out.println("<<<<< Welcome to TrikiTruku >>>>>");
      System.out.println(
      "(0) Exit. \n" +
      "(1) Create a player. \n" +
      "(2) Register enemy to a level. \n" +
      "(3) Register treasure to a level. \n" +
      "(4) Modify a player's score. \n" +
      "(5) Increase level for a player. \n" + 
      "(6) Report treasures and enemies in a level. \n" + 
      "(7) Report the amount of treasure found at all levels. \n" + 
      "(8) Report the quantity encountered of an enemy at all levels. \n" + 
      "(9) Report the most repeated treasure at all levels. \n" + 
      "(10) Report the highest scoring enemy and the level where it is located. \n" + 
      "(11) Report the number of consonants found in the names of enemies in the video game. \n" + 
      "(12) Report the top 5 players according to the score.");
      option = input.nextInt();
      return option;
    }
  
    /**
     * executeOption: This method executes the option chosen by the user.
     * @param option: int: Integer representing the option chosen by the user.
     */
    public void executeOption(int option){

    switch (option) {
      case 0:
        System.out.println("Exit program.");
        break;
        
      case 1:
        System.out.println("<<< Create a player >>>");
        registerPlayer();
				break; 

      case 2:
        System.out.println("<<< Register enemy to a level >>>");
        registerEnemyToALevel();
				break; 

      case 3:
        System.out.println("<<< Register treasure to a level >>>");
        registerTreasureToALevel();
				break; 

      case 4:
        System.out.println("<<< Modify a player's score >>>");
        modifyScoreOfPlayer();
				break; 

      case 5:
        System.out.println("<<< Increase level for a player >>>");
        uiLevelUpForPlayer();
        break;

      case 6:
        System.out.println("<<< Report treasures and enemies in a level >>>");
        uiReportTreasuresAndEnemies();
        break;

      case 7:
        System.out.println("<<< Report the amount of treasure found at all levels >>>");
        uiReportQuantityOfATreasure();
        break;

      case 8:
        System.out.println("<<< Report the quantity encountered of an enemy at all levels >>>");
        uiReportQuantityOfAnEnemy();
        break;

      case 9:
        System.out.println("<<< Report the most repeated treasure at all levels >>>");
        uiReportMostRepeatedTreasure();
        break;

      case 10:
        System.out.println("<<< Report the highest scoring enemy and the level where it is located >>>");
        uiReportEnemyWithTheHighestScore();
        break;

      case 11:
        System.out.println("<<< Report the number of consonants found in the names of enemies in the video game >>>");
        uiCalculateNumberOfConsonantsInEnemyNames();
        break;

      case 12:
        System.out.println("<<< Report the top 5 players according to the score >>>");
        uiTopPlayers();
        break;

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}
}
