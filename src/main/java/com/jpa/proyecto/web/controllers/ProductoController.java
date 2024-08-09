package com.jpa.proyecto.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.proyecto.domain.services.producto.ProductoService;
import com.jpa.proyecto.persistence.entities.Producto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService servicio;

    ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> ver(@PathVariable Long id) {
        Optional<Producto> productoOpt = servicio.findById(id);
        if (productoOpt.isPresent()) {
            return ResponseEntity.ok(productoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Producto producto, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Producto producto, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Producto> productoOpt = servicio.update(id, producto);
        if (productoOpt.isPresent()) {
            return ResponseEntity.ok(productoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminar(@PathVariable Long id) {
        Optional<Producto> productoOpt = servicio.delete(id);
        if (productoOpt.isPresent()) {
            return ResponseEntity.ok(productoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validar(BindingResult resultado) {
        Map<String, String> errores = new HashMap<>();
        resultado.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/gama")
    public ResponseEntity<List<Producto>> productoGama(@RequestParam Long gamaProducto){
        List<Producto> listGP = servicio.findProductsByGama(gamaProducto);
        if (listGP.isEmpty()) {
            return ResponseEntity.ok(listGP);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stockProducto")
    public ResponseEntity<List<Producto>> stockPr(@RequestParam int stockMinimo){
        List<Producto> listStockPr = servicio.findProductsWithLowStock(stockMinimo);
        if(listStockPr.isEmpty()){
            return ResponseEntity.ok(listStockPr);
        }
        return ResponseEntity.notFound().build();
    }
}