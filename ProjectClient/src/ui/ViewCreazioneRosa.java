package ui;

import java.util.ArrayList;

import controllers.CreazioneRosaController;
import controllers.GestioneLegaController;
import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

public class ViewCreazioneRosa {
	private CreazioneRosaController controller;
	private GestioneLegaController gestioneController;
	
	public ViewCreazioneRosa(CreazioneRosaController controller, GestioneLegaController gestioneController) {
		super();
		this.controller=controller;
		this.gestioneController=gestioneController;
	}
	
    public void showViewCreazioneRosa(Stage stage) {
    	Button creditiDisponibili = new Button("Crediti: "+controller.getCreditiDisponibili());
    	Button pilotiSelezionati = new Button("Piloti Rimasti: "+controller.getPilotiSelezionati());
    	creditiDisponibili.setDisable(true);
    	pilotiSelezionati.setDisable(true);
    	
    	TableView<Pilota> tableView = new TableView<>();

        // Crea le colonne per nome, cognome e prezzo
        TableColumn<Pilota, String> nomeColonna = new TableColumn<>("Nome");
        nomeColonna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeColonna.setPrefWidth(150);

        TableColumn<Pilota, String> cognomeColonna = new TableColumn<>("Cognome");
        cognomeColonna.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        cognomeColonna.setPrefWidth(150);
        
        TableColumn<Pilota, Double> prezzoColonna = new TableColumn<>("Prezzo");
        prezzoColonna.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        prezzoColonna.setPrefWidth(150);

        // Crea la colonna per il pulsante di acquisto/vendita
        TableColumn<Pilota, Void> azioneColonna = new TableColumn<>("Azione");
        azioneColonna.setCellFactory(param -> new TableCell<Pilota, Void>() {
            private final Button actionButton = new Button();

            public TableCell<Pilota, Void> createCell() {
                actionButton.setOnAction(event -> {
                    Pilota pilota = getTableView().getItems().get(getIndex());
                    if (controller.getRosa().containsPilota(pilota))
                    {
                    	controller.eliminaPilota(pilota);
                    	System.out.println("Compra");
                    	controller.aggiornaCreditiDisponibili(pilota.getPrezzo());
                    	controller.aggiornaPilotiSelezionati(-1);
                        creditiDisponibili.setText("Crediti: "+controller.getCreditiDisponibili());
                        pilotiSelezionati.setText("Piloti Rimasti: "+controller.getPilotiSelezionati());
                        updateButtonText(pilota);
                    } else {
                    	if((controller.getPilotiSelezionati() < controller.getMaxPilotiRosa()) && (controller.getCreditiDisponibili()-pilota.getPrezzo())>=0)
                    	{
                    		controller.aggiungiPilota(pilota);
                            controller.aggiornaCreditiDisponibili(-pilota.getPrezzo());
                            controller.aggiornaPilotiSelezionati(1);
                            creditiDisponibili.setText("Crediti: "+controller.getCreditiDisponibili());
                            pilotiSelezionati.setText("Piloti Rimasti: "+controller.getPilotiSelezionati());
                            updateButtonText(pilota);
                    	}
                    }
                });
                return this;
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Pilota pilota = getTableView().getItems().get(getIndex());
                    setGraphic(actionButton);
                    updateButtonText(pilota);
                    actionButton.setAlignment(Pos.CENTER);
                    createCell();
                }
            }

            private void updateButtonText(Pilota pilota) {
                if (controller.getRosa().containsPilota(pilota)) {
                    actionButton.setText("Vendi");
                } else {
                    actionButton.setText("Compra");
                }
            }
        });
        azioneColonna.setPrefWidth(150);

        // Aggiungi le colonne alla TableView
        tableView.getColumns().addAll(nomeColonna, cognomeColonna, prezzoColonna, azioneColonna);

        ObservableList<Pilota> piloti = FXCollections.observableArrayList(this.controller.getListaPiloti().getPiloti());
        tableView.setItems(piloti);
        tableView.setMinWidth(600);
        tableView.setMaxWidth(600);

        // Avvolgi la TableView in un ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setMinWidth(600);
        scrollPane.setMaxWidth(600);
        
        Button btnSalvataggio = new Button("Salva");
        btnSalvataggio.setPrefWidth(110);
        btnSalvataggio.setPrefHeight(30);
        btnSalvataggio.setAlignment(Pos.CENTER);
        btnSalvataggio.setOnAction(event -> {
            System.out.println("Rosa salvata");
            controller.creaRosa();
        });
        
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);
        
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150);
        logoImageView.setFitHeight(150);
        logoImageView.setPreserveRatio(true);
        logoImageView.setSmooth(true);
        
        Button btnBack = new Button("Esci");
        btnBack.setStyle("-fx-font-size: 16px;");
        btnBack.setPrefWidth(110); 
        btnBack.setPrefHeight(30);
        btnBack.setTranslateX(50);
        btnBack.setTranslateY(10);
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata precedente
            HomeGestione homeGestione = new HomeGestione(this.gestioneController);
            try {
            	homeGestione.showHomeGestione(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        //PARTE SORPA
        VBox v1 = new VBox(10, creditiDisponibili, pilotiSelezionati);
        HBox h1 = new HBox(450, btnBack, v1, logoImageView);
        h1.setTranslateY(30);
        
        //PARTE CENTRALE
        VBox v2 = new VBox(scrollPane);
        v2.setAlignment(Pos.CENTER);
        v2.setPrefHeight(300);
        
        VBox mainLayout = new VBox(100, h1, v2, btnSalvataggio);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPrefWidth(1366);
        mainLayout.setPrefHeight(768);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, mainLayout);

        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());
        
        Scene scene = new Scene(root, 1366, 768);
        stage.setTitle("Creazione Lega");
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
