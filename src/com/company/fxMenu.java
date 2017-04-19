package com.company;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by Oussama on 14/04/2017.
 */
public class fxMenu extends Application{
    static Stage teacher;

    //first stage stuff
    static Scene scene;
    static VBox layout;

    //Moving stage stuff
    Scene levelUpScene;
    VBox levelUpLayout;

    //opening scene
    Scene openingScene;
    VBox openingLayout;

    //stage stuff
    Label question;
    static Label Title;
    TextField answer;
    Label correction;
    Button submit;
    Label currentScore;

    int score = 0;
    int stage = 1;

    public static void main(String[] args) {
        launch(args);
        Title.setText("test");
        teacher.setScene(scene);
        teacher.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        teacher = primaryStage;


        Title = new Label("MATH TEACHER");
        Title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
        Title.setTranslateY(-30);
        Title.setTextFill(Paint.valueOf("#FFFFFF"));

        currentScore = new Label("Stage: " + stage + " / Score: " + score);
        currentScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 25));
        currentScore.setTextFill(Paint.valueOf("#FFFFFF"));

        submit = new Button();
        submit.setText("Start");
        submit.setTextFill(Paint.valueOf("#ffffff"));
        submit.setFont(Font.font("", FontWeight.EXTRA_BOLD, 15));
        submit.setBackground(new Background(new BackgroundFill(Paint.valueOf("0098ECFF"), null, null)));

        answer = new TextField();
        answer.setMaxSize(200, 5);
        answer.setStyle("");
        answer.setPromptText("Type your answer here...");
        answer.setAlignment(Pos.CENTER);


        question = new Label("Fill in your name");
        question.setFont(Font.font("", FontWeight.EXTRA_BOLD, null, 30));
        question.setTextFill(Paint.valueOf("#ffffff"));
        question.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));

        correction = new Label();
        correction.setFont(Font.font(30));

        layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(Title, question, answer, submit, correction, currentScore);
        layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#009866FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        levelUpLayout = new VBox(50);
        levelUpLayout.setAlignment(Pos.CENTER);
        levelUpLayout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#009866FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        openingLayout = new VBox(50);
        openingLayout.setAlignment(Pos.CENTER);
        openingLayout.getChildren().addAll(Title, question, answer, submit);
        openingLayout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#009866FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        openingScene = new Scene(openingLayout, 600, 600);

        levelUpScene = new Scene(levelUpLayout, 600, 600);

        scene = new Scene(layout, 600, 600);

        teacher.setScene(openingScene);
        teacher.setResizable(false);
        teacher.show();
    }

}
