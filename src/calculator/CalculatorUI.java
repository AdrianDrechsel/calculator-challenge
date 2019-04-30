package calculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class CalculatorUI extends GridPane {
    private final CalculatorPM model;

    private ArrayList<Button> numberButtons = new ArrayList<>();

    private Button dot;

    private Button plus;
    private Button minus;
    private Button multiply;
    private Button divide;

    private Button plusminus;

    private Button equal;
    private Button ac;

    private Label display;

    public CalculatorUI(CalculatorPM model) {
        this.model = model;
        initializeSelf();
        initializeControls();
        layoutControls();
        setupEventHandlers();
        setupBindings();
    }

    private void initializeSelf() {
        Font.loadFont("file:resources/fonts/GOODDP_.TTF", 120);
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeControls() {
        for (int i = 0; i < 10; i++) {
            numberButtons.add(new Button(String.valueOf(i)));
        }

        dot = new Button(".");
        plus = new Button("+");
        minus = new Button("-");
        multiply = new Button("*");
        divide = new Button("/");

        plusminus = new Button("+/-");
        equal = new Button("=");
        ac = new Button();

        display = new Label("");

        /**********
         * Set IDs
         **********/
        plus.getStyleClass().add("operator");
        minus.getStyleClass().add("operator");
        multiply.getStyleClass().add("operator");
        divide.getStyleClass().add("operator");

        plusminus.setId("plusminus");
        ac.setId("ac");
        equal.setId("equal");
        display.setId("display");
    }

    private void layoutControls() {
        /************************
         * Set Grid Constraints
         ************************/
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(25);
        getColumnConstraints().addAll(cc, cc, cc, cc);

        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(new RowConstraints(), rc, rc, rc, rc, rc, rc);

        /************************
         * Set Alignment and Grow
         ************************/
        for (Button b : new Button[]{ac, plus, minus, multiply, divide, plusminus, dot, equal}) {
            setHalignment(b, HPos.CENTER);
        }
        for (Button b : numberButtons) {
            setHalignment(b, HPos.CENTER);
        }

        display.setAlignment(Pos.CENTER_RIGHT);

        setHgrow(display, Priority.ALWAYS);
        setVgrow(display, Priority.ALWAYS);

        /****************
         * Add controls
         ****************/
        add(display, 0, 0, 3, 1);
        add(ac, 3, 0);

        add(plus, 0, 1);
        add(minus, 1, 1);
        add(multiply, 2, 1);
        add(divide, 3, 1);

        add(numberButtons.get(7), 0, 2);
        add(numberButtons.get(8), 1, 2);
        add(numberButtons.get(9), 2, 2);
        add(plusminus, 3, 2);

        add(numberButtons.get(4), 0, 3);
        add(numberButtons.get(5), 1, 3);
        add(numberButtons.get(6), 2, 3);

        add(numberButtons.get(1), 0, 4);
        add(numberButtons.get(2), 1, 4);
        add(numberButtons.get(3), 2, 4);

        add(numberButtons.get(0), 1, 5);
        add(dot, 2, 5);
        add(equal, 3, 5);
    }

    private void setupEventHandlers() {
        /****************
         * Load Sounds
         ****************/
        String candy = "src/calculator/sound/candy_link.wav";
        Media candySound = new Media(new File(candy).toURI().toString());

        String star = "src/calculator/sound/star_3.wav";
        Media starSound = new Media(new File(star).toURI().toString());

        String bounce = "src/calculator/sound/sp_field_bounce.wav";
        Media bounceSound = new Media(new File(bounce).toURI().toString());

        String spider = "src/calculator/sound/spider_fall.wav";
        Media spiderSound = new Media(new File(spider).toURI().toString());

        String chewing = "src/calculator/sound/monster_chewing.wav";
        Media chewingSound = new Media(new File(chewing).toURI().toString());


        /****************
         * Set Events
         ****************/
        for (int i = 0; i < 10; i++) {
            int idx = i;
            numberButtons.get(idx).setOnAction(event -> {
                new MediaPlayer(candySound).play();
                model.setDisplay(String.valueOf(idx));
            });
        }
        dot.setOnAction(event -> {
            new MediaPlayer(candySound).play();
            model.setDisplay(".");
        });

        plus.setOnAction(event -> {
            new MediaPlayer(chewingSound).play();
            model.setOperator(CalculatorPM.Operator.PLUS);
        });
        minus.setOnAction(event -> {
            new MediaPlayer(chewingSound).play();
            model.setOperator(CalculatorPM.Operator.MINUS);
        });
        multiply.setOnAction(event -> {
            new MediaPlayer(chewingSound).play();
            model.setOperator(CalculatorPM.Operator.MULTIPLY);
        });
        divide.setOnAction(event -> {
            new MediaPlayer(chewingSound).play();
            model.setOperator(CalculatorPM.Operator.DIVIDE);
        });

        plusminus.setOnAction(event -> {
            new MediaPlayer(bounceSound).play();
            model.switchSign();
        });
        equal.setOnAction(event -> {
            new MediaPlayer(starSound).play();
            model.calculate();
        });
        ac.setOnAction(event -> {
            new MediaPlayer(spiderSound).play();
            model.clear();
        });
    }

    private void setupBindings() {
        display.textProperty().bind(model.displayProperty());
    }
}
