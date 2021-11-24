package com.example.apidbfront.controller;

import com.example.apidbfront.MainApplication;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController implements IController {

    private MainApplication main;

    public void setMain(MainApplication mainApplication) {
        main = mainApplication;
    }

    @FXML
    public void searchUser(){
        try {
            main.switchScene("searchView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser() {
        try {
            main.switchScene("addView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        try {
            main.switchScene("deleteView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        try {
            main.switchScene("updateView.fxml", 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}