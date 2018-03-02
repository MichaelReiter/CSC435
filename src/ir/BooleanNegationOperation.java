package ir;

public class BooleanNegationOperation extends UnaryOperation {
    private final Temp temp;
    
    public BooleanNegationOperation(Temp temp) {
        this.temp = temp;
    }

    public String toString() {
        return "Z!";
    }
}
