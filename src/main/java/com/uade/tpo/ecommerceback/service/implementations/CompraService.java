package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.Dto.ProductoCantidadRequestDto;
import com.uade.tpo.ecommerceback.Dto.CompraRequestDto;
import com.uade.tpo.ecommerceback.entity.Compra;
import com.uade.tpo.ecommerceback.entity.Descuento;
import com.uade.tpo.ecommerceback.entity.ItemCompra;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.repository.ICompraRepository;
import com.uade.tpo.ecommerceback.repository.IItemCompraRepository;
import com.uade.tpo.ecommerceback.repository.IProductoRepository;
import com.uade.tpo.ecommerceback.service.ICompraService;
import com.uade.tpo.ecommerceback.service.IDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class CompraService implements ICompraService {
    @Autowired
    private ICompraRepository compraRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IItemCompraRepository compraItemRepository;
    @Autowired
    private IDescuentoService iDescuentoService;
    @Override
    public Compra GuardarCompra(CompraRequestDto compraRequestDto) {
        Compra compraSave = new Compra();
        List<Producto> productoList = new ArrayList<>();
        double montoTotal = 0;
        for (ProductoCantidadRequestDto productoCantidadRequestDto : compraRequestDto.getProductoCantidadRequests()){
            Producto producto =  productoRepository.findById(productoCantidadRequestDto.getIdProducto()).get();
            producto.setStock(producto.getStock() - productoCantidadRequestDto.getCantidad());
            productoRepository.save(producto);
            montoTotal += producto.getPrecio();
            productoList.add(producto);
        }

        Descuento descuento = iDescuentoService.findByDescripcion(compraRequestDto.getDescuento());

        if(descuento != null){
            compraSave.setMonto(montoTotal - (montoTotal * descuento.getPorcentaje()/100));
            compraSave.setDescuentos(descuento);
        }else compraSave.setMonto(montoTotal);

        compraSave.setFecha(LocalDate.now());

        compraSave = compraRepository.save(compraSave);

        for(Producto producto: productoList){
            int cantidad = compraRequestDto.getCantidadById(producto.getId());
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProducto(producto);
            itemCompra.setCantidad(cantidad);
            itemCompra.setPrecioUnitario(producto.getPrecio() * cantidad);
            itemCompra.setCompra(compraSave);
            compraItemRepository.save(itemCompra);
        }

        return compraSave;
    }
}
