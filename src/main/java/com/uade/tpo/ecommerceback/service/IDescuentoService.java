package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.Dto.DescuentoRequestDto;
import com.uade.tpo.ecommerceback.entity.Descuento;

public interface IDescuentoService {
    Descuento createDescuento(DescuentoRequestDto descuento);
    Descuento findByDescripcion(String descrip);
}
