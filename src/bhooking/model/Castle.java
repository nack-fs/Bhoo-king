package bhooking.model;

import bhooking.auxiliar.Registro;

public class Castle {
	private Registro codigo;
	private String denominacion;
	private String descripcion;
	private String pais;
	private float precio;
	private Encantamiento encantamiento;
	private String direccion;
	private String puntuation;
	
	public Castle(Registro codigo,String denominacion,String descripcion
			, String pais,float precio,Encantamiento encantamiento) {
		setCodigo(codigo);
		setDenominacion(denominacion);
		setDescripcion(descripcion);
		setEncantamiento(encantamiento);
		setPais(pais);
		setPrecio(precio);
		setEncantamiento(encantamiento);
	}
	
	
	public Registro getCodigo() {
		return codigo;
	}
	private void setCodigo(Registro codigo) {
		this.codigo = codigo;
	}
	
	public  String getDenominacion() {
		return denominacion;
	}
	
	private void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	public String getPais() {
		return pais;
	}
	private void setPais(String pais) {
		this.pais = pais;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	private void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Encantamiento getEncantamiento() {
		return encantamiento;
	}
	
	private void setEncantamiento(Encantamiento encantamiento) {
		this.encantamiento = encantamiento;
	}


	public String getDireccion() {
		return direccion;
	}


	public String getPuntuation() {
		return puntuation;
	}
	
	
	
	

}
