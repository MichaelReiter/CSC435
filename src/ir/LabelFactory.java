package ir;

public class LabelFactory {
    private int labelCount;

    public LabelFactory() {
        this.labelCount = 0;
    }

    public Label getLabel() {
        Label Label = new Label(this.labelCount);
        this.labelCount++;
        return Label;
    }
}
