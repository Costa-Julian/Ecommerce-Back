package com.uade.tpo.ecommerceback.Dto;

import com.uade.tpo.ecommerceback.Dto.ProductoCantidadRequestDto;

import java.util.List;

public class CompraRequestDto {
    private List<ProductoCantidadRequestDto> productoCantidadRequestDtos;
    private int idUser;

    public List<ProductoCantidadRequestDto> getProductoCantidadRequests() {
        return productoCantidadRequestDtos;
    }

    public void setProductoCantidadRequests(List<ProductoCantidadRequestDto> productoCantidadRequestDtos) {
        this.productoCantidadRequestDtos = productoCantidadRequestDtos;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getCantidadById(Long idProducto) {
        for (ProductoCantidadRequestDto productoCantidad : productoCantidadRequestDtos) {
            if (productoCantidad.getIdProducto().equals(idProducto)) {
                return productoCantidad.getCantidad();
            }
        }
        return 0;
    }
}
