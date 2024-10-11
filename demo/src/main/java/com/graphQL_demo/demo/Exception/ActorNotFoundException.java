package com.graphQL_demo.demo.Exception;

import graphql.ErrorClassification;

public class ActorNotFoundException extends Exception implements ErrorClassification {

    public ActorNotFoundException (String message){
        super(message);
    }

    public ActorNotFoundException (String message, Throwable cause){
        super(message, cause);
    }

    public ActorNotFoundException (){
        super();
    }
}
