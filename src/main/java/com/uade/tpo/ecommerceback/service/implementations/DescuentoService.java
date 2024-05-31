package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.Dto.DescuentoRequestDto;
import com.uade.tpo.ecommerceback.entity.Descuento;
import com.uade.tpo.ecommerceback.repository.IDescuentoRepository;
import com.uade.tpo.ecommerceback.service.IDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescuentoService implements IDescuentoService {
    @Autowired
    private IDescuentoRepository descuentoRepository;

    public Descuento createDescuento(DescuentoRequestDto descuento) {
        return descuentoRepository.save(new Descuento(descuento.getDescripcion(),descuento.getPorcentaje(),true));
    }
}
