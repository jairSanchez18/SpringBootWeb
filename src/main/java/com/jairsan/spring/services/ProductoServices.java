package com.jairsan.spring.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jairsan.spring.model.ProductoModel;
import com.jairsan.spring.repository.ProductoRepository;

@Service
public class ProductoServices {
	@Autowired
	ProductoRepository productoRepository;

	// CREATE
	public String AgregarProducto(@RequestBody ProductoModel producto) {
		try {
			productoRepository.save(producto);
			return "El producto fue agregado con exito";
		} catch (Exception e) {
			return "Error al agregar el producto";
		}
	}

	// READ
	public ArrayList<ProductoModel> ObtenerProductos() {
		return (ArrayList<ProductoModel>) productoRepository.findAll();
	}

	// READ BY ID
	public Optional<ProductoModel> ObtenerProductoUnico(Long id) {
		return (Optional<ProductoModel>) productoRepository.findById(id);
	}
	
	//UPDATE
	public Optional<ProductoModel> ActualizarProducto(Long id, @RequestBody ProductoModel producto){
		Optional<ProductoModel> optionalProducto = productoRepository.findById(id);
		
		ProductoModel productoModel = optionalProducto.get();
		productoModel.setProducto(producto.getProducto());
		productoModel.setDescripcion(producto.getDescripcion());
		productoModel.setCantidad(producto.getCantidad());
		productoModel.setPrecio(producto.getPrecio());
		
		return Optional.of(productoRepository.save(productoModel));
	}

	// DELETE
	public boolean EliminarProducto(Long id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
