package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.Dto.CompraRequestDto;
import com.uade.tpo.ecommerceback.entity.Compra;

public interface ICompraService {
    Compra GuardarCompra(CompraRequestDto compra);
}
