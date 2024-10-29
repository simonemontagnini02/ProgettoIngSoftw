package ui;

import java.util.ArrayList;
import java.util.List;

import controllers.CreazioneRosaController;
import controllers.GestioneLegaController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

public class HomeGestione {
	private GestioneLegaController controller;
	
	
    public HomeGestione(GestioneLegaController controller) {
		super();
		this.controller=controller;
	}
	
    private HBox h1 = null;
	private HBox h3 = null;
	private VBox centralBox;
	private ListView<String> listView;
	private ComboBox<String> comboBoxRose;
	private List<String> nomiScuderia;
	private VBox mainLayout;
	private StackPane root;
	private Scene scene;
    
    public void showHomeGestione(Stage stage) {
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
        logoImageView.setFitWidth(150);
        logoImageView.setFitHeight(150);
        logoImageView.setPreserveRatio(true);
        logoImageView.setSmooth(true);

        Label nomeLega = new Label(controller.getNomeLega());
        nomeLega.setStyle("-fx-font-weight: bold; -fx-font-size: 48px; -fx-text-fill: white;");
        nomeLega.setTranslateY(30);
        Label nomeScuderia = new Label(controller.getNomeScuderia());
        nomeScuderia.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #800000;");
        
        // Creazione dei pulsanti
        Button btnRosa = new Button("Crea Rosa");
        Button btnFormazione = new Button("Schiera Formazione");
        Button btnCalcola = new Button("Calcola Giornata");
        Button btnGestione = new Button("Gestione Partecipanti");
        Button homeButton = new Button();
        Button classificaButton = new Button();
        Button risultatiButton = new Button();
        Button roseButton = new Button();
        Button btnBack = new Button("Esci");
        
        // Imposta una larghezza e un'altezza preferita per i pulsanti (tutti uguali)
        double buttonWidth = 300;
        double buttonHeight = 50;
        btnRosa.setPrefWidth(buttonWidth);
        btnRosa.setPrefHeight(buttonHeight);
        btnFormazione.setPrefWidth(buttonWidth);
        btnFormazione.setPrefHeight(buttonHeight);
        btnRosa.setPrefSize(buttonWidth, buttonHeight);
        btnFormazione.setPrefSize(buttonWidth, buttonHeight);
        btnCalcola.setPrefWidth(buttonWidth);
        btnCalcola.setPrefHeight(buttonHeight);
        btnGestione.setPrefWidth(buttonWidth);
        btnGestione.setPrefHeight(buttonHeight);
        btnCalcola.setPrefSize(buttonWidth, buttonHeight);
        btnGestione.setPrefSize(buttonWidth, buttonHeight);
        
        buttonWidth = 200;
        homeButton.setPrefWidth(buttonWidth);
        homeButton.setPrefHeight(buttonHeight);
        classificaButton.setPrefWidth(buttonWidth);
        classificaButton.setPrefHeight(buttonHeight);
        risultatiButton.setPrefWidth(buttonWidth);
        risultatiButton.setPrefHeight(buttonHeight);
        roseButton.setPrefWidth(buttonWidth);
        roseButton.setPrefHeight(buttonHeight);
        classificaButton.setPrefSize(buttonWidth, buttonHeight);
        risultatiButton.setPrefSize(buttonWidth, buttonHeight);
        roseButton.setPrefSize(buttonWidth, buttonHeight); 
        
        Label text = new Label("Home");
        text.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
        Image homeImage = new Image(getClass().getResourceAsStream("/img/home.png"));
        ImageView homeView = new ImageView(homeImage);
        homeView.setFitWidth(35);
        homeView.setFitHeight(35); 
        HBox content = new HBox(10, text, homeView);
        content.setAlignment(Pos.CENTER);
        homeButton.setGraphic(content);
        
        btnBack.setStyle("-fx-font-size: 16px;");
        text = new Label("Classifica");
        text.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
        Image classificaImage = new Image(getClass().getResourceAsStream("/img/classifica.png"));
        ImageView classificaView = new ImageView(classificaImage);
        classificaView.setFitWidth(60);
        classificaView.setFitHeight(32); 
        content = new HBox(10, text, classificaView);
        content.setAlignment(Pos.CENTER);
        classificaButton.setGraphic(content);
        
        text = new Label("Rose");
        text.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
        Image roseImage = new Image(getClass().getResourceAsStream("/img/rose.png"));
        ImageView roseView = new ImageView(roseImage);
        roseView.setFitWidth(35);
        roseView.setFitHeight(35); 
        content = new HBox(10, text, roseView);
        content.setAlignment(Pos.CENTER);
        roseButton.setGraphic(content);
        
        text = new Label("Risultati");
        text.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
        Image risultatiImage = new Image(getClass().getResourceAsStream("/img/risultati.png"));
        ImageView risultatiView = new ImageView(risultatiImage);
        risultatiView.setFitWidth(35);
        risultatiView.setFitHeight(35); 
        content = new HBox(10, text, risultatiView);
        content.setAlignment(Pos.CENTER);
        risultatiButton.setGraphic(content);
        
        btnBack.setPrefWidth(110);
        btnBack.setPrefHeight(30);
        btnBack.setTranslateX(50);
        btnBack.setTranslateY(10);
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata precedente
            HomeUtente homeUtente = new HomeUtente(GestioneLegaController.getUtente());
            try {
                homeUtente.showHomeUtente(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Gestori degli eventi per ogni pulsante
        btnRosa.setOnAction(event -> {
        	controller.refresh();
        	CreazioneRosaController creazioneRosaController=new CreazioneRosaController(GestioneLegaController.getPartecipante(), GestioneLegaController.getLega());
            ViewCreazioneRosa viewCreazioneRosa = new ViewCreazioneRosa(creazioneRosaController, controller);
        	try {
        		viewCreazioneRosa.showViewCreazioneRosa(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnFormazione.setOnAction(event -> {
            System.out.println("Vai a schiera formazione");
        });
        
        btnCalcola.setOnAction(event -> {
            System.out.println("Vai a calcola giornata");
        });
        
        btnGestione.setOnAction(event -> {
            System.out.println("Vai a gestione partecipanti");
        });
        
        homeButton.setOnAction(event -> {
        	centralBox.getChildren().setAll(btnRosa, btnFormazione);
        	centralBox.setPrefHeight(135);
        	if(controller.isCapo()) {
            	centralBox.getChildren().addAll(btnCalcola, btnGestione);
            	centralBox.setSpacing(25);
            }
            mainLayout.getChildren().setAll(h1, centralBox, h3);
            if(controller.isCapo()) {
            	mainLayout.setSpacing(125);
            }
        	root.getChildren().setAll(backgroundImageView, mainLayout);
            scene.setRoot(root);
        });
        
        classificaButton.setOnAction(event -> {
            System.out.println("Visualizza classifica");
        });

        risultatiButton.setOnAction(event -> {
            System.out.println("Visualizza Risultati");
        });

        roseButton.setOnAction(event -> {        	
        	comboBoxRose = new ComboBox<>();
            nomiScuderia = new ArrayList<>(controller.getNomiScuderie()); 
            comboBoxRose.getItems().addAll(nomiScuderia);
            comboBoxRose.setPromptText("Seleziona una rosa");
            comboBoxRose.setPrefWidth(300);
            comboBoxRose.setPrefHeight(40);
            controller.refresh();
            
            comboBoxRose.valueProperty().addListener((observable, oldValue, newValue) -> {
            	controller.refresh();
                if (newValue != null) {
                    System.out.println("Rosa selezionata: " + newValue);
                    
                    ListView<String> listView = new ListView<>();
                    ObservableList<String> rosa = FXCollections.observableArrayList(controller.getRosa(newValue));
                    listView.setItems(rosa);
                    
                    centralBox.getChildren().setAll(comboBoxRose, listView);
                    centralBox.setMaxWidth(300);
                    mainLayout.getChildren().setAll(h1, centralBox, h3);
                	root.getChildren().setAll(backgroundImageView, mainLayout);
                    scene.setRoot(root);
                }
            });
            
            centralBox.getChildren().setAll(comboBoxRose);
            centralBox.setPrefHeight(136);
            centralBox.setSpacing(30);
            mainLayout.getChildren().setAll(h1, centralBox, h3);
            mainLayout.setSpacing(200);
        	root.getChildren().setAll(backgroundImageView, mainLayout);
            scene.setRoot(root);
        });
        
        VBox backButtonBox = new VBox(btnBack);
        backButtonBox.setAlignment(Pos.CENTER);
        backButtonBox.setPrefWidth(120); 
        backButtonBox.setPrefHeight(50);

        // Layout verticale (VBox) con i pulsanti centrati
        VBox vbox = new VBox(20, nomeLega, nomeScuderia);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setTranslateY(-10);
        vbox.setPrefWidth(330); 
        vbox.setPrefHeight(100);
        
        // Layout per posizionare il logo in alto a sinistra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(-30);   
        logoBox.setPrefWidth(150); 
        logoBox.setPrefHeight(150);
        
        h1 = new HBox(390, backButtonBox, vbox, logoBox);
        h1.setAlignment(Pos.TOP_CENTER);
        
        centralBox = new VBox(30, btnRosa, btnFormazione);
        centralBox.setAlignment(Pos.CENTER);
        
        if(controller.isCapo()) {
        	centralBox.getChildren().addAll(btnCalcola, btnGestione);
        	centralBox.setSpacing(25);
        }
        
        h3 = new HBox(10, homeButton, classificaButton, risultatiButton, roseButton);
        h3.setAlignment(Pos.BOTTOM_CENTER);
        h3.setTranslateY(-30);
        
        mainLayout = new VBox(200, h1, centralBox, h3);
        if(controller.isCapo()) {
        	mainLayout.setSpacing(125);
        }
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPrefWidth(1366);
        mainLayout.setPrefHeight(768);
        		
        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e il contenuto
        root = new StackPane();
        root.getChildren().addAll(backgroundImageView, mainLayout);

        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());

        // Creazione della scena
        scene = new Scene(root, 1366, 768);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Gestione Lega");
        stage.setScene(scene);
    }
}
