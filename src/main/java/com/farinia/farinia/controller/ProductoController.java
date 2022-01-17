package com.farinia.farinia.controller;

import com.farinia.farinia.handle.ProductoException;
import com.farinia.farinia.model.Producto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/farinia")
public class ProductoController {

    Logger logger = LogManager.getLogger(ProductoController.class);

    @GetMapping("/productos/ej")
    public String getEjemplo() {
        logger.info("Inicio-GETEjemplo");
        //logger.warn("Hola");
        //logger.error("hola");
        return "Mensaje GET";
    }

    @GetMapping("/productos/")
    public List<Producto> getProductosAll() throws ProductoException {
        if(lista.size() > 0){
            logger.info("Inicio-GetProductosAll");
            return lista;
        }
        else throw new ProductoException("La lista esta vacia");
    }

    @GetMapping("/productos/{id}")
    public String getProductosPorId(@PathVariable Long id) throws ProductoException {
        logger.info("Inicio-GetProductosPorId");
        if(lista.size() > 0) {
            if(id <= 0) {
                throw new ProductoException("El identificador del mensaje debe ser mayor a 0");
            }
            var prodFiltered = lista.stream()
                    .filter(producto -> producto.getId() == id).findFirst();
            if(prodFiltered.isPresent()){
                return prodFiltered.get().toString();
            }
            else {
                throw new ProductoException("No existe producto con id "+id);
            }
        }
        else throw new ProductoException("La lista esta vacia");
    }

    @PostMapping("/productos/")
    public String crearProducto(@RequestBody Producto productoNuevo) throws ProductoException {
        logger.info("Inicio-PostCrearProducto");
        if(productoNuevo.getTitle() != null && productoNuevo.getPrice() > 0){
            var prodFiltered = lista.stream()
                    .filter(prod -> prod.getId() == productoNuevo.getId()).findFirst();
            if(prodFiltered.isPresent()){
                throw new ProductoException("Ya existe un producto con dicho ID");
            }
            else {
                lista.add(productoNuevo);
                return productoNuevo.toString();
            }
        }
        else throw new ProductoException("Los campos son invalidos");
    }

    private List<Producto> lista = dataProductos();

    private List<Producto> dataProductos() {
        List<Producto> listaInicial = new ArrayList<Producto>();
        listaInicial.add(new Producto("harina",50));
        listaInicial.add(new Producto("arroz",60));
        return listaInicial;
    }
}
