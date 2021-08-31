package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class PokerGame extends Application {
    final int sceneHeight = 500; final int sceneWidth = 700;
    final String[] cardBackColours = {"blue", "grey", "green", "purple", "red", "yellow"};

    @Override
    public void start(Stage stage) throws Exception {
        /**
         * First screen elements:
         *  - Top label "Welcome to Poker (18+)
         *  - Card Png
         *  - Name and age input boxes text fields in a horizontal box formation
         *  - Label "Card back colour is randomised" and randomisation process
         *  - Play button
         *  - Task verification verdict (visibility set to false until button click)
         */

        //Top label "Welcome to Poker (18+)
        Label welcomeLabel = createLabelWithCustomRotation("Welcome To Poker (18+)", 0, 80, 20, true);

        //Card png
        InputStream inputStream = new FileInputStream("src/main/java/PNG/aces.png");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        //Name and age input boxes text fields in a horizontal box formation
        HBox hb = new HBox();
        Label labelName = createLabelWithCustomRotation("Name: ", 0, 80, 20, true);
        TextField textFieldName = new TextField();
        Label labelAge = createLabelWithCustomRotation("Age: ", 0, 80, 20, true);
        TextField textFieldAge = new TextField();
        hb.getChildren().addAll(labelName, textFieldName, labelAge, textFieldAge);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        //Label "Card back colour is randomised" and randomisation process
        Label cardBackColour = createLabelWithCustomRotation("Card back colour is randomised", 0, 80, 20, true);
        Random rand = new Random();
        int randomNumberCardBack = rand.nextInt(6);
        String backColour = cardBackColours[randomNumberCardBack];

        //Play button
        Button newScene = buttonFormatSize("Play", 50);

        //Task verification verdict (visibility set to false until button click)
        Label taskVerifFail = createLabelWithCustomRotation("", 0, 80, 20, false);

        //Creation of vertical box layout including all of the elements on the first screen
        VBox vbox = createVbox();
        vbox.getChildren().addAll(welcomeLabel, imageView, hb, cardBackColour, newScene, taskVerifFail);

        //Creating the first scene including the vertical box format, and configuring size
        Scene firstScene = new Scene (vbox, sceneWidth, sceneHeight);
        stage.setScene(firstScene);
        stage.show();

        /**
         * Creating the deck and two poker hands given the original deck
         */
        Deck deck = new Deck();
        Hand hand1 = new Hand(deck);
        Hand hand2 = new Hand(deck);

        /**
         * Second screen elements:
         *  - Reveal winner button
         *  - Reveal hand 1 and reveal hand 1 buttons
         *  - Rotated labels indicating the result of hand 1 and hand 2
         *  - Card buttons showing back of the cards
         *  - Card labels showing the front of the cards
         *  - Label displaying the winner between the two hands
         *  - Close program button
         */

        //Reveal winner button
        Button revealAll = new Button ("Reveal Winner");

        //Reveal hand 1 and reveal hand 1 buttons
        Button H1RevealAll = createButtonWithRotatedText("Reveal Hand 1");
        Button H2RevealAll = createButtonWithRotatedText("Reveal Hand 2");

        //Rotated labels indicating the result of hand 1 and hand 2
        Label hand1Display = createLabelWithCustomRotation(hand1.display(), -90, 80, 20, false);
        Label hand2Display = createLabelWithCustomRotation(hand2.display(), -90, 80, 20, false);

        //Card buttons showing back of the cards; hand 1 and hand 2
        Button H1Card1 = cardButton(backColour);
        Button H1Card2 = cardButton(backColour);
        Button H1Card3 = cardButton(backColour);
        Button H1Card4 = cardButton(backColour);
        Button H1Card5 = cardButton(backColour);

        Button H2Card1 = cardButton(backColour);
        Button H2Card2 = cardButton(backColour);
        Button H2Card3 = cardButton(backColour);
        Button H2Card4 = cardButton(backColour);
        Button H2Card5 = cardButton(backColour);

        //Card labels showing the front of the cards
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

        //Label displaying the winner between the two hands
        Label winner = createLabelWithCustomRotation(winnerLabelSetText(hand1, hand2), 0, 80, 20, false);

        //Close program button
        Button closeProgram = buttonFormatSize("Close Program", 100);
        closeProgram.setVisible(false);

        //Creating the gridpane with all the above elements and adding it ins=to the second scene
        GridPane root = createGridPane();
        root.addRow(1, revealAll);
        root.addRow(2, H1RevealAll, hand1Display, H1Card1, H1Card2, H1Card3, H1Card4, H1Card5);
        root.addRow(3, H2RevealAll, hand2Display, H2Card1, H2Card2, H2Card3, H2Card4, H2Card5);
        root.addRow(4, winner);
        root.add(closeProgram, 0, 5);
        Scene scene = new Scene(root,sceneWidth,sceneHeight);

        /**
         * Action listeners
         *  - Play button listener and task verification
         *  - Individual card button listeners
         *  - Hand 1 and hand 2 button listeners
         *  - Reveal all cards button listener
         *  - Close program button listener
         */

        //Play button listener and task verification
        newScene.setOnAction(value -> {
            int validation = isInputValid(textFieldName.getText(), Integer.parseInt(textFieldAge.getText()));
            if (validation == 1) {
                textFieldName.setText("");
                textFieldAge.setText("");

                stage.setScene(scene);
                stage.show();
            } else {
                taskVerifFail.setVisible(true);
                if (validation == 0) {
                    taskVerifFail.setText("Age is not valid");
                } else if (validation == -1) {
                    taskVerifFail.setText("Name is not valid");
                } else {
                    taskVerifFail.setText("Name and age or not valid");
                }
            }
        });

        //Individual card button listeners
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

        //Hand 1 and hand 2 button listeners
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

        //Reveal all cards button listener
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
            closeProgram.setVisible(true);

            revealAll.setDisable(true);

        });

        //Close program button listener
        closeProgram.setOnAction(value -> {
            stage.close();
        });
    }

    /** Method Name:    createLabelWithCustomRotation
     *  Description:    Create a new label object. The function requires inputs such as text, rotation, size and a visibility
     *                  boolean and creates a label to fit this description. The words are always set to the middle of the label
     *                  and the background colour is always set to white
     *
     *  Parameter:      String: text, int: rotation, int: width, int: height, boolean: visible
     *  Output:         Label object: label
     **/
    private Label createLabelWithCustomRotation(String text, int rotation, int width, int height, boolean visible){
        Label label = new Label(text);
        label.setMinSize(width, height);
        label.setRotate(rotation);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
        label.setVisible(visible);
        return label;
    }

    /** Method Name:    buttonFormatSize
     *  Description:    Create a new button object with text. The function requires inputs a string for the text and the width
     *                  and creates a button to fit this description. The button height is fixed at 20 pixels.
     *
     *  Parameter:      String: label, int: width
     *  Output:         Button object: button
     **/
    private Button buttonFormatSize (String label, int width){
        Button button = new Button(label);
        button.setMinSize(width, 20);
        return button;
    }

    /** Method Name:    createVbox
     *  Description:    Create a new vertical box object and format it. The vertical box has a padding of 10 pixels and a spacing
     *                  of 8 pixels which are fixed. The vertical box always is aligned to the center of the screen and the background
     *                  colour is set to green.
     *
     *  Parameter:      No parameters
     *  Output:         Vertical box object: vbox
     **/
    private VBox createVbox (){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setAlignment(Pos.CENTER);
        BackgroundFill myBF = new BackgroundFill(Color.GREEN, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        vbox.setBackground(new Background(myBF));

        return vbox;
    }

    /** Method Name:    createButtonWithRotatedText
     *  Description:    Create a new button object with rotated text. The function requires inputs a string for the text
     *                  and creates a button to fit this description. The button size is fixed at 20 pixels tall and 80 pixels
     *                  wide and it is rotated 90 degrees anticlockwise.
     *
     *  Parameter:      String: text
     *  Output:         Button object: button
     **/
    private Button createButtonWithRotatedText(String text) {
        Button button = new Button();
        Label  label  = new Label(text);
        label.setRotate(-90);
        button.setGraphic(new Group(label));
        button.setMinSize(20,80);
        return button;
    }

    /** Method Name:    cardButton
     *  Description:    Create a new button object with a specified colour card back to it. The button size is fixed at 80 pixels tall
     *                  and 50 pixels wide. A png of the input card colour string is attached to the front of the button.
     *
     *  Parameter:      String: colour
     *  Output:         Button object: button
     **/
    private Button cardButton(String colour) throws FileNotFoundException {
        Button button = new Button();
        button.setMinSize(50,80);
        String string = colour + "_back";
        InputStream stream = new FileInputStream("src/main/java/PNG/" + string + ".png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);
        return button;
    }

    /** Method Name:    returnCardString
     *  Description:    Takes in two short values, the card suit and rank. This is formatted in a way that can be used to
     *                  find the correct png for the card label and attach it to it.
     *
     *  Parameter:      Short: rank, short: suit
     *  Output:         String: rank + suit
     **/
    public static String returnCardString(short rank, short suit){
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

    /** Method Name:    buttonGraphics
     *  Description:    Create a new imageView object of a specified card face. The size is fixed at 80 pixels tall
     *                  and 50 pixels wide. A png of the card that the previous function has formatted is found and this function
     *                  creates the appropriate image for the suit and rank
     *
     *  Parameter:      Card: card
     *  Output:         Image View object: image view
     **/
    private ImageView buttonGraphics (Card card) throws FileNotFoundException {
        String string = returnCardString(card.getRank(), card.getSuit());
        InputStream inputStream = new FileInputStream("src/main/java/PNG/" + string + ".png");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(50);

        return imageView;
    }

    /** Method Name:    winnerLabelSetText
     *  Description:    Create a new label object. The function requires takes in the two hands and compares them. A string is
     *                  returned detailing the outcome of the game
     *
     *  Parameter:      Hand: hand1, Hand: hand2
     *  Output:         String: string
     **/
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

    /** Method Name:    createGridPane
     *  Description:    Create a new GridPane object. The grid pane gets a vertical gap between elements as 10 pixels and it is
     *                  always aligned with the center of the screen. The background colour of the grid pane is set to green
     *
     *  Parameter:      No parameters
     *  Output:         GridPane: gridPane
     **/
    private GridPane createGridPane (){
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        BackgroundFill myBF = new BackgroundFill(Color.GREEN, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        gridPane.setBackground(new Background(myBF));
        return gridPane;
    }

    /** Method Name:    isInputValid
     *  Description:    The main structure to check if the input is valid entered in the text boxes of the user. The function
     *                  uses the sub functions isNameValid and isAgeValid to verify the text. The function will return a number
     *                  depending on the case. 1 --> Both name and age are valid 0 --> Age is invalid 1 --> Name is invalid -2 -->
     *                  name and age are invalid
     *
     *  Parameter:      String: name, int: age
     *  Output:         int
     **/
    private int isInputValid(String name, int age) {
        if (!isAgeValid(age) && !isNameValid(name))return -2;
        else if (!isNameValid(name)) return -1;
        else if (!isAgeValid(age)) return 0;
        return 1;
    }

    /** Method Name:    isNameValid
     *  Description:    Text validation for name input box using regrex.
     *                  - ignore case: "(?i)
     *                  - Must starts with a character that falls between a - z: "[a-z]"
     *                  - Must end with a character that falls between a - z(.*[a-z])?"
     *                  - Must be between 1 - 25 characters that can contain a - z(.{0,23}[a-z])?"
     *                  If the above is satisfied the function returns true, otherwise the function returns false
     *
     *  Parameter:      String: name
     *  Output:         boolean
     **/
    private boolean isNameValid (String name){
        if ((name == null || name.length() == 0) || !name.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) return false;
        return true;
    }

    /** Method Name:    isNameValid
     *  Description:    Text validation for name input box using arithmatic. If the age is between the values of 18 and 120
     *                  inclusive the program will return true otherwise false.
     *
     *  Parameter:      String: name
     *  Output:         boolean
     **/
    private boolean isAgeValid (int age){
        if (age <= 18 || age >= 120) return false;
        return true;
    }

    public static void main(String[] args) {
        launch();
    }
}