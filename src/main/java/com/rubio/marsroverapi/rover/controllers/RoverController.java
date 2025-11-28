package com.rubio.marsroverapi.rover.controllers;

import com.rubio.marsroverapi.rover.docs.RoverApiDocs;
import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.dto.request.CommandRequestDto;
import com.rubio.marsroverapi.rover.dto.response.CommandResponseDto;
import com.rubio.marsroverapi.rover.services.RoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rover")
public class RoverController implements RoverApiDocs {
    private final RoverService service;

    @Autowired
    public RoverController(RoverService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<RoverDto> findRover() {
        return ResponseEntity.ok(service.findRover());
    }

    @Override
    @PostMapping("/command")
    public ResponseEntity<CommandResponseDto> setCommand(@RequestBody CommandRequestDto commandRequest) {
        return ResponseEntity.ok(service.setCommand(commandRequest));
    }

}
