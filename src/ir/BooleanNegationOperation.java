package ir;

public class BooleanNegationOperation extends UnaryOperation {
    private final Temp temp;
    
    public BooleanNegationOperation(Temp temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Z!";
    }
}
