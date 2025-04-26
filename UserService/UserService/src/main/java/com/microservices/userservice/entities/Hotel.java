package com.microservices.userservice.entities;

import lombok.Data;

@Data//common for for noargs,allargs,getter,setter
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
