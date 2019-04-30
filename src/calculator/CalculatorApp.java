package calculator;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalculatorPM model = new CalculatorPM();

        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);

        Parent rootPanel = new CalculatorUI(model);

        Scene scene = new Scene(rootPanel, 768, 1024);

        primaryStage.titleProperty().bind(model.applicationTitleProperty());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
