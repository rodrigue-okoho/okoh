package com.okoho.okoho.service.dto;

import java.io.Serializable;

public class RegisterRequestInit  implements Serializable{


    private String email;
    private String user_type; //1=candidate 2=recruteur 3=personnel

    private String password;
    public String getUser_type() {
        return this.user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
