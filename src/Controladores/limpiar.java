/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import app.PrestamosITCA;

/**
 *
 * @author mjavi
 */
public class limpiar extends PrestamosITCA { // EXTENDEMOS LA CLASE DE "PrestamosITCA" para tener acceso a
	// los objetos y propiedades

	// ESTABLECEMOS LOS "TEXTBOX" DE USUARIOS EN VACIO
	public void limipiarUsuarios() {
		txtIdUsuario.setText("");
		txtCedula.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtDireccion.setText("");
	}
	
	// ESTABLECEMOS LOS "TEXTBOX" DE EQUIPOS EN VACIO
	public void limipiarEquipos() {
		txtIdEquipo.setText("");
		txtDescripcion.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		txtColor.setText("");
		txtCantidad.setText("");
		txtDepartamento.setText("");
	}
	
	// ESTABLECEMOS LOS "TEXTBOX" DE PRESTAMOS EN VACIO
	public void limpiarPrestamos(){
		txtIdPrestamo.setText("");
		txtEquipo.setText("");
		txtUsuario.setText("");
		txtCantidadEquipos.setText("");
		txtFechaSalida.setText("");
		txtFechaEntrega.setText("");
	}

}
