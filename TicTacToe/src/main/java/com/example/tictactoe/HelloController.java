package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    // Pulsanti della griglia
    @FXML private Button button00;
    @FXML private Button button01;
    @FXML private Button button02;
    @FXML private Button button10;
    @FXML private Button button11;
    @FXML private Button button12;
    @FXML private Button button20;
    @FXML private Button button21;
    @FXML private Button button22;

    // Etichetta per mostrare lo stato del gioco (vincitore o messaggio)
    @FXML private Label whoWins;

    // Variabili per la logica del gioco
    private static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private static char currentPlayer = 'X'; // Giocatore corrente
    private Button[][] buttons = new Button[3][3]; // Matrice di pulsanti

    // Metodo per inizializzare la griglia e collegare i pulsanti
    @FXML
    private void initialize() {
        buttons[0][0] = button00;
        buttons[0][1] = button01;
        buttons[0][2] = button02;
        buttons[1][0] = button10;
        buttons[1][1] = button11;
        buttons[1][2] = button12;
        buttons[2][0] = button20;
        buttons[2][1] = button21;
        buttons[2][2] = button22;

        // Aggiungi i gestori degli eventi per i pulsanti
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnAction(event -> markXorO(row, col));
            }
        }

        // Inizializza il messaggio con il giocatore che inizia
        whoWins.setText("Giocatore " + currentPlayer + " inizia");
    }

    // Metodo per gestire la mossa del giocatore
    @FXML
    private void markXorO(int row, int col) {
        // Verifica se la casella è vuota
        if (board[row][col] == ' ') {
            // Segna la mossa del giocatore
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer)); // Cambia il testo del pulsante

            // Verifica se il giocatore ha vinto
            if (checkWin()) {
                whoWins.setText("Giocatore " + currentPlayer + " ha vinto!");
                disableButtons();  // Disabilita i pulsanti dopo la vittoria
            } else if (isBoardFull()) {
                whoWins.setText("Partita terminata con un pareggio.");
                disableButtons();  // Disabilita i pulsanti dopo il pareggio
            } else {
                // Cambia il turno
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                whoWins.setText("Giocatore " + currentPlayer + " è il turno.");
            }
        }
    }

    // Metodo per controllare se un giocatore ha vinto
    private boolean checkWin() {
        // Controllo righe e colonne
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Controllo diagonali
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // Metodo per controllare se la griglia è piena
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Metodo per disabilitare i pulsanti alla fine del gioco
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }

    // Funzione per il pulsante "Play" che resetta il gioco
    @FXML
    void onPlayButtonClick(ActionEvent event) {
        resetGame();
    }


    private void resetGame() {
        board = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        currentPlayer = 'X';  // Giocatore X inizia
        whoWins.setText("Giocatore " + currentPlayer + " inizia");


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setDisable(false);
            }
        }
    }
}
