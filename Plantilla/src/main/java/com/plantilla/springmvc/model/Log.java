package com.plantilla.springmvc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Logs database table.
 * 
 */
@Entity
@Table(name="Logs")
@NamedQuery(name="Log.findAll", query="SELECT l FROM Log l")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LogId")
	private int logId;

	@Column(name="Clase")
	private Object clase;

	@Column(name="Ensamblado")
	private Object ensamblado;

	@Column(name="Fecha")
	private Timestamp fecha;

	@Column(name="Mensaje")
	private Object mensaje;

	@Column(name="Metodo")
	private Object metodo;

	@Column(name="NivelLog")
	private int nivelLog;

	public Log() {
	}

	public int getLogId() {
		return this.logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public Object getClase() {
		return this.clase;
	}

	public void setClase(Object clase) {
		this.clase = clase;
	}

	public Object getEnsamblado() {
		return this.ensamblado;
	}

	public void setEnsamblado(Object ensamblado) {
		this.ensamblado = ensamblado;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Object getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(Object mensaje) {
		this.mensaje = mensaje;
	}

	public Object getMetodo() {
		return this.metodo;
	}

	public void setMetodo(Object metodo) {
		this.metodo = metodo;
	}

	public int getNivelLog() {
		return this.nivelLog;
	}

	public void setNivelLog(int nivelLog) {
		this.nivelLog = nivelLog;
	}

}