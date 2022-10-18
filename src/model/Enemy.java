package model;

/**
 * This class allows initializing and creating Enemy type objects.
 */
public class Enemy {
  private String name;
  private EnemyType type;
  private int scoreToDecrease;
  private int scoreToIncrease;
  private int posX;
  private int posY;

  /**
   * Enemy: This constructor method allows to initialize the attributes of a Enemy type object.
   * @param name: String: Name of the enemy.
   * @param type: EnemyType: Enemy type.
   * @param scoreToDecrease: int: Score that the enemy subtracts.
   * @param scoreToIncrease: int: Score that the enemy adds up.
   * @param posX: int: Position that the enemy will have in the width of the screen.
   * @param posY: int: Position that the enemy will have in the length of the screen.
   */
  public Enemy(String name, EnemyType type, int scoreToDecrease, int scoreToIncrease, int posX, int posY) {
    this.name = name;
    this.type = type;
    this.scoreToDecrease = scoreToDecrease;
    this.scoreToIncrease = scoreToIncrease;
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * getEnemyType: This method returns the type of the enemy.
   * @return type: String: Type of the enemy. Ogre, Abstract, Boss, Magic.
   */
  public String getEnemyType() {
    return type.toString();
  }

  /**
   * getScoreToDecrease: This method returns the score subtracted by the enemy.
   * @return scoreToDecrease: int: Score subtracted by the enemy.
   */
  public int getScoreToDecrease() {
    return scoreToDecrease;
  }

  /**
   * getName: This method returns the name of the enemy.
   * @return name: String: Name of the enemy.
   */
  public String getName() {
    return name;
  }

  /**
   * getScoreToIncrease: This method returns the score that the enemy increases.
   * @return scoreToIncrease: int: Score that the enemy increases.
   */
  public int getScoreToIncrease() {
    return scoreToIncrease;
  }

}
