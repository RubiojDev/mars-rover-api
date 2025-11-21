package com.rubio.marsroverapi.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "app.map")
public class MapProperties {

    private int width;
    private int height;

    public MapProperties(){}

    public void setWidth(int width) {
        if (width <= 1) {
            this.width = 2;
        } else {
            this.width = width;
        }
    }

    public void setHeight(int height) {
        if (height <= 1) {
            this.height = 2;
        } else {
            this.height = height;
        }
    }
}
