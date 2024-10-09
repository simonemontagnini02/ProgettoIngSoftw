package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane; // Usa StackPane per sovrapporre gli elementi
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FantaF1App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Creazione dei pulsanti
        Button btnRegister = new Button("Registrazione");
        Button btnLogin = new Button("Login");
        Button btnUpdates = new Button("Aggiornamenti");

        // Imposta una larghezza preferita maggiore per i pulsanti (ad esempio, 250 pixel)
        btnRegister.setPrefWidth(250);
        btnLogin.setPrefWidth(250);
        btnUpdates.setPrefWidth(250);

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

        // Layout verticale (VBox) con il logo e i pulsanti sotto
        VBox vbox = new VBox(30, logoImageView, btnRegister, btnLogin, btnUpdates); // Spaziatura maggiore tra elementi
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(400);  // Larghezza preferita della scena
        vbox.setPrefHeight(500); // Altezza preferita della scena

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Usa StackPane per sovrapporre l'immagine di sfondo e il contenuto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, vbox); // Aggiungi l'immagine di sfondo e il VBox

        // Creazione della scena
        Scene scene = new Scene(root, 400, 500); // Imposta larghezza e altezza
        primaryStage.setTitle("FantaF1 - Benvenuto");
        primaryStage.setScene(scene);
        
        // Listener per ridimensionare l'immagine di sfondo quando la finestra cambia dimensioni
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitWidth(newVal.doubleValue());
        });
        
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitHeight(newVal.doubleValue());
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
