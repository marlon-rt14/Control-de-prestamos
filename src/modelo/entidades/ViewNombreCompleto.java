/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "nombre_completo")
@Immutable
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewNombreCompleto.findAll", query = "SELECT v FROM ViewNombreCompleto v"),
	@NamedQuery(name = "ViewNombreCompleto.findByIdUsuario", query = "SELECT v FROM ViewNombreCompleto v WHERE v.idUsuario = :idUsuario"),
	@NamedQuery(name = "ViewNombreCompleto.findByNombreUsuario", query = "SELECT v FROM ViewNombreCompleto v WHERE v.nombreUsuario = :nombreUsuario")})
public class ViewNombreCompleto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	public ViewNombreCompleto() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

}
