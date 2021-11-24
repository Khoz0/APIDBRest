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


public class UpdateController implements IController{

    private MainApplication main;
    @FXML
    private TextField idUserSearch;
    @FXML
    private TextField id;
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
        if (!id.getText().equals("") && !name.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && !city.getText().equals("")){
            StringBuilder sb = new StringBuilder();
            sb.append("{"+
                    "\"id\":"+id.getText()+","+
                    "\"name\":\""+name.getText()+"\","+
                    "\"email\":\""+email.getText()+"\","+
                    "\"password\":\""+password.getText()+"\","+
                    "\"city\":\""+city.getText()+"\"}");
            try {
                URL url = new URL("http://localhost:8080/Users");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
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

                System.out.println("Utilisateur "+idUserSearch.getText()+" modifié");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getUser() throws IOException {
        URL url = new URL("http://localhost:8080/User/"+idUserSearch.getText());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
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
        }
        id.setText(idUserSearch.getText());
        name.setDisable(false);
        email.setDisable(false);
        password.setDisable(false);
        city.setDisable(false);

        System.out.println(sb);

        String parsing = sb.toString();
        parsing = parsing.replace("{", "");
        parsing = parsing.replace("}", "");

        String[] words = parsing.split(",");
        for (String word: words){
            word = word.replace("\"", "");
            String[] attributes = word.split(":");
            switch (attributes[0]) {
                case "name":
                    name.setText(attributes[1]);
                    break;
                case "email":
                    email.setText(attributes[1]);
                    break;
                case "password":
                    password.setText(attributes[1]);
                    break;
                case "city":
                    city.setText(attributes[1]);
                    break;
            }
        }
    }
}
