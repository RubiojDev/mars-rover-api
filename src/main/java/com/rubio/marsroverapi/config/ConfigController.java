package com.rubio.marsroverapi.config;

import com.rubio.marsroverapi.config.docs.ConfigApiDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController implements ConfigApiDocs {

    private final MapProperties mapProperties;

    @Autowired
    public ConfigController(MapProperties mapProperties) {
        this.mapProperties = mapProperties;
    }

    @Override
    @GetMapping
    public ResponseEntity<MapProperties> getConfig() {
        return ResponseEntity.ok(mapProperties);
    }

}
