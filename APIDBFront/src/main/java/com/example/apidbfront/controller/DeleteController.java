package com.example.apidbfront.controller;

import com.example.apidbfront.MainApplication;
import com.example.apidbfront.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Roberge-Mentec Corentin
 */


public class DeleteController implements IController{

    private MainApplication main;
    @FXML
    private TextField idDelete;

    @Override
    public void setMain(MainApplication main) {
        this.main = main;
    }

    public void backScene() {
        try {
            main.switchScene("homeView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws IOException {
        URL url = new URL("http://localhost:8080/Users/" + idDelete.getText());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("accept", "application/json");

        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
            sb.append("\n");
        }

        String jsonInputString = sb.toString();

        conn.setDoOutput(true);

        /*try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }*/

        try (BufferedReader br2 = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
        br.close();
        System.out.println("Utilisateur "+idDelete.getText()+" supprimé");
    }
}
