package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class TicTacToe extends Application {
    private Label playerX;                                  // Create A variable to set the player X Message
    private Label playerO;                                  // Create A variable to to set the player O Message
    private boolean xturn = true;                           // Create A variable to identify the Player Turn
    private Button buttons [][] = new Button [3][3];        //Create A Button grid to store the Buttons
    private int playeXScore=0;                              // Create A variable to store the player X Score
    private int  PlayerOScore=0;                            // Create A variable to store the player O Score
    private BorderPane createContent() {

        BorderPane root = new BorderPane();

        //Creating Title of Game
        Label titlelable = new Label("Tic Tac Toe");                            // Set the Name of Game
        titlelable.setStyle("-fx-font-size : 24px; -fx-font-weight : bold");       // Set the font-Size & Style
        root.setTop(titlelable);                                                   // Set The Title Location of Screen
        BorderPane.setAlignment(titlelable, Pos.CENTER);                           // Set the Title on center

        //Creating a Game Board
        GridPane gridpane = new GridPane();                     // Creating a Game Board Using Gridpane
        gridpane.setHgap(10);                                   // Give the Horizontal Padding between Grids
        gridpane.setVgap(10);                                   // Give the Vertical Padding between Grids

        // Create the Buttons and set the font-size or Style
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setStyle("-fx-font-size : 34px; -fx-font-weight : bold");
                button.setPrefSize(100,100);
                button.setOnAction(event -> buttonclicked(button));
                buttons[i][j]=button;
                gridpane.add(button, j,i);
            }
        }

        // Set the Game Board on Center
        root.setCenter(gridpane);                                       //Set the Grid to center of Screen
        gridpane.setAlignment(Pos.CENTER);

        // Score Screen
        HBox scoreboard = new HBox(20);
        scoreboard.setAlignment(Pos.CENTER);                                // Set the Score Screen in center
        playerX = new Label("Player X : 0");                             // Creating a variable to Store the Score of X
        playerX.setStyle("-fx-font-size : 14px; -fx-font-weight : bold");   // Set the Font-Size & Style of Player X Score
        playerO = new Label("Player O : 0");                             // Creating a variable to Store the Score of X
        playerO.setStyle("-fx-font-size : 14px; -fx-font-weight : bold");   // Set the Font-Size & Style of Player X Score

        scoreboard.getChildren().addAll(playerX,playerO);                   // Add the Playerx & PlayerO to scoreboard
        root.setBottom(scoreboard);                                         // Set the Score Board at Bottom of Game Screen
        return root;
    }
    // Create a Event when Button is Clicked
    private void buttonclicked(Button button){
        if(button.getText().equals("")){        //if Button has Empty text
            if(xturn){
                button.setText("X");            // Set the Button text to X
            }else{
                button.setText("O");            // Set the Button text to X
            }
            xturn = !xturn;                     // Change the Turn
        }
        checkWinner();                          // Announce the winner
        return;
     }

//    // Check the Winner Condition
     private void checkWinner(){
         // check the winner in row
         for (int row = 0; row < 3; row++) {
             if(buttons[row][0].getText().equals(buttons[row][1].getText())
                 && buttons[row][1].getText().equals(buttons[row][2].getText()) && !buttons[row][0].getText().isEmpty()){
                 // we will have a winner
                 String winner = buttons[row][0].getText();
                 showWinner(winner);
                 updateScore(winner);
                 resetBoard();
                 return;
             }
         }
         //Check the winner in column
         for (int col = 0; col < 3; col++) {
             if(buttons[0][col].getText().equals(buttons[1][col].getText())
                     && buttons[1][col].getText().equals(buttons[2][col].getText()) && !buttons[0][col].getText().isEmpty()){
                 // we will have a winner
                 String winner = buttons[0][col].getText();
                 showWinner(winner);
                 updateScore(winner);
                 resetBoard();
                 return;
             }
         }
         //Check the winner in Diagonal
         if(buttons[0][0].getText().equals(buttons[1][1].getText())
                 && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().isEmpty()){
             // we will have a winner
             String winner = buttons[0][0].getText();
             showWinner(winner);
             updateScore(winner);
             resetBoard();
             return;
         }
         // Check the winner in Diagonal
         if(buttons[0][2].getText().equals(buttons[1][1].getText())
                 && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[1][1].getText().isEmpty()){
             // we will have a winner
             String winner = buttons[1][1].getText();
             showWinner(winner);
             updateScore(winner);
             resetBoard();
             return;
         }

         // Check the Game Tie Condition
         boolean tie  = true;
         for(Button row[] : buttons){
             for(Button button : row){
                if( button.getText().isEmpty()){
                    tie=false;
                 }
             }
         }

         // if No one is the winner
         if(tie){
             gameTie();
             resetBoard();
         }

     }

     // Update the Player Score after Winning
     private  void updateScore(String winner){
        if(winner=="X"){                                        //if Player X is Winner
            playeXScore++;
            playerX.setText("Player X: "+playeXScore);
        }else{                                                  // If Player O is Winner
            PlayerOScore++;
            playerO.setText("Player O: "+PlayerOScore);
        }
     }

     //Show the Winner Announcement
     private  void showWinner(String winner){
         Alert alert= new Alert(Alert.AlertType.INFORMATION);                   //Create a alert Object For Winner
         alert.setTitle("Winner");                                              //Set the Alert title
         alert.setContentText("Congratulation "+ winner+ "! you won the game"); // Set the Winner Message
         alert.setHeaderText("");                                               // Make Announcement Header Text Blank
         alert.showAndWait();
     }
    private  void gameTie(){
        Alert gametie= new Alert(Alert.AlertType.INFORMATION);              //Create a alert Object For Game Tie
        gametie.setTitle("Game Tie");                                       //Set the Alert title
        gametie.setContentText("Game Over : It's Tie ");                    // Set the Game Tie Message
        gametie.setHeaderText("");                                          // Make Announcement Header Text Blank
        gametie.showAndWait();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");

        stage.setScene(scene);
        stage.show();
    }

    // After cehcking the winner of Tie Condition Reset the Game Board
    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");                     // Clear the Text of Every Button
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }
}