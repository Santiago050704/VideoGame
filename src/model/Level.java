package model;

/**
 * This class allows initializing and creating Level type objects.
 */
public class Level {

  public static final int NUMBER_OF_TREASURES = 5;
  
  private int id;
  private int scoreToLevelUp;
  private String difficulty;

  Enemy[] enemies;
  Treasure[] treasures;

  /**
   * Level: This constructor method allows to initialize the attributes of a Level type object.
   * @param id: int: Level Id.
   * @param scoreToLevelUp: int: Score to move up to the next level.
   * @param numberOfEnemies: int: Number of enemies that the level will have.
   */
  public Level(int id, int scoreToLevelUp, int numberOfEnemies) {
    this.id = id;
    this.scoreToLevelUp = scoreToLevelUp;
    enemies = new Enemy[numberOfEnemies];
    treasures = new Treasure[NUMBER_OF_TREASURES];
  }
  
  /**
   * calculateLevelDifficulty: This method allows to calculate the difficulty of the level based on the score increased by treasures and 
   * the score decreased by enemies.
   */
  public void calculateLevelDifficulty() {
    int sumOfScoresTreasures = 0;
    int sumOfScoresEnemies = 0;
    for (int i = 0; i < NUMBER_OF_TREASURES; i++) {
      sumOfScoresTreasures += treasures[i].getScoreToIncrease();
    }
    for (int i = 0; i < enemies.length; i++) {
      sumOfScoresEnemies += enemies[i].getScoreToDecrease();
    }
    if (sumOfScoresTreasures > sumOfScoresEnemies) {
      difficulty = "Low";
    } else if (sumOfScoresTreasures < sumOfScoresEnemies) {
      difficulty = "High";
    } else {
      difficulty = "Medium";
    }
  }
  
  /**
   * getDifficulty: This method returns the difficulty of the level.
   * @return difficulty: String: Difficulty of the level.
   */
  public String getDifficulty() {
    calculateLevelDifficulty();
    return difficulty;
  }

  /**
   * getId: This method returns the Id of the level.
   * @return id: int: Level Id.
   */
  public int getId() {
    return id;
  }

  /**
   * getScoreToLevelUp: This method returns the score to move up to the level.
   * @return scoreToLevelUp: int: Score to move up to the level.
   */
  public int getScoreToLevelUp() {
    return scoreToLevelUp;
  }

  /**
   * getEnemies: This method returns the objects of the array of enemies. 
   * @return enemies: Enemy[]: Objects of the array of enemies.
   */
  public Enemy[] getEnemies() {
    return enemies;
  }

  /**
   * getTreasures: This method returns the objects of the array of treasures.
   * @return treasures: Treasure[]: Objects of the array of treasures.
   */
  public Treasure[] getTreasures() {
    return treasures;
  }

  
}