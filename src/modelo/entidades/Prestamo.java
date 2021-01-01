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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "prestamos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
	@NamedQuery(name = "Prestamo.findByIdPrestamos", query = "SELECT p FROM Prestamo p WHERE p.idPrestamos = :idPrestamos"),
	@NamedQuery(name = "Prestamo.findByFechaSalida", query = "SELECT p FROM Prestamo p WHERE p.fechaSalida = :fechaSalida"),
	@NamedQuery(name = "Prestamo.findByFechaEntrega", query = "SELECT p FROM Prestamo p WHERE p.fechaEntrega = :fechaEntrega")})
public class Prestamo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_prestamos")
	private Integer idPrestamos;
	@Basic(optional = false)
        @Column(name = "fecha_salida")
        @Temporal(TemporalType.DATE)
	private Date fechaSalida;
	@Basic(optional = false)
        @Column(name = "fecha_entrega")
        @Temporal(TemporalType.DATE)
	private Date fechaEntrega;
	@JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
        @ManyToOne(optional = false)
	private Equipo idEquipo;
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
        @ManyToOne(optional = false)
	private Usuario idUsuario;

	public Prestamo() {
	}

	public Prestamo(Integer idPrestamos) {
		this.idPrestamos = idPrestamos;
	}

	public Prestamo(Integer idPrestamos, Date fechaSalida, Date fechaEntrega) {
		this.idPrestamos = idPrestamos;
		this.fechaSalida = fechaSalida;
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getIdPrestamos() {
		return idPrestamos;
	}

	public void setIdPrestamos(Integer idPrestamos) {
		this.idPrestamos = idPrestamos;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Equipo getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Equipo idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPrestamos != null ? idPrestamos.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Prestamo)) {
			return false;
		}
		Prestamo other = (Prestamo) object;
		if ((this.idPrestamos == null && other.idPrestamos != null) || (this.idPrestamos != null && !this.idPrestamos.equals(other.idPrestamos))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Prestamo[ idPrestamos=" + idPrestamos + " ]";
	}
	
}
