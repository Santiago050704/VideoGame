package model;


/**
 * This class contains most of the methods of the video game functionality. It contains the objects of the player and level classes.
 */
public class VideoGame {

  public static final int NUMBER_OF_PLAYERS = 20;
  public static final int NUMBER_OF_LEVELS = 10;

  private int width;
  private int length;

  private Player[] players;
  private Level[] levels;

  private String[] treasureNames = new String[50];

  /**
   * VideoGame: This constructor method allows to initialize the arrays of levels and players that are inside the VideoGame class.
   */
  public VideoGame() {
    players = new Player[NUMBER_OF_PLAYERS];
    levels = new Level[NUMBER_OF_LEVELS];
    levels[0] = new Level(0, 10, 1);
    levels[1] = new Level(1, 20, 1);
    levels[2] = new Level(2, 30, 1);
    levels[3] = new Level(3, 40, 2);
    levels[4] = new Level(4, 50, 2);
    levels[5] = new Level(5, 60, 2);
    levels[6] = new Level(6, 70, 3);
    levels[7] = new Level(7, 80, 3);
    levels[8] = new Level(8, 90, 5);
    levels[9] = new Level(9, 100, 5);
  }
  
  /**
   * setResolution: This method allows to set the resolution that the video game will have.
   * @param resolution: int: Integer that represents the resolution options that the video game has.
   */
  public void setResolution(int resolution) {
    switch (resolution) {
      case 1:
        width = 640;
        length = 480;
        break;
    
      case 2:
        width = 960;
        length = 540;
        break;
    
      case 3:
        width = 1280;
        length = 720;
        break;
    
      case 4:
        width = 1920;
        length = 1080;
        break;
    
      case 5:
        width = 2560;
        length = 1440;
        break;
    
      case 6:
        width = 3840;
        length = 2160;
        break;
    
      case 7:
        width = 7680;
        length = 4320;
        break;
    
      default:
        System.out.println("Invalid option.");
        break;
    }
  }

  /**
   * getWidth: This method returns the width of the resolution that the video game will have.
   * @return width: int: Width of the video game resolution.
   */
  public int getWidth() {
    return width;
  }

  /**
   * getLength: This method returns the length of the resolution that the video game will have.
   * @return length: int: Length of the video game resolution.
   */
  public int getLength() {
    return length;
  }
  
  /**
   * getLevels: This method returns the objects of the array of levels inside the video game.
   * @return levels: Level[]: Objects of the array of levels.
   */
  public Level[] getLevels() {
    return levels;
  }
  
  /**
   * addPlayer: This method allows to add players to the array of players within the video game, if the player does not yet exist or if 
   * there is still space available.
   * @param player: Player: Player already created to be added.
   * @return msj: String: Confirmation message if it was added or not.
   */
  public String addPlayer(Player player) {
    String msj = "";
    boolean wasAdded = false;
    boolean thePlayerAlreadyExists = false;
    for (int i = 0; i < NUMBER_OF_PLAYERS && !thePlayerAlreadyExists; i++) {
      if (players[i] != null && player.getNickname().equals(players[i].getNickname())) {
        thePlayerAlreadyExists = true;
      }
    }
    if (!thePlayerAlreadyExists) {
      for (int i = 0; i < NUMBER_OF_PLAYERS && !wasAdded; i++) {
        if (players[i] == null) {
          players[i] = player;
          wasAdded = true;
          msj = "The player was added successfully.";
        }
      }
      if (!wasAdded) {
        msj = "The player was not added. Maximum capacity of players reached.";
      }
    } else {
      msj = "The nickname already exists, so the player cannot be added.";
    }
    return msj;
  }

