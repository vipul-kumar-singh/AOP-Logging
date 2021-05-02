package com.vkstech.AOPLogging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DemoContext {

    private String id;
    private String name;

    public DemoContext(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
