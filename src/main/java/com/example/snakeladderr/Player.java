package com.example.snakeladderr;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;

    private static Board gameBoard = new Board();

    public Player(int tileSize, Color coinColor, String playerName) {
        coin = new Circle(tileSize / 2);
        coin.setFill(coinColor);
        currentPosition = 0; // Start position outside the board
        movePlayer(1); // Place the player at the start without moving
        name = playerName;
    }

    public void movePlayer(int diceValue) {
        if (currentPosition + diceValue <= 100) {
            currentPosition += diceValue;

            TranslateTransition secondMove = null, firstMove = translateAnimation(currentPosition);

            int newPosition  = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition !=-1){
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }
            if(secondMove ==null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                new PauseTransition(Duration.millis(1000)),secondMove
                );
                sequentialTransition.play();
            }
        }
    }

    private TranslateTransition translateAnimation(int diceValue) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(200 * diceValue), coin);
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition)); // Corrected this line
        animate.setAutoReverse(false);
        return animate;
    }

    public void startingPosition(){
        currentPosition =0;
        movePlayer(1);
    }

    boolean isWinnwer(){
        if (currentPosition ==100) {
            return true;
        }
        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
