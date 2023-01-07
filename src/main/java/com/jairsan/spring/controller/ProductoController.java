package com.jairsan.spring.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jairsan.spring.model.ProductoModel;
import com.jairsan.spring.services.ProductoServices;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoServices productoServices;
	
	@PostMapping("/add")
	public String AgregarProducto(@RequestBody ProductoModel producto) {
		return productoServices.AgregarProducto(producto);
	}
	
	@GetMapping("/all")
	public ArrayList<ProductoModel> ObtenerProductos(){
		return productoServices.ObtenerProductos();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<ProductoModel> ObtenerProductoUnico(@PathVariable(value = "id") Long id){
		return productoServices.ObtenerProductoUnico(id);
	}
	
	@PutMapping(value = "/{id}")
	public Optional<ProductoModel> ActualizarProducto(@PathVariable(value = "id") Long id, @RequestBody ProductoModel producto){
		return productoServices.ActualizarProducto(id, producto);
	}
	
	@DeleteMapping(value = "/{id}")
	public String EliminarProducto(@PathVariable(value = "id") Long id) {
		boolean ok = productoServices.EliminarProducto(id);
		
		if(ok) {
			return "Producto eliminado correctamente";
		}else {
			return "El producto que desea eliminar no existe";
		}
	}
}