  /**
   * addEnemyToALevel: This method allows to add an enemy to a level if space is available.
   * @param enemy: Enemy: Enemy already created to be added.
   * @param levelId: int: Id of the level to which the enemy is to be added.
   * @return msj: String: Confirmation message if the enemy was added or not.
   */
  public String addEnemyToALevel(Enemy enemy, int levelId) {
    String msj = "";
    boolean wasAdded = false;
    int posLevel = searchLevelById(levelId);
    for (int i = 0; i < levels[posLevel].getEnemies().length && !wasAdded; i++) {
      if (levels[posLevel].getEnemies()[i] != null && levels[posLevel].getEnemies()[i].getName().equalsIgnoreCase(enemy.getName())) {
        wasAdded = true;
        msj = "The enemy already exists within the level.";
      }
    }
    if (!wasAdded) {
      if (posLevel != -1) {
        for (int i = 0; i < levels[posLevel].getEnemies().length && !wasAdded; i++) {
          if (levels[posLevel].getEnemies()[i] == null) {
            levels[posLevel].getEnemies()[i] = enemy;
            wasAdded = true;
            msj = "The enemy was added successfully.";
          }
        }
        if (!wasAdded) {
          msj = "Maximum capacity of enemies reached.";
        }
      } else {
        msj = "The level was not found.";
      }
    }
    return msj;
  }

  /**
   * confirmTypeOfEnemy: This method checks if the type of enemy entered matches any of those allowed by the video game.
   * @param type: String: Type of enemy entered by the user.
   * @return isFound: boolean: Confirmation status if the type entered matches or does not match any of the video game.
   */
  public boolean confirmTypeOfEnemy(String enemyType) {
    String[] enemyTypes = { "Ogre", "Abstract", "Boss", "Magic" };
    boolean isFound = false;
    for (int i = 0; i < enemyTypes.length && !isFound; i++) {
      if (enemyType.equalsIgnoreCase(enemyTypes[i])) {
        isFound = true;
      }
    }
    return isFound;
  }
  
  /**
   * addTreasureToALevel: This method allows to add a treasure to a level if space is available. In addition, the user can add several 
   * copies of the same treasure created.
   * @param treasure: Treasure: Treasure already created to be added.
   * @param levelId: int: Id of the level to which the enemy is to be added.
   * @param numberOfTreasures: int: Number of copies of the same treasure to be added.
   * @return msj: String: Confirmation message if the treasure or the amount of treasure was added.
   */
  public String addTreasureToALevel(Treasure treasure, int levelId, int numberOfTreasures) {
    String msj = "";
    int posLevel = searchLevelById(levelId);
    int counter = 0;
    int amountOfAddedTreasures = 0;
    if (posLevel != -1) {
      for (int i = 0; i < levels[posLevel].getTreasures().length; i++) {
        if (levels[posLevel].getTreasures()[i] == null) {
          counter++;
        }
        if (counter >= numberOfTreasures) {
          for (int j = 0; j < levels[posLevel].getTreasures().length && amountOfAddedTreasures != numberOfTreasures; j++) {
            if (levels[posLevel].getTreasures()[j] == null) {
              levels[posLevel].getTreasures()[j] = treasure;
              addNameOfTreasure(treasure.getName());
              amountOfAddedTreasures++;
              msj = "The amount of treasures was successfully added.";
            }
          }
        } else {
          msj = "There are not enough positions to add that amount of treasures. Space availability: " + counter;
        }
      }
    } else {
      msj = "The level was not found.";
    }
    return msj;
  }
  
  /**
   * addNameOfTreasure: This method allows you to add non-repeated treasure names to an array. This method is used to find the most 
   * repeated treasure in the video game.
   * @param treasureName: String: Treasure name to be added in case it is not repeated.
   */
  public void addNameOfTreasure(String treasureName) {
    boolean wasFound = false;
    for (int i = 0; i < treasureNames.length && !wasFound; i++) {
      if (treasureNames[i] != null && treasureNames[i].equalsIgnoreCase(treasureName)) {
        wasFound = true;
      }
    }

    if (!wasFound) {
      for (int i = 0; i < treasureNames.length && !wasFound; i++) {
        if (treasureNames[i] == null) {
          wasFound = true;
          treasureNames[i] = treasureName;
        }
        
      }
      
    }
  }

