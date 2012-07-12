package com.UnsettlingTrend.games.PacMan;

public class Score 
{
    private String _name;
    private Integer _score;
    
    public Score()
    {
    }

    Score(String name, Integer score) {
         this.Score(score);
        this.Name(name);
    }
    public void Score(String name, Integer score)
    {
        this.Score(score);
        this.Name(name);
    }
    
    public void Score(Integer score)
    {
        _score = score;
    }
    public Integer Score()
    {
        return _score;
    }
    public void Name(String name)
    {
        _name = name;
    }
    public String Name()
    {
        return _name;
    }

}
