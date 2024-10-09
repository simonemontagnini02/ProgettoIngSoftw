package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FantaF1App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Layout per posizionare il logo in alto a sinistra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.TOP_RIGHT); // Allinea il logo in alto a sinistra
        logoBox.setPrefWidth(1024);  // Larghezza della scena in base alla risoluzione dell'immagine di sfondo
        logoBox.setPrefHeight(1024); // Altezza della scena

        // Creazione dei pulsanti
        Button btnRegister = new Button("Registrazione");
        Button btnLogin = new Button("Login");
        Button btnUpdates = new Button("Aggiornamenti");

        // Imposta una larghezza e un'altezza preferita per i pulsanti
        btnRegister.setPrefWidth(300); // Aumenta la larghezza
        btnRegister.setPrefHeight(50); // Aumenta l'altezza
        btnLogin.setPrefWidth(300); // Aumenta la larghezza
        btnLogin.setPrefHeight(50); // Aumenta l'altezza
        btnUpdates.setPrefWidth(300); // Aumenta la larghezza
        btnUpdates.setPrefHeight(50); // Aumenta l'altezza

        // Imposta il testo in grassetto
        btnRegister.setStyle("-fx-font-weight: bold;");
        btnLogin.setStyle("-fx-font-weight: bold;");
        btnUpdates.setStyle("-fx-font-weight: bold;");

        // Effetto hover per tutti i pulsanti
        for (Button btn : new Button[]{btnRegister, btnLogin, btnUpdates}) {
            btn.setOnMouseEntered(event -> btn.setScaleX(1.1)); // Aumenta la scala del pulsante su hover
            btn.setOnMouseExited(event -> btn.setScaleX(1.0)); // Torna alla scala originale
        }

        // Gestori degli eventi per ogni pulsante
        btnRegister.setOnAction(event -> {
            System.out.println("Vai alla registrazione");
        });

        btnLogin.setOnAction(event -> {
            System.out.println("Vai al login");
        });

        btnUpdates.setOnAction(event -> {
            System.out.println("Vai agli aggiornamenti");
        });

        // Layout verticale (VBox) con i pulsanti centrati
        VBox vbox = new VBox(30, btnRegister, btnLogin, btnUpdates); // Spaziatura maggiore tra i pulsanti
        vbox.setAlignment(Pos.CENTER);

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);

        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e il contenuto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, logoBox, vbox); // Aggiungi l'immagine di sfondo, il logo e il VBox dei pulsanti

        // Listener per ridimensionare l'immagine di sfondo e adattare la dimensione del testo dei pulsanti
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitWidth(newVal.doubleValue());
            double fontSize = newVal.doubleValue() / 25; // Calcola la dimensione del font basato sulla larghezza
            btnRegister.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
            btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
            btnUpdates.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitHeight(newVal.doubleValue());
        });

        // Creazione della scena
        Scene scene = new Scene(root, 400, 500); // Imposta la scena iniziale
        primaryStage.setTitle("FantaF1 - Benvenuto");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