  /**
   * setScoreOfPlayer: This method allows to change the score of a player, if the player exists.
   * @param nickname: String: Nickname of the player to change the score.
   * @param score: int: New score that the player will have.
   * @return msj: String: Confirmation message if the score was changed or if the player does not exist to change the score.
   */
  public String setScoreOfPlayer(String nickname, int score) {
    String msj = "";
    boolean wasChanged = false;
    int posPlayer = searchPlayerByNickname(nickname);
    if (posPlayer != -1) {
      players[posPlayer].setScore(score);
      wasChanged = true;
      msj = "The player's score was modified.";
    } else {
      msj = "The player does not exist.";
    }
    return msj;
  }

  /**
   * levelUpForPlayer: This method allows to level up a player, if the player and the level exist.
   * @param nickname: String: Player's nickname to level up.
   * @param levelId: int: Id of the level to raise the player.
   * @return msj: String: Confirmation message if the player could be leveled up or if neither the player nor the level exists.
   */
  public String levelUpForPlayer(String nickname, int levelId) {
    String msj = "";
    boolean levelUp = false;
    int posPlayer = searchPlayerByNickname(nickname);
    int posLevel = searchLevelById(levelId);
    if (posPlayer != -1 && posLevel != -1) {
      if (players[posPlayer].getScore() >= levels[posLevel].getScoreToLevelUp()) {
        players[posPlayer].setLevel(levels[posLevel]);
        levelUp = true;
        msj = "The player was leveled up.";
      } else {
        int subtractionOfScores = levels[posLevel].getScoreToLevelUp() - players[posPlayer].getScore();
        msj = "The player does not have the necessary score to go up to this level. He/She needs " + subtractionOfScores
            + " points to go up.";
      }
    } else {
      msj = "The player or the level or both do not exist in the video game.";
    }
    return msj;
  }

  /**
   * reportTreasureAndEnemies: This method searches for and returns enemies and treasures within a level.
   * @param levelId: int: Id of the level where treasures and enemies will be searched.
   * @return msj: String: Message with treasures and enemies inside the level.
   */
  public String reportTreasuresAndEnemies(int levelId) {
    String msj = "";
    boolean wasFound = false;
    boolean wasFound1 = false;
    int posLevel = searchLevelById(levelId);
    if (posLevel != -1) {
      for (int i = 0; i < levels[posLevel].getTreasures().length; i++) {
        if (levels[posLevel].getTreasures()[i] != null) {
          msj += "Treasure: " + levels[posLevel].getTreasures()[i].getName() + ". \n";
          wasFound = true;
        }
      }
      if(wasFound == false){
        msj = "There are no treasures inside the level. \n";
      }

      for (int j = 0; j < levels[posLevel].getEnemies().length; j++) {
        if (levels[posLevel].getEnemies()[j] != null) {
          msj += "Enemy: " + levels[posLevel].getEnemies()[j].getName() + ". \n";
          wasFound1 = true;
        }
      }
      if(wasFound1 == false){
        msj += "There are no enemies inside the level.";
      }
    } else {
      msj = "The required level does not exist.";
    }
    return msj;
  }

  /**
   * reportQuantityOfATreasure: This method counts and returns the amount of treasure found within the video game.
   * @param treasureName: String: Name the treasure to count its amount in the video game.
   * @return quantityOfTreasure: int: Amount found of the treasure sought.
   */
  public int reportQuantityOfATreasure(String treasureName) {
    int quantityOfTreasure = 0;
    for (int i = 0; i < levels.length; i++) {
      for (int j = 0; j < levels[i].getTreasures().length; j++) {
        if (levels[i].getTreasures()[j] != null
            && levels[i].getTreasures()[j].getName().equalsIgnoreCase(treasureName)) {
          quantityOfTreasure++;
        }
      }
    }
    return quantityOfTreasure;
  }
  
