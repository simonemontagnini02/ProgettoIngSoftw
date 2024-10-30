package ui;

import controllers.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

public class ViewLogin {
    private LoginController loginController;
    private Label errorLabel; // Etichetta per visualizzare errori

    public ViewLogin(LoginController loginController) {
        super();
        this.loginController = loginController;
    }

    public void showViewLogin(Stage stage) {
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

        // Campi di input per username e password con le etichette
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        usernameLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 128, 64), CornerRadii.EMPTY, null)));

        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300);
        usernameField.setPrefHeight(50);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        passwordLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 128, 64), CornerRadii.EMPTY, null)));

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(50);

        // Layout orizzontale per ogni coppia di etichetta e campo di input
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);
        
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        // Pulsante di login
        Button btnLogin = new Button("Login");
        btnLogin.setPrefWidth(100);
        btnLogin.setPrefHeight(50);
        btnLogin.setStyle("-fx-font-weight: bold;");

        // Etichetta per messaggi di errore
        errorLabel = new Label();
        errorLabel.setVisible(false); // Nascondi l'etichetta all'inizio

        // Gestore dell'evento per il pulsante Login
        btnLogin.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean success = loginController.login(username, password, stage);
            if (!success) {
                erroreLogin(); // Mostra errore se il login non ha avuto successo
            } else {
                errorLabel.setVisible(false); // Nascondi l'etichetta se il login ha avuto successo
            }
        });

        // Layout verticale (VBox) con i campi di input, il pulsante di login e l'etichetta di errore
        VBox vbox = new VBox(20, usernameBox, passwordBox, btnLogin, errorLabel);
        vbox.setAlignment(Pos.CENTER);

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);

        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e i campi di login
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, logoBox, vbox);

        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());

        // Creazione della scena
        Scene scene = new Scene(root, 1366, 768);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Imposta la scena al stage
        stage.setTitle("Login");
        stage.setScene(scene);
    }
    
    public void erroreLogin() {
        errorLabel.setText("Username o password errati"); // Imposta il messaggio di errore
        errorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        errorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 128, 64), CornerRadii.EMPTY, null)));
        errorLabel.setVisible(true); // Mostra l'etichetta di errore
    }
}

