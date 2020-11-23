package com.ana.discold.Beans;

public class UserBean {
        private Long id;
        private String pseudo;

//constructor 1
    public UserBean(Long id, String pseudo) {
        this.id = id;
        this.pseudo = pseudo;
    }


 //constructor 2
    public UserBean(String pseudo) {
        this.pseudo = pseudo;
    }


//Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