  /**
   * reportQuantityOfAnEnemy: This method counts and returns the number of enemy encountered based on its type within the video game.
   * @param enemyType: Enemy type to count the amount within the video game.
   * @return quantityOfEnemy: Quantity found of the type of enemy sought.
   */
  public int reportQuantityOfAnEnemy(String enemyType) {
    int quantityOfEnemy = 0;
    for (int i = 0; i < levels.length; i++) {
      for (int j = 0; j < levels[i].getEnemies().length; j++) {
        if (levels[i].getEnemies()[j] != null && levels[i].getEnemies()[j].getEnemyType().equalsIgnoreCase(enemyType)) {
          quantityOfEnemy++;
        }
      }
    }
    return quantityOfEnemy;
  }

  /**
   * reportMostRepeatedTreasure: This method looks for the most repeated treasure within the video game. For this, it makes use of an 
   * array of the names of the non-repeated treasures to compare with each treasure in each level.
   * @return treasureNames[posMostRepeatedTreasure]: String: Name of the treasure that is repeated most often in the video game.
   */
  public String reportMostRepeatedTreasure() {
    int posMostRepeatedTreasure = 0;
    int[] quantityOfTreasures = new int[50];
    for (int i = 0; i < treasureNames.length; i++) {
      for (int j = 0; j < levels.length; j++) {
        for (int s = 0; s < levels[j].getTreasures().length; s++) {
          if (treasureNames[i] != null && levels[j].getTreasures()[s] != null
              && treasureNames[i].equalsIgnoreCase(levels[j].getTreasures()[s].getName())) {
            quantityOfTreasures[i]++;
          }
        }
      }
    }

    for (int i = 1; i < quantityOfTreasures.length; i++) {
      if (quantityOfTreasures[posMostRepeatedTreasure] < quantityOfTreasures[i]) {
        posMostRepeatedTreasure = i;
      }

    }

    return treasureNames[posMostRepeatedTreasure];
  }
  
  /**
   * reportEnemyWithTheHighestScore: This method returns the highest scoring enemy and its level within the video game. For this, it uses 
   * an array of the highest scoring enemies within each level, and then compares them with each other.
   * @return msj: String: Message that returns the highest scoring enemy and its level within the video game, or returns that there are 
   * no enemies within the video game, if so.
   */
  public String reportEnemyWithTheHighestScore() {
    String msj = "";
    int posEnemyWithHighestLevelScore = 0;
    Enemy[] enemiesWithMaxScore = new Enemy[10];
    int[] highestScoringEnemiesLevels = new int[10];
    for (int i = 0; i < 3; i++) {
      if (enemiesWithMaxScore[i] == null) {
        enemiesWithMaxScore[i] = levels[i].getEnemies()[0];
        highestScoringEnemiesLevels[i] = levels[i].getId();
      }
    }
    for (int i = 3; i < enemiesWithMaxScore.length; i++) {
      for (int j = 1; j < levels[i].getEnemies().length; j++) {
        if (levels[i].getEnemies()[posEnemyWithHighestLevelScore] != null && levels[i].getEnemies()[j] != null
            && levels[i].getEnemies()[posEnemyWithHighestLevelScore].getScoreToIncrease() < levels[i].getEnemies()[j]
                .getScoreToIncrease()) {
          posEnemyWithHighestLevelScore = j;
        }
      }
      enemiesWithMaxScore[i] = levels[i].getEnemies()[posEnemyWithHighestLevelScore];
      highestScoringEnemiesLevels[i] = levels[i].getId();
    }
    int posEnemyWithHighestScoreInGame = -1;
    boolean wasFound = false;
    for (int i = 0; i < enemiesWithMaxScore.length && !wasFound; i++) {
      if (enemiesWithMaxScore[i] != null) {
        posEnemyWithHighestScoreInGame = i;
        wasFound = true;
      }
    }
    if (posEnemyWithHighestScoreInGame != -1) {
      for (int j = posEnemyWithHighestScoreInGame + 1; j < enemiesWithMaxScore.length; j++) {
        if (enemiesWithMaxScore[j] != null && enemiesWithMaxScore[posEnemyWithHighestScoreInGame]
            .getScoreToIncrease() < enemiesWithMaxScore[j].getScoreToIncrease()) {
          posEnemyWithHighestScoreInGame = j;
        }
      }
      msj = "The highest scoring enemy in the video game is "
          + enemiesWithMaxScore[posEnemyWithHighestScoreInGame].getName() + " and is in the level "
          + highestScoringEnemiesLevels[posEnemyWithHighestScoreInGame];
    } else {
      msj = "There are no enemies inside the video game.";
    }
    return msj;
  }

