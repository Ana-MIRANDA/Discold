package com.ana.discold.Beans;

public class UserBean {
        private Long id;
        private String pseudo;
        private String password;


//constructor 1
    public UserBean(Long id, String pseudo, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
    }


 //constructor 2
    public UserBean(String pseudo, String password ) {
        this.pseudo = pseudo;
        this.password = password;
    }

// constructor 3
public UserBean(Long id,String pseudo) {
    this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
