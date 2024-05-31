package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.DescuentoRequestDto;
import com.uade.tpo.ecommerceback.entity.Descuento;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.service.IDescuentoService;
import com.uade.tpo.ecommerceback.service.implementations.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descuento")
public class DescuentoController {
    @Autowired
    private IDescuentoService descuentoService;

    @PostMapping("/create")
    public ResponseEntity<Descuento> createDescuento(@RequestBody DescuentoRequestDto descuento) {
        Descuento nuevoDescuento =   descuentoService.createDescuento(descuento);
        return new ResponseEntity<>(nuevoDescuento, HttpStatus.CREATED);
    }
}
