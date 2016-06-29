package main.plantilla.springmvc.model;

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
	private String clase;

	@Column(name="Ensamblado")
	private String ensamblado;

	@Column(name="Fecha")
	private Timestamp fecha;

	@Column(name="Mensaje")
	private String mensaje;

	@Column(name="Metodo")
	private String metodo;

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

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public Object getEnsamblado() {
		return this.ensamblado;
	}

	public void setEnsamblado(String ensamblado) {
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

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public int getNivelLog() {
		return this.nivelLog;
	}

	public void setNivelLog(int nivelLog) {
		this.nivelLog = nivelLog;
	}

}