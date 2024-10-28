package ui;

import java.time.LocalDateTime;
import java.time.LocalTime;

import controllers.AmministratoreController;
import models.GP;
import models.Pilota;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewRegistraPiloti {
	
	private AmministratoreController amministratoreController;
	
	public ViewRegistraPiloti(AmministratoreController amministratoreController) {
		super();
		this.amministratoreController = amministratoreController;
	}
	
	public void showViewRegistraPiloti (Stage stage) {
		
		// Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Layout per posizionare il logo in alto a destra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(-30);
        logoBox.setPrefWidth(150);  
        logoBox.setPrefHeight(150);
        
        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);
        
        // Bottone per tornare alla Home Amministratore
        Button btnBack = new Button("Indietro");
        btnBack.setPrefWidth(110); // Imposta una larghezza minore per il pulsante Indietro
        btnBack.setPrefHeight(30);
        btnBack.setTranslateX(50);
        btnBack.setTranslateY(10);
        btnBack.setStyle("-fx-font-size: 16px;");
        
        // Layout per il pulsante "Indietro" in alto a sinistra
        VBox backButtonBox = new VBox(btnBack);
        backButtonBox.setAlignment(Pos.CENTER);

        backButtonBox.setPrefWidth(110); 
        backButtonBox.setPrefHeight(50);
        
        // Gestore per il pulsante Indietro
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata principale
            HomeAmministratore homeAmministratore = new HomeAmministratore(this.amministratoreController);
            try {
                homeAmministratore.showHomeAmministratore(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        ComboBox<Pilota> comboBoxPiloti = new ComboBox<>();
        comboBoxPiloti.getItems().addAll(amministratoreController.getPilotiList());
        /*comboBoxPiloti.getItems().addAll(
                new Pilota("Max", "Verstappen", 95),
                new Pilota("Charles", "Leclerc", 70)
            );*/
        
        // Pulsante per aggiungere un nuovo Pilota
        Button btnAddPilota = new Button("Aggiungi Pilota");
        Button btnRemovePilota = new Button("Rimuovi Pilota");
        
        HBox h2 = new HBox(50, btnAddPilota, btnRemovePilota);
        h2.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, h2, comboBoxPiloti);
        vbox.setAlignment(Pos.CENTER);
        HBox h1 = new HBox(1100, backButtonBox, logoBox);
        h1.setAlignment(Pos.TOP_CENTER);
        
        VBox v1 = new VBox(100, h1, vbox);
        v1.setAlignment(Pos.TOP_CENTER);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, v1); // Aggiungi l'immagine di sfondo, il logo e il VBox dei pulsanti
        
        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());
        
        // Azione dei pulsanti
        btnAddPilota.setOnAction(event -> showAddPilotaDialog(comboBoxPiloti));
        btnRemovePilota.setOnAction(event -> {
        	Pilota selectedPilota = comboBoxPiloti.getSelectionModel().getSelectedItem();
        	if(selectedPilota != null) {
        		comboBoxPiloti.getItems().remove(selectedPilota);
        		this.amministratoreController.removePilota(selectedPilota);
        	} else {
        		Alert alert = new Alert(AlertType.WARNING, "Seleziona un Pilota da rimuovere.");
        		alert.setTitle("Attenzione");
                alert.showAndWait();
        	}
        });
        
        Scene scene = new Scene(root, 1366, 768);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setTitle("Calendario");
        stage.setScene(scene);
        
        stage.show();
	}
	
	private void showAddPilotaDialog(ComboBox<Pilota> comboBoxPiloti) {
		Stage dialog = new Stage();
		
		//Label per nomePilota
		Label nameLabel = new Label("Nome Pilota:");
		TextField nameField = new TextField();
		
		//Label per cognomePilota
		Label surnameLabel = new Label("Cognome Pilota:");
		TextField surnameField = new TextField();
		
		//Label per prezzoPilota
		Label priceLabel = new Label("Prezzo:");
	    Spinner<Integer> priceSpinner = new Spinner<>(1, 100, 1); //Valore iniziale 1
	    
	    Button btnAdd = new Button("Aggiungi");
	    
	    btnAdd.setOnAction(e -> {
	    	String nome = nameField.getText();
	    	String cognome = surnameField.getText();
	    	int prezzo = priceSpinner.getValue();
	        if (nome != null && cognome != null) {
	        	Pilota nuovoPilota = new Pilota(nome, cognome, prezzo);
	            comboBoxPiloti.getItems().add(nuovoPilota);
	            this.amministratoreController.savePilota(nuovoPilota);
	            dialog.close();
            } else {
            	Alert alert2 = new Alert(Alert.AlertType.ERROR);
            	alert2.setTitle("Errore");
                alert2.setHeaderText("Dati mancanti");
                alert2.setContentText("Assicurati di aver inserito sia il nome del GP che una data valida.");
                alert2.showAndWait();
            }
	    });
	    
	    Button btnDiscard = new Button("Scarta modifiche");
	    
	    btnDiscard.setOnAction(e -> {
	    	dialog.close();
	    });
	    
	    HBox h2 = new HBox(btnAdd, btnDiscard);
	    VBox dialogVbox = new VBox(10, nameLabel, nameField, surnameLabel, surnameField,
	    		priceLabel, priceSpinner, h2);
	    
	    Scene dialogScene = new Scene(dialogVbox, 250, 300);
	    
	    dialog.setTitle("Aggiungi GP al calendario");
	    dialog.setScene(dialogScene);
        
	    dialog.show();
	}
	
}
