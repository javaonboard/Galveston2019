package com.galveston.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Request {

    private String family;
    private String request;
    private boolean isApproved;

    public Request(String family,String prize){

        this.family = family;
        this.request = prize;
        this.isApproved = false;
    }
}
