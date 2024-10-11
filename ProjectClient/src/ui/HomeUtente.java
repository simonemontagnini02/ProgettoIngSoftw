package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import java.util.List;

import controllers.GestioneLegaController;

public class HomeUtente {
    
	private Utente utente;
	
	
    public HomeUtente(Utente utente) {
		super();
		this.utente = utente;
	}


	public void showHomeUtente(Stage stage) {

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

        // Pulsante "Crea Lega"
        Button btnCreaLega = new Button("Crea Lega");
        btnCreaLega.setPrefWidth(300);
        btnCreaLega.setPrefHeight(50);
        btnCreaLega.setStyle("-fx-font-weight: bold;");
        btnCreaLega.setOnAction(event -> {
            // Aggiungi qui il metodo per creare una nuova lega
            System.out.println("Crea Lega clicked!");
        });

        // Pulsante "Iscriviti a una Lega"
        Button btnIscrivitiLega = new Button("Iscriviti a una Lega");
        btnIscrivitiLega.setPrefWidth(300);
        btnIscrivitiLega.setPrefHeight(50);
        btnIscrivitiLega.setStyle("-fx-font-weight: bold;");
        btnIscrivitiLega.setOnAction(event -> {
            // Aggiungi qui il metodo per iscriversi a una lega
            System.out.println("Iscriviti a una Lega clicked!");
        });

        // ComboBox per selezionare una lega
        ComboBox<String> comboBoxLeghe = new ComboBox<>();
        List<String> leghe = utente.getLeghe(); // Ottieni la lista di leghe dall'oggetto Utente
        comboBoxLeghe.getItems().addAll(leghe); // Aggiungi le leghe al ComboBox
        comboBoxLeghe.setPromptText("Seleziona una Lega");
        comboBoxLeghe.setPrefWidth(300);
        comboBoxLeghe.setPrefHeight(40);
        
        comboBoxLeghe.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Lega selezionata: " + newValue);
                Lega lega=utente.ricercaLega(newValue);
                // Passa alla scena di gestione della lega
                GestioneLegaController controller=new GestioneLegaController(utente, lega);
                HomeGestione homeGestione = new HomeGestione(controller);
                homeGestione.showHomeGestione(stage);  // Passa lo stage corrente alla nuova scena
            }
        });
        
        // Layout verticale (VBox) con i pulsanti e la ComboBox
        VBox vbox = new VBox(20, btnCreaLega, btnIscrivitiLega, comboBoxLeghe); // Spaziatura di 20 pixel tra gli elementi
        vbox.setAlignment(Pos.CENTER);

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);

        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e i pulsanti
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, logoBox, vbox); // Aggiungi l'immagine di sfondo, il logo e il layout con i pulsanti

        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());

        // Creazione della scena
        Scene scene = new Scene(root, 1366, 768); // Imposta la scena

        // Imposta la scena al stage
        stage.setTitle("Home Utente");
        stage.setScene(scene);
    }
}
