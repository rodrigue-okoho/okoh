package com.okoho.okoho.rest.errors;

import java.net.URI;

public class TypeAccountExeption extends RuntimeException{

    public TypeAccountExeption(Exception exception) {
        //super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Account type not exists!", "userManagement", "accountnotexists");
       // super("error");
       super(exception);
    }
    
}
