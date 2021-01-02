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
public class limpiar  { // EXTENDEMOS LA CLASE DE "PrestamosITCA" para tener acceso a
	// los objetos y propiedades

	//INSTANCIAMOS NUESTRA CLASE DE PRESTAMOS ITCA
	PrestamosITCA parent;
	
	public limpiar(PrestamosITCA obj){
		parent = obj;
	}
	
	// ESTABLECEMOS LOS "TEXTBOX" DE USUARIOS EN VACIO
	public void limipiarUsuarios() {
		parent.txtIdUsuario.setText("");
		parent.txtCedula.setText("");
		parent.txtNombres.setText("");
		parent.txtApellidos.setText("");
		parent.txtTelefono.setText("");
		parent.txtEmail.setText("");
		parent.txtDireccion.setText("");
	}
	
	// ESTABLECEMOS LOS "TEXTBOX" DE EQUIPOS EN VACIO
	public void limipiarEquipos() {
		parent.txtIdEquipo.setText("");
		parent.txtDescripcion.setText("");
		parent.txtMarca.setText("");
		parent.txtModelo.setText("");
		parent.txtColor.setText("");
		parent.txtCantidad.setText("");
		parent.txtDepartamento.setText("");
	}
	
	// ESTABLECEMOS LOS "TEXTBOX" DE PRESTAMOS EN VACIO
	public void limpiarPrestamos(){
		parent.txtIdPrestamo.setText("");
		parent.txtEquipo.setText("");
		parent.txtUsuario.setText("");
		parent.txtCantidadEquipos.setText("");
		parent.txtFechaSalida.setText("");
		parent.txtFechaEntrega.setText("");
	}

}
