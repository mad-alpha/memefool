package com.foolapp.memefool.config.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookupFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ConfigurationLoader {
    private final StringSubstitutor stringSubstitutor;

    public ConfigurationLoader() {
        this.stringSubstitutor = new StringSubstitutor(StringLookupFactory.INSTANCE.environmentVariableStringLookup());
    }

    public <T> T loadConfiguration(File config, Class<T> cls) {
        try {
            String contents = this.stringSubstitutor.replace(new String(Files.readAllBytes(config.toPath()), StandardCharsets.UTF_8));
            return new ObjectMapper(new YAMLFactory()).readValue(contents, cls);
        } catch (IOException var4) {
            throw new UncheckedIOException(var4);
        }
    }
}
