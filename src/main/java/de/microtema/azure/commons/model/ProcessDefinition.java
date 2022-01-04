package de.microtema.azure.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record ProcessDefinition(@JsonProperty("ID") int id, @JsonProperty("DISPLAY_NAME") String displayName) implements Serializable {

}
