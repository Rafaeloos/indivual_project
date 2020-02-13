package com.parkingmanager.dto;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Parking implements Serializable {
	private int id;
	private String municipio;
	private int plazas;
	private String direccion;
	private LocalDateTime horario;
	private Double precio;
	
	public Parking(int id, String municipio2, Integer plazas2, String direccion2, LocalDateTime horario2, Double precio2) {	
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", municipio=" + municipio + ", plazas=" + plazas + ", direccion=" + direccion
				+ ", horario=" + horario + ", precio=" + precio + "]";
	}
	
}