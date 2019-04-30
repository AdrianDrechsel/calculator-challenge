package calculator;

import javafx.beans.property.*;

public class CalculatorPM {
    private final StringProperty applicationTitle = new SimpleStringProperty("Cut the Calculator");
    private final StringProperty display = new SimpleStringProperty("");
    private final FloatProperty memory = new SimpleFloatProperty(0);

    private final ObjectProperty operator = new SimpleObjectProperty();

    public enum Operator { PLUS, MINUS, MULTIPLY, DIVIDE }

    public CalculatorPM() {}

    public String getApplicationTitle() {
        return applicationTitle.get();
    }
    public StringProperty applicationTitleProperty() { return applicationTitle; }

    public String getDisplay() { return display.get(); }
    public StringProperty displayProperty() { return display; }
    public void setDisplay(String s) { display.set(display.get() + s); }

    public Operator getOperator() { return (Operator) operator.get(); }
    public ObjectProperty operatorProperty() { return operator; }
    public void setOperator(Operator o) {
        memory.set(Float.parseFloat(display.get()));
        display.set("");
        operator.set(o);
    }

    public void calculate() {
        switch (getOperator()) {
            case PLUS: display.set(String.valueOf(Float.parseFloat(display.get()) - memory.get()));
            case MINUS: display.set(String.valueOf(Float.parseFloat(display.get()) - memory.get()));
            case MULTIPLY: display.set(String.valueOf(Float.parseFloat(display.get()) - memory.get()));
            case DIVIDE: display.set(String.valueOf(Float.parseFloat(display.get()) - memory.get()));
        }
    }

    public void switchSign() { display.set(String.valueOf(-Float.parseFloat(display.get()))); }

    public void clear() { display.set(""); }
}
