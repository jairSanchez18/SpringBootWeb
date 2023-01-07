package com.jairsan.spring.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jairsan.spring.model.ProductoModel;
import com.jairsan.spring.services.ProductoServices;

@Controller
@RequestMapping("/producto/web")
public class ProductoWeb {
	@Autowired
	ProductoServices productoServices;
	
	private Long idglobal;
	
	@GetMapping("/all")
	public String VerProductos(Model model) {
		ArrayList<ProductoModel> productos = productoServices.ObtenerProductos();
		model.addAttribute("productos", productos);
		return "productos";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String EliminarProducto(@PathVariable(value = "id") Long id, Model model) {
		boolean ok = productoServices.EliminarProducto(id);
		
		String mensaje = "";
		if(ok) {
			mensaje = "Eliminado con exito";
		}else {
			mensaje = "El producto que desea eliminar no existe";
		}
		
		return "redirect:/producto/web/all";
	}
	
	@GetMapping("/add")
	public String AgregarProducto(Model model) {
		model.addAttribute("producto", new ProductoModel());
		return "agregar";
	}
	
	@PostMapping("/save")
	public String save(@Validated ProductoModel productoModel, Model model) {
		productoServices.AgregarProducto(productoModel);
		return "redirect:/producto/web/all";
	}
	
	@PostMapping("update/save")
	public String updatesave(@Validated ProductoModel productoModel, Model model) {
		productoServices.ActualizarProducto(idglobal ,productoModel);
		return "redirect:/producto/web/all";
	}
	
	@GetMapping(value = "/update/{id}")
	public String ActualizarProducto(@PathVariable(value = "id") Long id, Model model) {
		Optional<ProductoModel> producto =  productoServices.ObtenerProductoUnico(id);
		idglobal = id;
		model.addAttribute("producto", producto);
		return "agregar";
	}
}
