package com.ana.discold.Beans;

public class MessageBean {
    private Long id;
    private String content;
    private UserBean user;
    private Long date;



    //constructor1
    public MessageBean(Long id, String content, UserBean user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }
//constructor 2
    public MessageBean(String content, UserBean user) {
        this.content = content;
        this.user = user;
    }

//constructor


//Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate() {
        return date;
    }
}
