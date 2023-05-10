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
    public scoreboard() {

         score = 0;
         trys = 0;
    }
    public void setScore(int score) {
        this.score = score;

    }

    public void setTrys(int trys) {
        this.trys = trys;
    }

    public int getScore() {
        return score;
    }

    public int getTrys() {
        return trys;
    }
    public void addScore() {
         score++;
    }
    public void addTrys() {
        trys++;
    }
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
