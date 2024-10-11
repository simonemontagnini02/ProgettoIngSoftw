package ui;

import controllers.AmministratoreController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeAmministratore extends Application{
	public void start (Stage stage) {
		// Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);
        
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
        Button btnPoints = new Button("Registra punteggi");
        Button btnPilots = new Button("Registra piloti");
        Button btnGP = new Button("Registra GP");
        //Button btnBack = new Button("Indietro");
        
        // Imposta una larghezza e un'altezza preferita per i pulsanti (tutti uguali)
        double buttonWidth = 300;
        double buttonHeight = 50;
        btnPoints.setPrefWidth(buttonWidth);
        btnPoints.setPrefHeight(buttonHeight);
        btnPilots.setPrefWidth(buttonWidth);
        btnPilots.setPrefHeight(buttonHeight);
        btnGP.setPrefWidth(buttonWidth);
        btnGP.setPrefHeight(buttonHeight);
        /*btnBack.setPrefWidth(110); // Imposta una larghezza minore per il pulsante Indietro
        btnBack.setPrefHeight(30);
        
        //Gestore per il pulsante Indietro
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata principale
            FantaF1App mainApp = new FantaF1App();
            try {
                mainApp.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }); */
        
        btnPoints.setOnAction(event -> {
            System.out.println("Vai a registra punteggi");
        });
        
        btnGP.setOnAction(event -> {
            System.out.println("Vai a registra GP");
            AmministratoreController amministratoreController = new AmministratoreController();
            ViewRegistraGP viewRegistraGP = new ViewRegistraGP(amministratoreController);
            try {
            	viewRegistraGP.showViewRegistraGP(stage);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });
        
        btnPilots.setOnAction(event -> {
            System.out.println("Vai a registra piloti");
        });
        
        VBox vbox = new VBox(30, btnPilots, btnGP, btnPoints); // Spaziatura maggiore tra i pulsanti
        vbox.setAlignment(Pos.CENTER);
        
        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e il contenuto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, logoBox, vbox); // Aggiungi l'immagine di sfondo, il logo e il VBox dei pulsanti
        
        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());
        
        // Creazione della scena
        Scene scene = new Scene(root, 1366, 768); // Imposta la scena iniziale
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setTitle("Home Amministratore");
        stage.setScene(scene);
        
        stage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
