package com.kfh.educationsite.service;

import java.util.Arrays;

public enum IntegrationModes {
    SOAP("SOAP"), REST("REST"), KAFKA("KAFKA"), RABBIT("RABBIT");

    private final String value;

    IntegrationModes(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static IntegrationModes fromValue(String v) {
        for (IntegrationModes c : IntegrationModes.values()) {
            if (c.value.equalsIgnoreCase(v))
                return c;
        }
        throw new IllegalArgumentException(Arrays.toString(IntegrationModes.values()));
    }
}
