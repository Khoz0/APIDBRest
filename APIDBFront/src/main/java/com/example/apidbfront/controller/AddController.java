package com.example.apidbfront.controller;

import com.example.apidbfront.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Roberge-Mentec Corentin
 */


public class AddController implements IController {

    private MainApplication main;

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField city;

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


    public void sendForm() {
        if (!name.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && !city.getText().equals("")){
            StringBuilder sb = new StringBuilder();
            sb.append("{"+
                    "\"id\":1,"+
                    "\"name\":\""+name.getText()+"\","+
                    "\"email\":\""+email.getText()+"\","+
                    "\"password\":\""+password.getText()+"\","+
                    "\"city\":\""+city.getText()+"\"}");
            try {
                URL url = new URL("http://localhost:8080/Users");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                String jsonInputString = sb.toString();

                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response);
                }
                System.out.println("Utilisateur ajouté");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
