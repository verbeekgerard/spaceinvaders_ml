package nl.gerardverbeek.util;

public class Option {
    private final double val;

    public Option(double val){
        this.val = val;
    }

    public Option(boolean val){
        if(val){
            this.val = 1;
        } else {
            this.val = 0;
        }
    }

    public Double getVal(){return val;}
    public int getIntVal(){return (int) val;}
    public Long getLongVal(){return (long)val;}
    public boolean getBooleanVal(){
        if(val>0){
            return true;
        }else{
            return false;
        }
    }

}
