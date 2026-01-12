package com.example.Exa.emMocCPV.controler;

import com.example.Exa.emMocCPV.entity.Producto;
import com.example.Exa.emMocCPV.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // POST: Crear nuevo producto
    @PostMapping(value = "/productos")
    public Producto addProducto(@RequestBody Producto producto) {
        return productoService.addProducto(producto);
    }

    // DELETE: Eliminar producto
    @DeleteMapping(value = "/producto/{productoId}")
    public void deleteProducto(@PathVariable Long productoId) {
        productoService.eliminarProductoById(productoId);
    }

    // PUT: Modificar producto
    @PutMapping(value = "/producto/{productoId}")
    public Producto modificarProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return productoService.modificarProducto(productoId, producto);
    }

    // GET: Obtener productos con filtros opcionales (precio y/o categoría)
    @GetMapping(value = "/productos")
    public List<Producto> getProductos(@RequestParam(defaultValue = "0.0") Float precio,
                                       @RequestParam(defaultValue = "") String categoria) {
        /*
            - Si no se indica ni precio ni categoría -> obtener todos los productos.
            - Si se indica el precio -> obtener los productos con ese precio.
            - Si se indica la categoria -> obtener los productos con esa categoria.
         */
        if (precio == 0.0 && categoria.isEmpty()) {
            return productoService.findAllProductos();
        } else if (precio != 0.0 && !categoria.isEmpty()) {
            return productoService.findByPrecioAndCategoria(precio, categoria);
        } else if (precio != 0.0) {
            return productoService.findByPrecio(precio);
        }
        return productoService.findByCategoria(categoria);
    }

    // GET: Obtener producto por ID
    @GetMapping(value = "/producto/{productoId}")
    public Optional<Producto> getProducto(@PathVariable Long productoId) {
        return productoService.findProducto(productoId);
    }
}
