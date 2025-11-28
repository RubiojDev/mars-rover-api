package com.rubio.marsroverapi.rover.docs;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.dto.request.CommandRequestDto;
import com.rubio.marsroverapi.rover.dto.response.CommandResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name="Rover", description = "Endpoint para gestionar al rover")
public interface RoverApiDocs {

    @Operation(
            summary = "Obtiene al rover",
            description = "Devuelve los datos de rover",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rover encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoverDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rover no encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"message\":\"Rover not found\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<RoverDto> findRover();

    @Operation(
            summary = "Ingreso de lista de comandos",
            description = "Recibe la lista con los comandos que el rover debe ejecutar",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de Comandos a ser procesados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandRequestDto.class),
                            examples = @ExampleObject(
                                    value = "{\"commandList\":[\"M\",\"L\",\"R\"]}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comandos recibidos con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommandResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Comando Invalido",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"message\":\"Invalid command: C\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<CommandResponseDto> setCommand(@RequestBody CommandRequestDto commandRequest);
}
