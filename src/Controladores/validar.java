package Controladores;

import app.PrestamosITCA;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class validar extends PrestamosITCA {

	boolean nuevo;

	public void validar() { // CADA VEZ QUE SE HAGA UNA REFERENCIA A LA CLASE INICIAR EN FALSE
		nuevo = false;
	}

	public static void validarNumeros(java.awt.event.KeyEvent evt) {
		// SI CADA TECLA PRESIONADA ES DIFERENTE DE UN NUMERO EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
		if (!Character.isDigit(evt.getKeyChar())) {
			evt.consume();
		}
	}

	public static void validarLetras(java.awt.event.KeyEvent evt) {
		// SI CADA TECLA PRESIONADA ES DIFERENTE DE UNA LETRA EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
		if (!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != ' ') {
			evt.consume();
		}
	}

	public boolean nuevoUsuario() {
		//VALIDAR EL ID PARA CREAR UN NUEVO USUARIO O ACTUALIZAR UNO EXISTENTE
		if (txtIdUsuario.getText().isEmpty()) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (txtCedula.getText().trim().length() == 10 && txtNombres.getText().trim().length() >= 3
				&& txtApellidos.getText().trim().length() >= 3) {
				//RETORNAR NUEVO COMO VERDADERO
				return nuevo = true;
			} else {
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(this,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (!txtIdUsuario.getText().isEmpty()) {//CASO CONTRARIO PARA ACTUALIZAR LOS DATOS DEL USUARIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (txtCedula.getText().trim().length() == 10 && txtNombres.getText().trim().length() >= 3
				&& txtApellidos.getText().trim().length() >= 3) {
				return nuevo;
			} else {
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(this,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		return nuevo;
	}

	public boolean nuevoEquipo() {
		//VALIDAR EL ID PARA CREAR UN NUEVO EQUIPO O ACTUALIZAR UNO EXISTENTE
		if (txtIdEquipo.getText().isEmpty()) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (txtDescripcion.getText().trim().length() >= 2 && txtMarca.getText().trim().length() >= 2
				&& txtModelo.getText().trim().length() >= 3 && txtColor.getText().trim().length() >= 2
				&& !txtCantidad.getText().isEmpty() && txtDepartamento.getText().trim().length() >= 4) {
				//RETORNA VERDADEO
				return nuevo = true;
			} else {
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(this,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (!txtIdEquipo.getText().isEmpty()) {//CASO CONTRARIO PARA ACTUALIZAR LOS DATOS DEL EQUIPO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (txtDescripcion.getText().trim().length() >= 2 && txtMarca.getText().trim().length() >= 2
				&& txtModelo.getText().trim().length() >= 3 && txtColor.getText().trim().length() >= 2
				&& !txtCantidad.getText().isEmpty() && txtDepartamento.getText().trim().length() >= 4) {
				//RETORNAR FALSE
				return nuevo;
			} else {
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(this,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		return nuevo;
	}

	public boolean nuevoPrestamo() {
		//VALIDAR EL ID PARA CREAR UN NUEVO PRESTAMO O ACTUALIZAR UNO EXISTENTE
		if (txtIdPrestamo.getText().isEmpty()) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (!txtEquipo.getText().isEmpty()) {
				if (!txtUsuario.getText().isEmpty()) {
					if (!txtCantidadEquipos.getText().isEmpty()
						&& !txtFechaSalida.getText().isEmpty()
						&& !txtFechaEntrega.getText().isEmpty()) {
						return nuevo = true;

					} else {
						//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
						JOptionPane.showMessageDialog(this, "Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
							"Información", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					//MOSTRAR MENSAJE QUE ESE USUARIO NO EXISTE
					JOptionPane.showMessageDialog(this, "El usuario que ha ingresado no existe!",
						"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				//MOSTRAR MENSAJE QUE ESE EQUIPO NO EXISTE
				JOptionPane.showMessageDialog(this, "El equipo que ha ingresado no existe!",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (!txtIdPrestamo.getText().isEmpty()) {//CASO CONTRARIO PARA ACTUALIZAR LOS DATOS DEL PRESTAMO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (!txtEquipo.getText().isEmpty()) {
				if (!txtUsuario.getText().isEmpty()) {
					if (!txtCantidadEquipos.getText().isEmpty()
						&& !txtFechaSalida.getText().isEmpty()
						&& !txtFechaEntrega.getText().isEmpty()) {
						return nuevo;
					} else {
						//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
						JOptionPane.showMessageDialog(this,
							"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
							"Información", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					//MOSTRAR MENSAJE QUE ESE USUARIO NO EXISTE
					JOptionPane.showMessageDialog(this, "El usuario que ha ingresado no existe!",
						"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				//MOSTRAR MENSAJE QUE ESE EQUIPO NO EXISTE
				JOptionPane.showMessageDialog(this, "El equipo que ha ingresado no existe!",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
		return nuevo;
	}
}
