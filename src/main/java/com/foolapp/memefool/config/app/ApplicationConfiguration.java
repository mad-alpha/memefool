package com.foolapp.memefool.config.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.foolapp.memefool.config.AppConfig;
import lombok.Getter;

public class ApplicationConfiguration {
    @Getter
    private final AppConfig appConfig;

    @JsonCreator
    public ApplicationConfiguration(@JsonProperty("appConfig") AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
