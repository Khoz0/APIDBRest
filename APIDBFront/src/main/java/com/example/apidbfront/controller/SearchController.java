package com.example.apidbfront.controller;

import com.example.apidbfront.MainApplication;
import com.example.apidbfront.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberge-Mentec Corentin
 */



public class SearchController implements IController {

    private MainApplication main;
    @FXML
    private TextArea user;
    @FXML
    private TextArea users;
    @FXML
    private TextField idUser;

    public void setMain(MainApplication mainApplication) {
        main = mainApplication;
    }

    public void backScene() {
        try {
            main.switchScene("homeView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUsers() throws IOException {
        URL url = new URL("http://localhost:8080/Users/");
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
            sb.append("\n");
        }
        users.setText(sb.toString());
    }

    public void getUser() throws IOException {
        if (idUser.getText().equals("")){
            idUser.setText("21");
        }
        URL url = new URL("http://localhost:8080/User/"+idUser.getText());
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
            sb.append("\n");
        }
        user.setText(sb.toString());
        if (sb.toString().equals("")){
            System.out.println("Utilisateur inconnu");
        }
    }
}
