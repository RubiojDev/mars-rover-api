package com.rubio.marsroverapi.config.docs;

import com.rubio.marsroverapi.config.MapProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name="Config", description = "Endpoint para gestionar la configuración inicial del mapa")
public interface ConfigApiDocs {

    @Operation(
            summary = "Devuelve los datos del mapa",
            description = "Devuelve el alto y ancho del mapa",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta exitosa",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MapProperties.class),
                                    examples = @ExampleObject(
                                            value = "{ \"width\": 10, \"height\": 10 }"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<MapProperties> getConfig();

}
