package com.microservices.userservice.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //to map to database JPA to manage instances of this class CRUD operation from these class
@Table(name="micro_users")
public class User {
    @Id //primary key
    @Column(name="ID")
    private String userId;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;

    @Transient
    @Builder.Default
    private List<Rating> ratings=new ArrayList<>();



//@JsonIgnore
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//to hide pass jwt
@Column(name = "PASSWORD")
private String password;


}
