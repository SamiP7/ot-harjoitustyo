package ui;

import java.util.*;
import logic.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class Interface extends Application {
    
    public Table sudoku = new Table();
    private BorderPane main = new BorderPane();
    public Button exit;
    private ArrayDeque<Button> previousMoveButton = new ArrayDeque<>();
    private ArrayDeque<String> previousMoveValue = new ArrayDeque<>();
    
    @Override
    public void start(Stage window) {
        sudoku.createAnswer();
        sudoku.createSudokuFromAnswer();
        window.setScene(window());
        window.show();
        exit.setOnAction(event -> window.close());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

    public Scene window() {
        
        VBox commands = new VBox();
        commands.setSpacing(15);
        
        Button previous = new Button("Cancel");
        Button clear = new Button("Clear");
        exit = new Button("Exit");
        
        commands.getChildren().add(previous);
        commands.getChildren().add(clear);
        commands.getChildren().add(exit);
        main.setRight(commands);
        
        HBox addNumbers = new HBox();
        addNumbers.setSpacing(15);
        addNumbers.setPrefHeight(40);
        addNumbers.setAlignment(Pos.CENTER);
        
        Button number1 = new Button("1");
        Button number2 = new Button("2");
        Button number3 = new Button("3");
        Button number4 = new Button("4");
        Button number5 = new Button("5");
        Button number6 = new Button("6");
        Button number7 = new Button("7");
        Button number8 = new Button("8");
        Button number9 = new Button("9");
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(number1);
        buttons.add(number2);
        buttons.add(number3);
        buttons.add(number4);
        buttons.add(number5);
        buttons.add(number6);
        buttons.add(number7);
        buttons.add(number8);
        buttons.add(number9);
        addNumbers.getChildren().addAll(number1, number2, number3, number4, number5, number6, number7, number8, number9);
        Label number = new Label("1");
        
        for (Button z : buttons) {
            z.setOnAction((event) -> {
                number.setText(z.getText());
            });
        }
        
        main.setBottom(addNumbers);
        
        ColumnConstraints always = new ColumnConstraints();
        always.setHgrow(Priority.SOMETIMES);
        always.setMaxWidth(50);
        always.setMinWidth(50);
        RowConstraints r = new RowConstraints();
        r.setMaxHeight(50);
        r.setMinHeight(50);
        
        GridPane a = new GridPane();
        a.setGridLinesVisible(true);
        a.getColumnConstraints().addAll(always,always,always);
        a.getRowConstraints().addAll(r, r, r);
        
        GridPane b = new GridPane();
        b.setGridLinesVisible(true);
        b.getColumnConstraints().addAll(always,always,always);
        b.getRowConstraints().addAll(r, r, r);
        
        GridPane c = new GridPane();
        c.setGridLinesVisible(true);
        c.getColumnConstraints().addAll(always,always,always);
        c.getRowConstraints().addAll(r, r, r);
        
        GridPane d = new GridPane();
        d.setGridLinesVisible(true);
        d.getColumnConstraints().addAll(always,always,always);
        d.getRowConstraints().addAll(r, r, r);
        
        GridPane e = new GridPane();
        e.setGridLinesVisible(true);
        e.getColumnConstraints().addAll(always,always,always);
        e.getRowConstraints().addAll(r, r, r);
        
        GridPane f = new GridPane();
        f.setGridLinesVisible(true);
        f.getColumnConstraints().addAll(always,always,always);
        f.getRowConstraints().addAll(r, r, r);
        
        GridPane g = new GridPane();
        g.setGridLinesVisible(true);
        g.getColumnConstraints().addAll(always,always,always);
        g.getRowConstraints().addAll(r, r, r);
        
        GridPane h = new GridPane();
        h.setGridLinesVisible(true);
        h.getColumnConstraints().addAll(always,always,always);
        h.getRowConstraints().addAll(r, r, r);
        
        GridPane j = new GridPane();
        j.setGridLinesVisible(true);
        j.getColumnConstraints().addAll(always,always,always);
        j.getRowConstraints().addAll(r, r, r);
        
        ArrayList<Button> clearButtons = new ArrayList<>();
      
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (i < 3 && o < 3) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        a.add(x, o, i);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        a.add(x, o, i);
                        
                    }
                } else if (i < 3 && o < 6) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE); 
                        x.setAlignment(Pos.CENTER);
                        b.add(x, o - 3, i);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        b.add(x, o - 3, i);
                    }
                } else if (i < 3 && o < 9) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        c.add(x, o - 6, i);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        c.add(x, o - 6, i);
                    }
                } else if (i < 6 && o < 3) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        d.add(x, o, i - 3);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        d.add(x, o, i - 3);
                    }
                } else if (i < 6 && o < 6) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        e.add(x, o - 3, i - 3);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        e.add(x, o - 3, i - 3);
                    }
                } else if (i < 6 && o < 9) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        f.add(x, o - 6, i - 3);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        f.add(x, o - 6, i - 3);
                    }
                } else if (i < 9 && o < 3) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        g.add(x, o, i - 6);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        g.add(x, o, i - 6);
                    }
                } else if (i < 9 && o < 6) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        h.add(x, o - 3, i - 6);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        h.add(x, o - 3, i - 6);
                    }
                } else if (i < 9 && o < 9) {
                    if (sudoku.sudokuTable[i][o] != 0) {
                        Label x = new Label(String.valueOf(sudoku.sudokuTable[i][o]));
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        j.add(x, o - 6, i - 6);
                    } else {
                        Button x = new Button();
                        x.setMaxWidth(Double.MAX_VALUE);
                        x.setMaxHeight(Double.MAX_VALUE);
                        x.setAlignment(Pos.CENTER);
                        int q = i + 1;
                        int w = o + 1;
                        x.setOnAction(ev -> {
                            if (sudoku.yetToBeAdded().get(Integer.valueOf(number.getText())) != 0) {
                                sudoku.addNumber(q, w, Integer.valueOf(number.getText()));
                                if (!x.getText().equals(number.getText())) {
                                    previousMoveButton.addFirst(x);
                                    previousMoveValue.addFirst(x.getText());
                                }
                                x.setText(number.getText());
                            }
                            if (sudoku.isPuzzleDone()) {
                                if (sudoku.checkIfCorrect(sudoku.sudokuTable)) {
                                    main.setCenter(new Label("You solved the puzzle, feel free to exit now :)"));
                                } else {
                                    main.setTop(new Label("Your solution is incorrect"));
                                }
                            }
                        });
                        clearButtons.add(x);
                        j.add(x, o - 6, i - 6);
                    }
                }
                
            }
        }
        
        previous.setOnAction(event -> {
            sudoku.cancelLastMove();
            if (!previousMoveValue.isEmpty()) {
                previousMoveButton.pollFirst().setText(previousMoveValue.pollFirst());
            }
        });
        clear.setOnAction(event -> {
            sudoku.clearTable();
            for (Button z : clearButtons) {
                z.setText("");
            }
        });
        
        GridPane s = new GridPane();
        s.setGridLinesVisible(true);
        s.add(a, 0, 0);
        s.add(b, 1, 0);
        s.add(c, 2, 0);
        s.add(d, 0, 1);
        s.add(e, 1, 1);
        s.add(f, 2, 1);
        s.add(g, 0, 2);
        s.add(h, 1, 2);
        s.add(j, 2, 2);
        
        main.setCenter(s);

        Scene scene = new Scene(main);
        
        return scene;
    }
    
}