  /**
   * calculateNumberOfConsonantsInEnemyNames: This method counts and returns the consonants of the names of all enemies in the video 
   * game. For this, an array of type char is used to count letter by letter.
   * @return msj: String: Message that returns the number of consonants of the names of all enemies in the video game.
   */
  public String calculateNumberOfConsonantsInEnemyNames() {
    String msj = "";
    int consonantCounter = 0;
    StringBuilder enemyNames = new StringBuilder();
    char[] namesInChar;
    for (int i = 0; i < levels.length; i++) {
      for (int j = 0; j < levels[i].getEnemies().length; j++) {
        if (levels[i].getEnemies()[j] != null) {
          enemyNames.append(levels[i].getEnemies()[j].getName().toLowerCase());
        }
      }
    }

    namesInChar = new char[enemyNames.length()];
    namesInChar = enemyNames.toString().toCharArray();
    for (int i = 0; i < namesInChar.length; i++) {
      if (namesInChar[i] != 'a' && namesInChar[i] != 'e' && namesInChar[i] != 'i' && namesInChar[i] != 'o'
          && namesInChar[i] != 'u' && namesInChar[i] != ' ') {
        consonantCounter++;
      }
    }
    msj = "The number of consonants in the names of enemies is " + consonantCounter;

    return msj;
  }
  
  /**
   * topPlayers: This method organizes players within the array of players in descending order according to their score.
   * @return msj: String: Message that returns the array of players organized with their names and score, in descending order according 
   * to the score, or returns that there are no players inside the video game in case there are.
   */
  public String topPlayers() {
    StringBuilder msj = new StringBuilder();
    Player auxiliary = null;
    boolean findsFullPosition = false;
    for (int i = 0; i < players.length - 1; i++) {
      for (int j = 0; j < players.length - 1; j++) {
        if (players[j] != null && players[j + 1] != null && players[j].getScore() < players[j + 1].getScore()) {
          auxiliary = players[j + 1];
          players[j + 1] = players[j];
          players[j] = auxiliary;
        }
      }
    }

    for (int i = 0; i < players.length && !findsFullPosition; i++) {
      if (players[i] != null) {
        findsFullPosition = true;
      }
    }

    if (findsFullPosition == true) {
      for (int i = 0; i < 5; i++) {
        if (players[i] != null) {
          msj.append((i + 1) + ". Nickname: " + players[i].getNickname() + " - Score: " + players[i].getScore() + " \n");
        }
      }
    } else {
      msj.append("There are no players inside the video game.");
    }
    return msj.toString();
  }
  

  /**
   * searchLevelById: This method allows to search for a level by its Id.
   * @param levelId: int: Id of the level to be searched.
   * @return pos: int: Position of the level within the array of levels.
   */
  public int searchLevelById(int levelId) {
    int pos = -1;
    boolean isFound = false;
    for (int i = 0; i < NUMBER_OF_LEVELS && !isFound; i++) {
      if (levels[i] != null && levels[i].getId() == levelId) {
        pos = i;
        isFound = true;
      }
    }
    return pos;
  }

  /**
   * searchPlayerByNickname: This method allows to search for a player by his/her nickname.
   * @param nickname: String: Nickname of the player to search.
   * @return pos: int: Position of the player within the array of players.
   */
  public int searchPlayerByNickname(String nickname) {
    int pos = -1;
    boolean isFound = false;
    for (int i = 0; i < NUMBER_OF_PLAYERS && !isFound; i++) {
      if (players[i] != null && players[i].getNickname().equals(nickname)) {
        pos = i;
        isFound = true;
      }
    }
    return pos;
  }

  

}