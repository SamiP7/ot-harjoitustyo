package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Hiscores;

public class MainMenu extends Application {

    @Override
    public void start(Stage window) throws SQLException {
        Hiscores hiscore = new Hiscores();
        hiscore.createDatabase();
        window.setTitle("Sudoku :)");
        BorderPane view = new BorderPane();
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        Button newGame = new Button("New game");
        Button hiscores = new Button("Hiscores");
        Button exit = new Button ("Exit");
        newGame.setMinWidth(120);
        hiscores.setMinWidth(120);
        exit.setMinWidth(120);
        buttons.setAlignment(Pos.CENTER);
        view.setMinSize(250, 250);
        
        
        buttons.getChildren().add(newGame);
        buttons.getChildren().add(hiscores);
        buttons.getChildren().add(exit);
        
        view.setCenter(buttons);
        Scene sceneMain = new Scene(view);
        
        BorderPane hiscoresView = new BorderPane();
        
        VBox scores = new VBox();
        scores.setSpacing(10);
        
        
        scores.setAlignment(Pos.CENTER);        
        
        
        Button exitToMain = new Button("Main menu");
        exitToMain.setOnAction(event -> window.setScene(sceneMain));
        
        hiscoresView.setBottom(exitToMain);
        
        Scene sceneHiscores = new Scene(hiscoresView);
        
        SudokuInterface sudoku = new SudokuInterface();
        sudoku.exit.setOnAction(event -> window.setScene(sceneMain));
        
        window.setScene(sceneMain);
        newGame.setOnAction(event -> window.setScene(sudoku.window()));
        hiscores.setOnAction(event -> {
            try {
                scores.getChildren().clear();
                ArrayList<Integer> possibleHiscores = hiscore.returnTimes();
                for (int i = 0; i < possibleHiscores.size(); i++) {
                    scores.getChildren().add(new Label(i + 1 + ": " + String.valueOf(possibleHiscores.get(i) + " s")));
                }
                if (possibleHiscores.size() < 10) {
                    for (int i = possibleHiscores.size(); i < 10; i++) {
                        scores.getChildren().add(new Label(i + 1 + ": --"));
                    }
                }
                hiscoresView.setCenter(scores);
                window.setScene(sceneHiscores);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        exit.setOnAction(event -> window.close());
        window.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
