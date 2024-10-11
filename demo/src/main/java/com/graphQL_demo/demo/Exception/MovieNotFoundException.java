package com.graphQL_demo.demo.Exception;

import graphql.ErrorClassification;

public class MovieNotFoundException extends Exception implements ErrorClassification {

    public MovieNotFoundException (String message){
        super(message);
    }

    public MovieNotFoundException (String message, Throwable cause){
        super(message, cause);
    }

    public MovieNotFoundException (){
        super();
    }

}
