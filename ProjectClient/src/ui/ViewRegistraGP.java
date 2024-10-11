package ui;

import java.time.LocalDateTime;
import java.time.LocalTime;

import controllers.AmministratoreController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Amministratore;
import models.GP;

public class ViewRegistraGP {
	
	private AmministratoreController amministratoreController;
	
	public ViewRegistraGP(AmministratoreController amministratoreController) {
		super();
		this.amministratoreController = amministratoreController;
	}



	public void showViewRegistraGP (Stage stage) {
		
		// Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Layout per posizionare il logo in alto a destra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(-30);
        logoBox.setTranslateY(10);  
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
        
        btnBack.setStyle("-fx-font-weight: bold;");
        
        // Layout per il pulsante "Indietro" in alto a sinistra
        VBox backButtonBox = new VBox(btnBack);
        backButtonBox.setAlignment(Pos.CENTER);

        backButtonBox.setPrefWidth(120); 
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
        
        ComboBox<GP> comboBoxGP = new ComboBox<>();
        //comboBoxGP.getItems().addAll(amministratoreController.getGPList());
        comboBoxGP.getItems().addAll(
                new GP("GP Australia", LocalDateTime.of(2024, 3, 17, 14, 0)),
                new GP("GP Italia", LocalDateTime.of(2024, 9, 8, 15, 0))
            );
        
        // Pulsante per aggiungere un nuovo GP
        Button btnAddGP = new Button("Aggiungi GP");
        Button btnRemoveGP = new Button("Rimuovi GP");
        
        HBox h2 = new HBox(50, btnAddGP, btnRemoveGP);
        h2.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, h2, comboBoxGP);
        vbox.setAlignment(Pos.CENTER);
        HBox h1 = new HBox(1100, backButtonBox, logoBox);
        h1.setAlignment(Pos.TOP_CENTER);
        
        VBox v1 = new VBox(100, h1, vbox);
        v1.setAlignment(Pos.TOP_CENTER);
        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e il contenuto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, v1); // Aggiungi l'immagine di sfondo, il logo e il VBox dei pulsanti
        
        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());
        
        // Azione del pulsante per aggiungere un GP
        btnAddGP.setOnAction(event -> showAddGPDialog(comboBoxGP));
        btnRemoveGP.setOnAction(event -> {
        	GP selectedGP = comboBoxGP.getSelectionModel().getSelectedItem();
        	if(selectedGP != null) {
        		comboBoxGP.getItems().remove(selectedGP);
        		//this.amministratoreController.removeGP(nuovoGP);
        	} else {
        		Alert alert = new Alert(AlertType.WARNING, "Seleziona un GP da rimuovere.");
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
	
	private void showAddGPDialog(ComboBox<GP> comboBoxGP) {
		Stage dialog = new Stage();
		
		//Label per nomeGP
		Label nameLabel = new Label("Nome GP:");
		TextField nameField = new TextField();
		
		//Label per dataGP
		Label dateLabel = new Label("Data:");
	    DatePicker datePicker = new DatePicker();
	    
	    //Label per Ore
	    Label hourLabel = new Label("Ore:");
	    Spinner<Integer> hourSpinner = new Spinner<>(0,23, 12); //Valore iniziale 12
	    
	    //Label per Ore
	    Label minuteLabel = new Label("Minuti:");
	    Spinner<Integer> minuteSpinner = new Spinner<>(0,59, 0); //Valore iniziale 12
	    
	    Button btnAdd = new Button("Aggiungi");
	    
	    btnAdd.setOnAction(e -> {
	    	String nome = nameField.getText();
	    	LocalDateTime dateTime = LocalDateTime.of(datePicker.getValue(),
	    			LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue()));
	        if (nome != null && dateTime != null) {
	        	GP nuovoGP = new GP(nome, dateTime);
	            comboBoxGP.getItems().add(nuovoGP);
	            //this.amministratoreController.saveGP(nuovoGP);
	            dialog.close();
            }
	    });
	    
	    Button btnDiscard = new Button("Scarta modifiche");
	    
	    btnDiscard.setOnAction(e -> {
	    	dialog.close();
	    });
	    
	    HBox h2 = new HBox(btnAdd, btnDiscard);
	    VBox dialogVbox = new VBox(10, nameLabel, nameField, dateLabel, datePicker,
	    		hourLabel, hourSpinner, minuteLabel, minuteSpinner, h2);
	    
	    Scene dialogScene = new Scene(dialogVbox, 250, 300);
	    
	    dialog.setTitle("Aggiungi GP al calendario");
	    dialog.setScene(dialogScene);
        
	    dialog.show();
	}
}
