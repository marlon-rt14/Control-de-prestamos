/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "equipos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
	@NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo"),
	@NamedQuery(name = "Equipo.findByDescripcion", query = "SELECT e FROM Equipo e WHERE e.descripcion = :descripcion"),
	@NamedQuery(name = "Equipo.findByMarca", query = "SELECT e FROM Equipo e WHERE e.marca = :marca"),
	@NamedQuery(name = "Equipo.findByModelo", query = "SELECT e FROM Equipo e WHERE e.modelo = :modelo"),
	@NamedQuery(name = "Equipo.findByColor", query = "SELECT e FROM Equipo e WHERE e.color = :color"),
	@NamedQuery(name = "Equipo.findByCantidad", query = "SELECT e FROM Equipo e WHERE e.cantidad = :cantidad"),
	@NamedQuery(name = "Equipo.findByPropiedad", query = "SELECT e FROM Equipo e WHERE e.propiedad = :propiedad")})
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_equipo")
	private Integer idEquipo;
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
        @Column(name = "cantidad")
	private int cantidad;
	@Basic(optional = false)
        @Column(name = "propiedad")
	private String propiedad;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
	private List<Prestamo> prestamoList;

	public Equipo() {
	}

	public Equipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Equipo(Integer idEquipo, String descripcion, String marca, String modelo, String color, int cantidad, String propiedad) {
		this.idEquipo = idEquipo;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.cantidad = cantidad;
		this.propiedad = propiedad;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}

	@XmlTransient
	public List<Prestamo> getPrestamoList() {
		return prestamoList;
	}

	public void setPrestamoList(List<Prestamo> prestamoList) {
		this.prestamoList = prestamoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEquipo != null ? idEquipo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Equipo)) {
			return false;
		}
		Equipo other = (Equipo) object;
		if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Equipo[ idEquipo=" + idEquipo + " ]";
	}
	
}
