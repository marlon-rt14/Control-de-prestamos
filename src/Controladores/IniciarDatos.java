/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.swing.table.DefaultTableModel;
import modelo.FacadeEquipos;
import modelo.FacadePrestamos;
import modelo.FacadeUsuarios;
import modelo.FacadeViewReporte;
import modelo.entidades.Equipo;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;
import modelo.entidades.ViewReportePrestamos;

/**
 *
 * @author mjavi
 */
public class IniciarDatos {

	public void IniciarDatosReporte(DefaultTableModel dtm) {
		FacadeViewReporte fac = new FacadeViewReporte(); //HACER REFEREENCIA A NUESTRA CLASE FACADE REPORTE
		dtm.setRowCount(0); // ESTABLECER LAS FILAS DE LA TABLA REPORTES EN CERO
		for (ViewReportePrestamos item : fac.getListReporte()) { // RECORRER LA LISTA DE REPORTES E IR LLENANDO EN LA TABLA

			//CREAR UNA FILA CON TODOS LOS DATOS DE NUESTRO REPORTE
			Object[] fila = new Object[]{item.getCedula(), item.getNombreUsuario(), item.getDescripcion(),
				item.getMarca(), item.getModelo(), item.getColor(), item.getCantidad(), item.getPropiedad(),
				item.getFechaSalida(), item.getFechaEntrega()};

			//AGREGAR LA FILA A LA TABLA REPORTES
			dtm.addRow(fila);
		}
	}

	public void IniciarDatosUsuarios(DefaultTableModel dtm) {
		//HACER REFERENCIA A NUESTRA CLASE FACADE USUARIOS
		FacadeUsuarios fac = new FacadeUsuarios();
		//ESTABLECER LA FILAS DE LA TABLA USUSARIOS EN CERO
		dtm.setRowCount(0);
		//RECORRER LA LISTA DE REPORTES E IR LLENANDO EN LA TABLA REPORTES
		for (Usuario item : fac.getListUsuario()) {
			//CREAR UNA FILA CON TODOS LOS DATOS DE NUESTRO REPORTE
			Object[] fila = new Object[]{item.getIdUsuario(), item.getCedula(), item.getNombres(), item.getApellidos(),
				item.getTelefono(), item.getEmail(), item.getDireccion()};
			//AGREGAR LA FILA A LA TABLA REPORTES
			dtm.addRow(fila);
		}
	}

	public void IniciarDatosEqipos(DefaultTableModel dtm) {
		//HACER REFERENCIA A LA CLASE FACADE EQUIPOS
		FacadeEquipos fac = new FacadeEquipos();
		//ESTABLECER LA FILAS DE LA TABLA EQUIPOS EN CERO
		dtm.setRowCount(0);
		//RECORRER LA LISTA DE EQUIPOS E IR LLENANDO EN LA TABLA
		for (Equipo item : fac.getListEquipo()) {
			//CREAR UNA FILA CON TODOS LOS DATOS DE NUESTRO EQUIPO
			Object[] fila = new Object[]{item.getIdEquipo(), item.getDescripcion(), item.getMarca(), item.getModelo(),
				item.getColor(), item.getCantidad(), item.getPropiedad()};
			//AGREGAR LA FILA A LA TABLA EQUIPOS
			dtm.addRow(fila);
		}
	}

	public void IniciarDatosPrestamos(DefaultTableModel dtm) {
		//HACER REFERENCIA A NUESTRA CLASE FACADE PRESTAMOS
		FacadePrestamos fac = new FacadePrestamos();
		//ESTABLECER LAS FILAS DE LA TABLA PRESTAMOS EN CERO
		dtm.setRowCount(0);
		//RECORRER LA LISTA DE PRESTAMOS E IR LLENANDO EN LA TABLA PRESTAMOS
		for (Prestamo item : fac.getListPrestamo()) {
			//CREAR UNA FILA CON TODOS LOS DATOS DE LOS PRESTAMOS
			Object[] fila = new Object[]{item.getIdPrestamos(), item.getIdEquipo().getDescripcion(), item.getIdUsuario().getCedula(),
				item.getFechaSalida(), item.getFechaEntrega()};
			//AGREGAR LA FILA A LA TABLA PRESTAMOS
			dtm.addRow(fila);
		}
	}

}
