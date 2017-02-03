package nl.gerardverbeek.util;

public class Option {
    private final double val;

    public Option(double val){
        this.val = val;
    }

    public Double getVal(){return val;}
    public Long getLongVal(){return (long)val;}

}
