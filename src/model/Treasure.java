package model;

/**
 * This class allows initializing and creating Treasure type objects.
 */
public class Treasure {
  private String name;
  private String url;
  private int scoreToIncrease;
  private int posX;
  private int posY;

  /**
   * Treasure: This constructor method allows to initialize the attributes of a Treasure type object.
   * @param name: String: Name of the treasure.
   * @param url: String: URL of the treasure image.
   * @param scoreToIncrease: int: Score that will increase the treasure.
   * @param posX: int: Position that the treasure will have in the width of the screen.
   * @param posY: int: Position that the treasure will have in the length of the screen.
   */
  public Treasure(String name, String url, int scoreToIncrease, int posX, int posY) {
    this.name = name;
    this.url = url;
    this.scoreToIncrease = scoreToIncrease;
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * getScoreToIncrease: This method returns the score that increases the treasure.
   * @return scoreToIncrease: int: Score that increases the treasure.
   */
  public int getScoreToIncrease() {
    return scoreToIncrease;
  }

  /**
   * getName: This method returns the name of the treasure.
   * @return name: String: Name of the treasure.
   */
  public String getName() {
    return name;
  }

}