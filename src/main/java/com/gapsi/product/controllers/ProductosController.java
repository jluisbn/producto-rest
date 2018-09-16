package com.gapsi.product.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gapsi.product.dao.Productos;
import com.gapsi.product.exceptions.InternalDataValidation;
import com.gapsi.product.exceptions.InternalErrorException;
import com.gapsi.product.exceptions.ResourceNotFoundException;
import com.gapsi.product.model.ProductModel;

@RestController
public class ProductosController {

	private static Logger logger = Logger.getAnonymousLogger();
	private final int validateLengthModelo = 10;
	private final int validateLengthDesc = 200;
	
	/**
	 * Agrega nuevo producto, todos los parámetros son necesarios.
	 * Regresa una cadena de que se agregó correctamente, junto con su ID generado en la base de datos
	 * 
	 * Errores:
	 * 500 si hubo un error en la base de datos
	 * */
	@RequestMapping(value = "/producto/add", method = RequestMethod.POST)
	public String addProducto(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String modelo, @RequestParam String precio) {
		
		logger.log(Level.INFO, "Executing POST method addProducto");
		int product = -1;
		ProductModel producto = new ProductModel();
		
		try {
			Double.parseDouble(precio);
		} catch (IllegalArgumentException e) {
			throw new InternalDataValidation("El precio debe estar en formato decimal");
		}
		
		try {
			product = producto.addProduct(nombre, descripcion, precio, modelo, "0");
			if (product == -1)
				throw new InternalErrorException();
		} catch(SQLException e) {
			logger.log(Level.WARNING, "Error executing POST method addProducto");
			throw new InternalErrorException();
		}
		return "Product added correctly. ID: " + product;
	}
	
	/**
	 * Consulta productos, recibe como parámetro un ID, si este viene con valor 0 (cero), consulta todos los registros,
	 * si viene con ID mayor a 0 (cero), busca por ese valor.
	 * Retorna un JSON
	 * */
	@RequestMapping(value = "/producto/get", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ArrayList<Productos>> getProducts(@RequestParam Integer id) {
		logger.log(Level.INFO, "Executing GET method getProducts");
		
		try {
			Integer.parseInt(String.valueOf(id));
		} catch (IllegalArgumentException e) {
			throw new InternalDataValidation("El ID debe estar en formato numérico");
		}
		
		ProductModel producto = new ProductModel();
		ArrayList<Productos> productos = producto.getProductos(id);
		return Collections.singletonList(productos);
	}
	
	/**
	 * Actualiza un registro en la tabla producto, si el ID proporcionado no lo encuentra, retorna un 404.
	 * Valida campos modelo y descripción que deban tener 10 y 200 caracteres de longitud como máximo respectivamente
	 * Errores: 500
	 * */
	@RequestMapping(value = "/producto/update", method = RequestMethod.PATCH)
	public String updateProduct(@RequestParam Integer id, @RequestParam String descripcion, @RequestParam String modelo) {

		logger.log(Level.INFO, "Executing PATCH method updateProduct");
		
		try {
			Integer.parseInt(String.valueOf(id));
		} catch (IllegalArgumentException e) {
			throw new InternalDataValidation("El ID debe estar en formato numérico");
		}
		
		if (modelo.length() > this.validateLengthModelo)
			throw new InternalDataValidation("Modelo no debe ser más de 10 caracteres de logitud");

		if (descripcion.length() > this.validateLengthDesc)
			throw new InternalDataValidation("Descripción no debe ser más de 200 caracteres de logitud");
		
		int product = -1;
		ProductModel producto = new ProductModel();
		
		try {
			product = producto.updateProduct(id, descripcion, modelo);
			
			if (product == 0)
				throw new ResourceNotFoundException();
		} catch(SQLException e) {
			logger.log(Level.WARNING, "Error executing PATCH method updateProduct");
			throw new InternalErrorException();
		}
		
		return "Product updated correctly";
	}
}
