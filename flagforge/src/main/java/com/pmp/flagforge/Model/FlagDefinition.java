package com.pmp.flagforge.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlagDefinition {

    private String flagKey;

    private String name;

    private String description;

    private Boolean defaultValue;

    private String managedBy;
}
