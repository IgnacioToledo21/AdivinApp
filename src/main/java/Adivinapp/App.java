package Adivinapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private int numeroAdivinar;  // Número que se debe adivinar
    private int intentos;        // Contador de intentos

    @Override
    public void start(Stage primaryStage) {
        numeroAdivinar = (int) (Math.random() * 100) + 1; // Número aleatorio entre 1 y 100
        intentos = 0;

        // Mostrar el número a adivinar en la consola para depuración
        System.out.println("Número a adivinar: " + numeroAdivinar);

        // Crear los elementos de la interfaz
        Label label = new Label("Introduce un número del 1 al 100:");
        TextField input = new TextField();
        Button botonComprobar = new Button("Comprobar");

        // Configurar acción del botón "Comprobar"
        botonComprobar.setOnAction(e -> {
            System.out.println("Número a adivinar actual: " + numeroAdivinar); // Verificar el valor actual del número

            try {
                String texto = input.getText();
                if (texto.isEmpty()) {
                    mostrarWarning("Campo vacío", "Por favor, introduce un número.");
                    return;
                }

                int numeroIntroducido = Integer.parseInt(texto);
                intentos++;

                if (numeroIntroducido < 1 || numeroIntroducido > 100) {
                    mostrarWarning("Número fuera de rango", "Por favor, introduce un número entre 1 y 100.");
                } else if (numeroIntroducido > numeroAdivinar) {
                    mostrarError("Intento fallido", "El número a adivinar es menor que " + numeroIntroducido + ".");
                } else if (numeroIntroducido < numeroAdivinar) {
                    mostrarError("Intento fallido", "El número a adivinar es mayor que " + numeroIntroducido + ".");
                } else {
                    mostrarExito("¡Has ganado!", "Solo has necesitado " + intentos + " intentos.");
                    reiniciarJuego();  // Reiniciar el juego para una nueva ronda
                }
            } catch (NumberFormatException ex) {
                mostrarWarning("Entrada inválida", "Por favor, introduce un número válido.");
            }
        });

        // Layout de la interfaz
        VBox layout = new VBox(10, label, input, botonComprobar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Adivina el Número");
        primaryStage.show();
    }

    private void mostrarExito(String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void mostrarError(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void mostrarWarning(String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void reiniciarJuego() {
        numeroAdivinar = (int) (Math.random() * 100) + 1;
        intentos = 0;
        // Mostrar el nuevo número a adivinar en la consola para depuración
        System.out.println("Nuevo número a adivinar: " + numeroAdivinar);
    }

}

