package com.example.apidbfront.model;

/**
 * @author Roberge-Mentec Corentin
 */

public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String city;

    public User(Integer id, String name, String email, String password, String city){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    protected User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
