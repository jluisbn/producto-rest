package com.gapsi.product.dao;

public class Productos {

	private int id;
	private int status;
	private String nombre;
	private String descripcion;
	private String modelo;
	private String precio;
	
	public Productos(int id, int status, String nombre, String descripcion, String modelo, String precio) {
		super();
		this.id = id;
		this.status = status;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.modelo = modelo;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Productos [id=" + id + ", status=" + status + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", modelo=" + modelo + ", precio=" + precio + "]";
	}
	
}
