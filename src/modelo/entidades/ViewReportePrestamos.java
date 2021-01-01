/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "reporte_prestamos")
@Immutable
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewReportePrestamos.findAll", query = "SELECT v FROM ViewReportePrestamos v"),
	@NamedQuery(name = "ViewReportePrestamos.findByCedula", query = "SELECT v FROM ViewReportePrestamos v WHERE v.cedula = :cedula"),
	@NamedQuery(name = "ViewReportePrestamos.findByNombreUsuario", query = "SELECT v FROM ViewReportePrestamos v WHERE v.nombreUsuario = :nombreUsuario"),
	@NamedQuery(name = "ViewReportePrestamos.findByDescripcion", query = "SELECT v FROM ViewReportePrestamos v WHERE v.descripcion = :descripcion"),
	@NamedQuery(name = "ViewReportePrestamos.findByMarca", query = "SELECT v FROM ViewReportePrestamos v WHERE v.marca = :marca"),
	@NamedQuery(name = "ViewReportePrestamos.findByModelo", query = "SELECT v FROM ViewReportePrestamos v WHERE v.modelo = :modelo"),
	@NamedQuery(name = "ViewReportePrestamos.findByColor", query = "SELECT v FROM ViewReportePrestamos v WHERE v.color = :color"),
	@NamedQuery(name = "ViewReportePrestamos.findByCantidadEquipos", query = "SELECT v FROM ViewReportePrestamos v WHERE v.cantidadEquipos = :cantidadEquipos"),
	@NamedQuery(name = "ViewReportePrestamos.findByPropiedad", query = "SELECT v FROM ViewReportePrestamos v WHERE v.propiedad = :propiedad"),
	@NamedQuery(name = "ViewReportePrestamos.findByFechaSalida", query = "SELECT v FROM ViewReportePrestamos v WHERE v.fechaSalida = :fechaSalida"),
	@NamedQuery(name = "ViewReportePrestamos.findByFechaEntrega", query = "SELECT v FROM ViewReportePrestamos v WHERE v.fechaEntrega = :fechaEntrega")})
public class ViewReportePrestamos implements Serializable {

	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	
	@Basic(optional = false)
        @Column(name = "cedula")
	private String cedula;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	@Basic(optional = false)
	
        @Column(name = "descripcion")
	private String descripcion;
	@Basic(optional = false)
	
        @Column(name = "marca")
	private String marca;
	@Basic(optional = false)
	
        @Column(name = "modelo")
	private String modelo;
	@Basic(optional = false)
        @Column(name = "color")
	private String color;
	@Basic(optional = false)
        @Column(name = "cantidad_equipos")
	private int cantidadEquipos;
	@Basic(optional = false)
        @Column(name = "propiedad")
	private String propiedad;
	@Basic(optional = false)
        @Column(name = "fecha_salida")
        @Temporal(TemporalType.DATE)
	private Date fechaSalida;
	@Basic(optional = false)
        @Column(name = "fecha_entrega")
        @Temporal(TemporalType.DATE)
	private Date fechaEntrega;

	public ViewReportePrestamos() {
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getColor() {
		return color;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public String getPropiedad() {
		return propiedad;
	}


	public Date getFechaSalida() {
		return fechaSalida;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	
}
