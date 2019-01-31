package components;

public class ScoreProfile {
    private String name;
    private int score;

    public ScoreProfile(String n, int s){
        name = n;
        score = s;
    }

    public void setName(String name1){
        name = name1;
    }

    public void setScore(int score1){
        score = score1;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }
}
