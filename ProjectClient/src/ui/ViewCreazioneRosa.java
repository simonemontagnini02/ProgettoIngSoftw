package ui;

import java.util.ArrayList;

import controllers.CreazioneRosaController;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

public class ViewCreazioneRosa {
	private CreazioneRosaController controller=null;
	private Partecipante p;
	private ListaPiloti listaPiloti;
	private Rosa rosa;
	
	public ViewCreazioneRosa(Lega lega, Partecipante part) {
		super();
		this.p=part;
		this.controller=new CreazioneRosaController(part);
		this.listaPiloti=this.controller.getListaPiloti();
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
            private final Button actionButton = new Button("Compra");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Imposta l'azione del pulsante
                    setGraphic(actionButton);

                    // Gestisci il clic sul pulsante
                    actionButton.setOnAction(event -> {
                        if (actionButton.getText().equals("Compra")) {
                            // Se il pulsante dice "Compra", acquistiamo il pilota
                            actionButton.setText("Vendi");
                            Pilota pilota = getTableView().getItems().get(getIndex());
                            System.out.println("Comprato: " + pilota.getNome() + " " + pilota.getCognome() + " per " + pilota.getPrezzo());
                        } else {
                            // Se il pulsante dice "Vendi", vendiamo il pilota
                            actionButton.setText("Compra");
                            Pilota pilota = getTableView().getItems().get(getIndex());
                            System.out.println("Venduto: " + pilota.getNome() + " " + pilota.getCognome() + " per " + pilota.getPrezzo());
                        }
                    });
                }
            }
        });

        // Aggiungi le colonne alla TableView
        tableView.getColumns().addAll(nomeColonna, cognomeColonna, prezzoColonna, azioneColonna);

        // Crea una lista di piloti
        ObservableList<Pilota> piloti = FXCollections.observableArrayList();
    	for(Pilota pilota : this.listaPiloti.getPiloti())
    	{
    		piloti.add(new CheckBox(pilota.getNome()+" "+pilota.getCognome()+" "+pilota.getPrezzo()));
    	}

        // Aggiungi i dati alla TableView
        tableView.setItems(piloti);

        // Avvolgi la TableView in un ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true); // Rende la TableView larga come lo ScrollPane

        VBox vbox = new VBox(scrollPane);
        Scene scene = new Scene(root, 1366, 768);
        stage.setTitle("Creazione Lega");
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
