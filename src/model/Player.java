package model;

/**
 * This class allows initializing and creating Player type objects.
 */
public class Player {
  private String nickname;
  private String name;
  private int score;
  private int numberOfLives;
  private Level level;

  /**
   * Player: This constructor method allows to initialize the attributes of a Player type object.
   * @param nickname: String: Player's nickname.
   * @param name: String: Player's name.
   * @param level: Level: Level with which the player will start.
   */
  public Player(String nickname, String name, Level level) {
    this.nickname = nickname;
    this.name = name;
    this.score = 10;
    this.numberOfLives = 5;
    this.level = level;
  }

  /**
   * getNickname: This method returns the player's nickname.
   * @return nickname: String: Player's nickname.
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * getScore: This method returns the player's score.
   * @return score: int: Player's score.
   */
  public int getScore() {
    return score;
  }

  /**
   * setLevel: This method allows the player's level to be reset.
   * @param level: Level: New level to which the player will be changed.
   */
  public void setLevel(Level level) {
    this.level = level;
  }
  
  /**
   * setScore: This method allows the player's score to be reset.
   * @param score: int: New score that the player will have.
   */
  public void setScore(int score) {
    this.score = score;
  }

}