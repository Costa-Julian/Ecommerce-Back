package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.CompraRequestDto;
import com.uade.tpo.ecommerceback.entity.Compra;
import com.uade.tpo.ecommerceback.service.implementations.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private CompraService compraService;

    @PostMapping("/crear")
    public ResponseEntity<Compra> createCompra(@RequestBody CompraRequestDto compraRequestDto){
        Compra compra  = compraService.GuardarCompra(compraRequestDto);
        return ResponseEntity.created(URI.create("/ShoppingCart/"+compra.getId())).body(compra);
    }

}
