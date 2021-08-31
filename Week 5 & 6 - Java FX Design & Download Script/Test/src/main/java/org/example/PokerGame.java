package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * JavaFX App
 */
public class PokerGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Deck deck;
        Hand hand1;
        Hand hand2;

        deck = new Deck();
        hand1 = new Hand(deck);
        hand2 = new Hand(deck);

        Label winner = createLabelWithCustomRotation(winnerLabelSetText(hand1, hand2), 0, 80, 20);
        Label hand1Display = createLabelWithCustomRotation(hand1.display(), -90, 80, 20);
        Label hand2Display = createLabelWithCustomRotation(hand2.display(), -90, 80, 20);

        Button revealAll = new Button ("Reveal Winner");
        Button H1Card1 = cardButton();
        Button H1Card2 = cardButton();
        Button H1Card3 = cardButton();
        Button H1Card4 = cardButton();
        Button H1Card5 = cardButton();

        Button H2Card1 = cardButton();
        Button H2Card2 = cardButton();
        Button H2Card3 = cardButton();
        Button H2Card4 = cardButton();
        Button H2Card5 = cardButton();

        Button H1RevealAll = createButtonWithRotatedText("Reveal Hand 1");
        Button H2RevealAll = createButtonWithRotatedText("Reveal Hand 2");

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        BackgroundFill myBF = new BackgroundFill(Color.GREEN, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        root.setBackground(new Background(myBF));

        Scene scene = new Scene(root,700,500);

        root.addRow(1, revealAll);
        root.addRow(2, H1RevealAll, hand1Display, H1Card1, H1Card2, H1Card3, H1Card4, H1Card5);
        root.addRow(3, H2RevealAll, hand2Display, H2Card1, H2Card2, H2Card3, H2Card4, H2Card5);
        root.addRow(4, winner);

        //New card graphics
        ImageView H1Card1ImageView = buttonGraphics(hand1.cards[0]);
        ImageView H1Card2ImageView = buttonGraphics(hand1.cards[1]);
        ImageView H1Card3ImageView = buttonGraphics(hand1.cards[2]);
        ImageView H1Card4ImageView = buttonGraphics(hand1.cards[3]);
        ImageView H1Card5ImageView = buttonGraphics(hand1.cards[4]);

        ImageView H2Card1ImageView = buttonGraphics(hand2.cards[0]);
        ImageView H2Card2ImageView = buttonGraphics(hand2.cards[1]);
        ImageView H2Card3ImageView = buttonGraphics(hand2.cards[2]);
        ImageView H2Card4ImageView = buttonGraphics(hand2.cards[3]);
        ImageView H2Card5ImageView = buttonGraphics(hand2.cards[4]);

        H1Card1.setOnAction(value -> {
            H1Card1.setGraphic(H1Card1ImageView);
        });
        H1Card2.setOnAction(value -> {
            H1Card2.setGraphic(H1Card2ImageView);
        });
        H1Card3.setOnAction(value -> {
            H1Card3.setGraphic(H1Card3ImageView);
        });
        H1Card4.setOnAction(value -> {
            H1Card4.setGraphic(H1Card4ImageView);
        });
        H1Card5.setOnAction(value -> {
            H1Card5.setGraphic(H1Card5ImageView);
        });

        H2Card1.setOnAction(value -> {
            H2Card1.setGraphic(H2Card1ImageView);
        });
        H2Card2.setOnAction(value -> {
            H2Card2.setGraphic(H2Card2ImageView);
        });
        H2Card3.setOnAction(value -> {
            H2Card3.setGraphic(H2Card3ImageView);
        });
        H2Card4.setOnAction(value -> {
            H2Card4.setGraphic(H2Card4ImageView);
        });
        H2Card5.setOnAction(value -> {
            H2Card5.setGraphic(H2Card5ImageView);
        });

        H1RevealAll.setOnAction(value ->  {
            H1Card1.setGraphic(H1Card1ImageView);
            H1Card2.setGraphic(H1Card2ImageView);
            H1Card3.setGraphic(H1Card3ImageView);
            H1Card4.setGraphic(H1Card4ImageView);
            H1Card5.setGraphic(H1Card5ImageView);
            hand1Display.setVisible(true);
        });

        H2RevealAll.setOnAction(value ->  {
            H2Card1.setGraphic(H2Card1ImageView);
            H2Card2.setGraphic(H2Card2ImageView);
            H2Card3.setGraphic(H2Card3ImageView);
            H2Card4.setGraphic(H2Card4ImageView);
            H2Card5.setGraphic(H2Card5ImageView);
            hand2Display.setVisible(true);
        });

        revealAll.setOnAction(value ->  {
            H1Card1.setGraphic(H1Card1ImageView);
            H1Card2.setGraphic(H1Card2ImageView);
            H1Card3.setGraphic(H1Card3ImageView);
            H1Card4.setGraphic(H1Card4ImageView);
            H1Card5.setGraphic(H1Card5ImageView);

            H2Card1.setGraphic(H2Card1ImageView);
            H2Card2.setGraphic(H2Card2ImageView);
            H2Card3.setGraphic(H2Card3ImageView);
            H2Card4.setGraphic(H2Card4ImageView);
            H2Card5.setGraphic(H2Card5ImageView);

            hand1Display.setVisible(true);
            hand2Display.setVisible(true);
            winner.setVisible(true);

            revealAll.setDisable(true);
        });


        stage.setScene(scene);
        stage.show();
    }

    private String winnerLabelSetText (Hand hand1, Hand hand2){
        String string;
        if (hand1.compareTo(hand2) == 1){
            string = "Hand 1 Wins";
        } else if (hand1.compareTo(hand2) == -1){
            string = "Hand 2 Wins";
        } else {
            string = "Its a draw";
        }
        return string;
    }
    private Label createLabelWithCustomRotation(String text, int rotation, int width, int height){
        Label label = new Label(text);
        label.setMinSize(width, height);
        label.setRotate(rotation);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
        label.setVisible(false);
        return label;
    }

    private ImageView buttonGraphics (Card card) throws FileNotFoundException {
        String string = returnCardString(card.getRank(), card.getSuit());
        InputStream inputStream = new FileInputStream("src/main/java/PNG/" + string + ".png");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(50);

        return imageView;
    }

    private Button cardButton() throws FileNotFoundException {
        Button button = new Button();
        button.setMinSize(50,80);

        String string = "blue_back";
        InputStream stream = new FileInputStream("src/main/java/PNG/" + string + ".png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(50);

        button.setStyle(
                "-fx-base: white; "
        );
        button.setGraphic(imageView);
        return button;
    }

    private Button createButtonWithRotatedText(String text) {
        Button button = new Button();
        Label  label  = new Label(text);
        label.setRotate(-90);
        button.setGraphic(new Group(label));
        button.setMinSize(20,80);
        return button;
    }

    public static String returnCardString(short rank, short suit){
        System.out.println("here");

        System.out.println("Rank:" + rank + " Suit: " + suit);
        String rankString;
        String suitString;
        switch (rank){
            case 0:
                rankString = "A";
                break;
            case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                rank += 1;
                rankString = "" + rank;
                break;
            case 10:
                rankString = "J";
                break;
            case 11:
                rankString = "Q";
                break;
            case 12:
                rankString = "K";
                break;
            default:
                rankString = "Error";
                break;
        }

        switch (suit){
            case 0:
                suitString = "H";
                break;
            case 1:
                suitString = "S";
                break;
            case 2:
                suitString = "D";
                break;
            case 3:
                suitString = "C";
                break;
            default:
                suitString = "Error";
                break;
        }

        return rankString + suitString;
    }
    public static void main(String[] args) {
        launch();
    }
}