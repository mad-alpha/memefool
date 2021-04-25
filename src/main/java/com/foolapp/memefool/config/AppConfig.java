package com.foolapp.memefool.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AppConfig {
    private final String dbDriver;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPwd;

    @JsonCreator
    public AppConfig(@JsonProperty("dbDriver") final String dbDriver, @JsonProperty("dbUrl") String dbUrl,
                     @JsonProperty("dbUser") String dbUser, @JsonProperty("dbPwd") String dbPwd) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
    }
}
