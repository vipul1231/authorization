package com.amrut.prabhu.product;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class User {

    public int id;

    public String name;

    public String password;

}
