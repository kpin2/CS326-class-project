/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * scoreboard.java - This is the main class for scoreboard
 * It keeps track of the score and the trys
 *
 * Produced: 4/15/2023
 *
 * @author Zakaria Lazzouni
 * */

/**
 * Class to keep track of scores and number of attempts
 */
public class scoreboard {

    int score;
    int trys;
    public String[] results = {
            "You got 0/5. Try again!",
            "You got 1/5. Try again!",
            "You got 2/5. Try again!",
            "You got 3/5. Bronze Medal!",
            "You got 4/5. Silver Medal!",
            "You got 5/5. Gold Medal!"
    };

    /**
     * Constructor class, initialize score and trys to 0
     */
    public scoreboard() {

         score = 0;
         trys = 0;
    }
    /**
     * Helper method to set the score variable
     * @param score - keeps track of number of correct answers
     */
    public void setScore(int score) {
        this.score = score;

    }

    /**
     * Helper method to set the trys variable
     * @param trys - keeps track of number of attempts
     */
    public void setTrys(int trys) {
        this.trys = trys;
    }

    /**
     * Helper function to retrieve score value
     * @return int of variable score
     */
    public int getScore() {
        return score;
    }

    /**
     * Helper function to retrieve trys value
     * @return int of variable trys
     */
    public int getTrys() {
        return trys;
    }

    /**
     * Helper function to increment score value
     */
    public void addScore() {
         score++;
    }

    /**
     * Helper function to increment trys value
     */
    public void addTrys() {
        trys++;
    }

    /**
     * Helper function to retrieve msg about the score card
     * @return String with information on score
     */
    public String getresult() {
        String msg = "Try Again!";
        if(score == 3) {
            msg = "You got a Bronze Medal!";
        }
        else if (score == 4)  {
            msg = "You got a Silver Medal!";
        }
        else if (score == 5) {
            msg = "You got a Gold Medal!";
        }
        return "You got " + score + " / " + trys + "\n" + msg;
    }


    /**
     * Helper function to retrieve msg about message to display beside planets
     * @return String with information on score
     */
    public String getPlanetResult () {
        String msg;
        if (trys == 0) {
            msg="";
        }
        else {
            msg="RESULTS: " + score + " / " + trys;
        }
        return msg;
    }

}
