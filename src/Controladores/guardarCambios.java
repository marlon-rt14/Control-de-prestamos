package Controladores;

import app.PrestamosITCA;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.FacadeEquipos;
import modelo.FacadePrestamos;
import modelo.FacadeUsuarios;
import modelo.entidades.Equipo;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class guardarCambios {

	public void guardarUsuario(String cedula, String nombres, String apellidos, String telefono,
		String email, String direccion) {
		try { // VALIDAD ALGUN POSIBLE ERROR
			//GUARDAR UN NUEVO USUARIO
			FacadeUsuarios.saveUsuario(cedula, nombres, apellidos, telefono, email, direccion);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar guardar un nuevo usuario.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizarUsuario(int id, String cedula, String nombres, String apellidos, String telefono,
		String email, String direccion) {
		try { // VALIDAR ALGUN POSIBLE ERROR
			//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL USUARIO
			int res = JOptionPane.showConfirmDialog(null,
				"¿Está seguro de actualizar este usuarios?", "Actualizar usuario",
				JOptionPane.YES_NO_OPTION);
			if (res == 0) {
				//ACTUALIZAR UN NUEVO USUARIO
				FacadeUsuarios.updateUsuario(id, cedula, nombres, apellidos, telefono, email, direccion);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar actualizar el usuario.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void guardarEquipo(String descripcion, String marca, String modelo, String color,
		int cantidad, String departamento) {
		try { // VALIDAR ALGUN POSIBLE ERROR
			//GUARDAR UN NUEVO EQUIPO
			FacadeEquipos.saveEquipo(descripcion, marca, modelo, color, cantidad, departamento);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar guardar un nuevo equipo.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizarEquipo(int id, String descripcion, String marca, String modelo, String color,
		int cantidad, String departamento) {
		try { // VALIDAR ALGUN POSIBLE ERROR
			//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL EQUIPO
			int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de actualizar este equipo?",
				"Actualizar Equipo", JOptionPane.YES_NO_OPTION);
			if (res == 0) {
				//ACTUALIZAR UN NUEVO EQUIPO
				FacadeEquipos.updateEquipo(id, descripcion, marca, modelo, color, cantidad, departamento);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar actualizar el equipo.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void guardarPrestamo(Equipo equipo, Usuario usuario, int cantidad,
		Date fecha_salida, Date fecha_entrega) { // GUARDAR UN NUEVO PRESTAMO
		try { // VALIDAR ALGUN POSIBLE ERROR
			//INSTANCIAR CLASE PRINCIPAL
			PrestamosITCA parent = new PrestamosITCA();
			//OBTENER EL TOTAL DE EQUIPOS DISPONIBLES
			int totalEquipos = parent.getEquipoPrestamo().getCantidad();
			if (cantidad <= totalEquipos) { //CONSULTAR SI HAY EQUIPOS DISPONIBLES
				//GUARDAR UN NUEVO PRESTAMO
				FacadePrestamos.savePrestamo(equipo, usuario, totalEquipos, fecha_salida, fecha_entrega);
				//ACTUALIZAR EL NUMERO DE EQUIPOS QUE QUEDAN
				pedirEquipos(parent, totalEquipos, cantidad);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar guardar un nuevo préstamo.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void actualizarPrestamo(int id, Equipo equipo, Usuario usuario, int cantidad,
		Date fecha_salida, Date fecha_entrega) {
		try { // VALIDAR ALGUN POSIBLE ERROR
			//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL PRESTAMO
			int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de actualizar este préstamo?",
				"Actualizar Prestamo",
				JOptionPane.YES_NO_OPTION);
			if (res == 0) {
				//OBTENER EL PRESTAMO ACTUAL
				Prestamo pres = FacadePrestamos.getPrestamo(id);
				//PREGUNTAR SI LA CANTIDAD ES MENOR PARA SUMAR LA CANTIDAD DE EQUIPOS QUE VUELVE
				if (cantidad < pres.getCantidadEquipos()) {
					//SUMA LA CANTIDAD DEL EQUIPO QUE VUELVE
					devolverEquipos(cantidad, id);
				} 
				//PREGUNTAR SI LA CANTIDAD ES MAYOR PARA PEDIR MAS EQUIPOS SI HAY DISPONIBLES
				if (cantidad > pres.getCantidadEquipos()) {
					//HACER REFERENCIA A LA CLASE PRINCIPAL PRESTAMOS ITCA
					PrestamosITCA parent = new PrestamosITCA();
					//CONSEGUIR EL TOTAL DE EQUIPOS DEL EQUIPO QUE SE ESTA PRESTANDO
					int totalEquipos = pres.getIdEquipo().getCantidad();
					//LA CANTIDAD DE PRESTA SE REDUCE DE NUEVO
					int sumarCantidad = cantidad - pres.getCantidadEquipos();
					//ACUTALIZAR LA NUEVA CANTIDAD DE EQUIPOS RESTANTES
					pedirEquipos(parent, totalEquipos, sumarCantidad);
				}
				//ACTUALIZAR PRESTAMO
				FacadePrestamos.updatePrestamo(id, equipo, usuario, cantidad, fecha_salida, fecha_entrega);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Se ha producido un error a intentar actualizar el préstamo.",
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void devolverEquipos(int cantidadPrestados, int id_prestamo) {
		//OBTENER EL PRESTAMOS ACTUAL
		Prestamo actual = FacadePrestamos.getPrestamo(id_prestamo);
		//OBTENER EL EQUIPO AL QUE VAMOS A DEVOLVER
		Equipo equipo = actual.getIdEquipo();
		//OBTENER TODOS LOS CAMPOS DEL EQUIPO QUE PRESTAMOS
		int idEquipo = equipo.getIdEquipo();
		String descripcion = equipo.getDescripcion();
		String marca = equipo.getMarca();
		String modelo = equipo.getModelo();
		String color = equipo.getColor();
		// SUMAMOS LA CANTIDAD QUE PRESTAMOS MAS LA QUE YA TENIA
		int cantidadNueva = equipo.getCantidad() + cantidadPrestados;
		String propiedad = equipo.getPropiedad();
		//ACTUALIZAR LA CANTIDAD DEL EQUIPO QUE SE DEVUELVE
		FacadeEquipos.updateEquipo(idEquipo, descripcion, marca, modelo, color, cantidadNueva, propiedad);
	}

	public void pedirEquipos(PrestamosITCA parent, int totalEquipos, int cantidadPrestamo) {
		//ACTUALIZAR LA CANTIDAD DE EQUIPOS DISPONIBLES
		int id_equipo = parent.getEquipoPrestamo().getIdEquipo(); // id del equipo que prestamos
		String _descripcion = parent.getEquipoPrestamo().getDescripcion(); // descripciond el equipo que prestamos
		String _marca = parent.getEquipoPrestamo().getMarca(); // marca del equipo que prestamos
		String _modelo = parent.getEquipoPrestamo().getModelo(); // modelo del equipo que prestamos
		String _color = parent.getEquipoPrestamo().getColor(); // color del equipo que prestamos
		int _cantidad_restante = totalEquipos - cantidadPrestamo; // establecemos la nueva cantidad de equpos disponibles
		String _departamento = parent.getEquipoPrestamo().getPropiedad(); // departamento al cual pertenece el equipo
		//ACTUALIZAR EL NUMERO DE EQUIPOS DISPONIBLE
		FacadeEquipos.updateEquipo(id_equipo, _descripcion, _marca, _modelo,
			_color, _cantidad_restante, _departamento);
	}
}
