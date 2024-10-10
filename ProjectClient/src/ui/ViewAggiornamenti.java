package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAggiornamenti {

    public void showViewAggiornamenti(Stage stage) {
        // Controlla se il stage è in modalità schermo intero
        boolean isFullScreen = stage.isFullScreen();

        // Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Layout per posizionare il logo in alto a destra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.TOP_RIGHT);
        logoBox.setTranslateX(-50); // Sposta il logo
        logoBox.setTranslateY(10);  
        logoBox.setPrefWidth(1024);  // Larghezza della scena in base alla risoluzione dell'immagine di sfondo
        logoBox.setPrefHeight(1024); // Altezza della scena

        // Creazione dei pulsanti
        Button btnCheckUpdates = new Button("Verifica Aggiornamenti");
        Button btnUpdate = new Button("Aggiorna");
        Button btnBack = new Button("Indietro");

        double buttonWidth = 300;
        double buttonHeight = 50;
        btnCheckUpdates.setPrefWidth(buttonWidth);
        btnCheckUpdates.setPrefHeight(buttonHeight);
        btnUpdate.setPrefWidth(buttonWidth);
        btnUpdate.setPrefHeight(buttonHeight);
        btnBack.setPrefWidth(100); // Imposta una larghezza minore per il pulsante Indietro
        btnBack.setPrefHeight(30);

        // Imposta il testo in grassetto
        btnCheckUpdates.setStyle("-fx-font-weight: bold;");
        btnUpdate.setStyle("-fx-font-weight: bold;");
        btnBack.setStyle("-fx-font-weight: bold;");

        // Gestori degli eventi per i pulsanti
        btnCheckUpdates.setOnAction(event -> {
            System.out.println("Controllo degli aggiornamenti...");
        });

        btnUpdate.setOnAction(event -> {
            System.out.println("Avvio aggiornamento...");
        });

        // Gestore per il pulsante Indietro
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata principale
            FantaF1App mainApp = new FantaF1App();
            try {
                mainApp.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Layout verticale (VBox) con i pulsanti centrati
        VBox vbox = new VBox(20, btnCheckUpdates, btnUpdate); // Spaziatura tra i pulsanti
        vbox.setAlignment(Pos.CENTER);

        // Layout per il pulsante "Indietro" in alto a sinistra
        VBox backButtonBox = new VBox(btnBack);
        backButtonBox.setAlignment(Pos.TOP_LEFT);
        backButtonBox.setTranslateX(20); // Margine sinistro
        backButtonBox.setTranslateY(20); // Margine superiore

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);

        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e i pulsanti
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, logoBox, vbox, backButtonBox); // Aggiungi l'immagine di sfondo, il logo, i pulsanti e il pulsante Indietro

        // Listener per ridimensionare l'immagine di sfondo
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitWidth(newVal.doubleValue());
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitHeight(newVal.doubleValue());
        });

        // Creazione della scena
        Scene scene = new Scene(root, 1280, 720); // Imposta la scena

        // Imposta la scena al stage
        stage.setScene(scene);

        // Ripristina lo stato di schermo intero se era attivo
        stage.setFullScreen(isFullScreen);
    }
}


