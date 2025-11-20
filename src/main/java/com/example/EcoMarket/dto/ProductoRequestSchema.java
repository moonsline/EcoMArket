package com.example.EcoMarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public class ProductoRequestSchema {

    @Schema(
            description = "Objeto JSON con los datos del producto",
            example = "{ \"nombre\": \"Leche\", \"precio\": 1000, \"stock\": 10 }"
    )
    public String producto;

    @Schema(
            description = "Imagen del producto (opcional)",
            type = "string",
            format = "binary"
    )
    public MultipartFile img;
}
