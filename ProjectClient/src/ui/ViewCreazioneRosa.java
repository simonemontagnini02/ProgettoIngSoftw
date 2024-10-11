package ui;

import java.util.ArrayList;

import controllers.CreazioneRosaController;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

public class ViewCreazioneRosa {
	private CreazioneRosaController controller;
	
	public ViewCreazioneRosa(CreazioneRosaController controller) {
		super();
		this.controller=controller;
	}
	
    public void showViewCreazioneRosa(Stage stage) {
    	Button creditiDisponibili = new Button("Crediti: "+controller.getCreditiDisponibili());
    	Button pilotiSelezionati = new Button("Piloti Rimasti: "+controller.getPilotiSelezionati());
    	
    	TableView<Pilota> tableView = new TableView<>();

        // Crea le colonne per nome, cognome e prezzo
        TableColumn<Pilota, String> nomeColonna = new TableColumn<>("Nome");
        nomeColonna.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Pilota, String> cognomeColonna = new TableColumn<>("Cognome");
        cognomeColonna.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        TableColumn<Pilota, Double> prezzoColonna = new TableColumn<>("Prezzo");
        prezzoColonna.setCellValueFactory(new PropertyValueFactory<>("prezzo"));

        // Crea la colonna per il pulsante di acquisto/vendita
        TableColumn<Pilota, Void> azioneColonna = new TableColumn<>("Azione");
        azioneColonna.setCellFactory(param -> new TableCell<Pilota, Void>() {
            private final Button actionButton = new Button();

            public TableCell<Pilota, Void> createCell() {
                actionButton.setOnAction(event -> {
                    Pilota pilota = getTableView().getItems().get(getIndex());
                    if (controller.getRosa().containsPilota(pilota)) {
                    	controller.eliminaPilota(pilota);
                    	controller.aggiornaCrediti(pilota.getPrezzo());
                        creditiDisponibili.setText("Crediti: "+controller.getCreditiDisponibili());
                    } else {
                        rosa.aggiungiPilota(pilota);
                    }
                    updateButtonText(pilota);
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
                }
            }

            private void updateButtonText(Pilota pilota) {
                if (rosa.containsPilota(pilota)) {
                    actionButton.setText("Vendi");
                } else {
                    actionButton.setText("Compra");
                }
            }
        });

        // Aggiungi le colonne alla TableView
        tableView.getColumns().addAll(nomeColonna, cognomeColonna, prezzoColonna, azioneColonna);

        ObservableList<Pilota> piloti = FXCollections.observableArrayList(this.listaPiloti.getPiloti());
        tableView.setItems(piloti);

        // Avvolgi la TableView in un ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(scrollPane);
        Scene scene = new Scene(root, 1366, 768);
        stage.setTitle("Creazione Lega");
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
