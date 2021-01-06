package Controladores;

import app.PrestamosITCA;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class validar {

	public validar() {
	}

	public void validarNumeros(java.awt.event.KeyEvent evt) {
		// SI CADA TECLA PRESIONADA ES DIFERENTE DE UN NUMERO EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
		if (!Character.isDigit(evt.getKeyChar())) {
			evt.consume();
		}
	}

	public void validarTelefono(java.awt.event.KeyEvent evt, String telefono) {
		if (telefono.trim().length() < 10) {
			// SI CADA TECLA PRESIONADA ES DIFERENTE DE UN NUMERO EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
			if (!Character.isDigit(evt.getKeyChar())) {
				evt.consume();
			}
		} else {
			evt.consume();
		}
	}

	public void validarCedula(java.awt.event.KeyEvent evt, String cedula) {
		if (cedula.trim().length() < 10) {
			// SI CADA TECLA PRESIONADA ES DIFERENTE DE UN NUMERO EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
			if (!Character.isDigit(evt.getKeyChar())) {
				evt.consume();
			}
		} else {
			evt.consume();
		}
	}

	public static void validarLetras(java.awt.event.KeyEvent evt) {
		// SI CADA TECLA PRESIONADA ES DIFERENTE DE UNA LETRA EL EVENTO SE CONSUME Y ESA TECLA NO SE ESCRIBIRA
		if (!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != ' ') {
			evt.consume();
		}
	}

	public boolean nuevoUsuario(String id, String cedula, String nombres, String apellidos) {
		//VALIDAR EL ID PARA CREAR UN NUEVO USUARIO O ACTUALIZAR UNO EXISTENTE
		if (id.trim().length() == 0) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (cedula.trim().length() > 0 && nombres.trim().length() >= 3
				&& apellidos.trim().length() >= 3) {
				//RETORNAR NUEVO COMO VERDADERO
				return true;
			} else {
				System.out.println("Cedula = " + cedula);
				System.out.println("Nombres = " + nombres);
				System.out.println("Apellidos = " + apellidos);
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(null,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		return false;
	}

	public boolean nuevoEquipo(String id, String descripcion, String marca, String modelo,
		String color, int cantidad, String departamento) {
		//VALIDAR EL ID PARA CREAR UN NUEVO EQUIPO O ACTUALIZAR UNO EXISTENTE
		if (id.trim().length() == 0) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (descripcion.trim().length() >= 2
				&& marca.trim().length() >= 2
				&& modelo.trim().length() >= 3
				&& color.trim().length() >= 2
				&& cantidad != 0
				&& departamento.trim().length() >= 4) {
				//RETORNA VERDADEO
				return true;
			} else {
				//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
				JOptionPane.showMessageDialog(null,
					"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
					"Información", JOptionPane.WARNING_MESSAGE);
			}
		}
		return false;
	}

	public boolean nuevoPrestamo(String id, String equipo, String usuario, int cantidad_equipos,
		String fecha_salida, String fecha_entrega) {
		//VALIDAR EL ID PARA CREAR UN NUEVO PRESTAMO O ACTUALIZAR UNO EXISTENTE
		if (id.trim().length() == 0) { //PREGUNTAR SI EL CAMPO ID ESTA VACIO
			//VALIDAR LOS CAMPOS A GUARDAR
			if (!equipo.isEmpty()) {
				if (!usuario.isEmpty()) {
					if (cantidad_equipos != 0
						&& fecha_salida.trim().length() == 10
						&& fecha_entrega.trim().length() == 10) {
						return true;

					} else {
						//MOSTRAR MENSAJE DE ADVERTENCIA DE DATOS INCORRECTOS
						JOptionPane.showMessageDialog(null,
							"Asegúrese de ingresar los datos correctos en los campos obligatorios (*).",
							"Información", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					//MOSTRAR MENSAJE QUE ESE USUARIO NO EXISTE
					JOptionPane.showMessageDialog(null, "El usuario que ha ingresado no existe!",
						"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				//MOSTRAR MENSAJE QUE ESE EQUIPO NO EXISTE
				JOptionPane.showMessageDialog(null, "El equipo que ha ingresado no existe!",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
		return false;
	}

	// VLIDAR NUEVA CANTIDAD DE EQUIPOS PRESTADOS
	public boolean numEquiposPrestados(int cantidadNueva, int cantidadActual) {
		if (cantidadNueva <= cantidadActual) {
			return true;
		}
		return false;
	}
}
