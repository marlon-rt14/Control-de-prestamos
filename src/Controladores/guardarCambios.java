package Controladores;

import app.PrestamosITCA;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.FacadeEquipos;
import modelo.FacadePrestamos;
import modelo.FacadeUsuarios;
import modelo.entidades.Equipo;
import modelo.entidades.Prestamo;

public class guardarCambios extends PrestamosITCA {

	public void guardarUsuario() {
		//GUARDAR UN NUEVO USUARIO
		FacadeUsuarios.saveUsuario(txtCedula.getText(), txtNombres.getText(), txtApellidos.getText(),
			txtTelefono.getText(), txtEmail.getText(), txtDireccion.getText());

		//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN USARUIO NEUVO
		JOptionPane.showMessageDialog(this, "Se ha creado un nuevo usuario exitosamente!", "Nuevo Usario",
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizarUsuario() {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL USUARIO
		int res = JOptionPane.showConfirmDialog(this,
			"¿Está seguro de actualizar este usuarios?", "Actualizar usuario",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR UN NUEVO USUARIO
			FacadeUsuarios.updateUsuario(Integer.parseInt(txtIdUsuario.getText()), txtCedula.getText(),
				txtNombres.getText(), txtApellidos.getText(),
				txtTelefono.getText(), txtEmail.getText(), txtDireccion.getText());
			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN USARUIO NEUVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el usuario"
				+ txtNombres.getText() + txtApellidos.getText() + "exitosamente!", "Actualizar usuario",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void guardarEquipo() {
		//GUARDAR UN NUEVO EQUIPO
		FacadeEquipos.saveEquipo(txtDescripcion.getText(), txtMarca.getText(), txtModelo.getText(),
			txtColor.getText(), Integer.parseInt(txtCantidad.getText()), txtDepartamento.getText());
		//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN EQUIPO NEUVO
		JOptionPane.showMessageDialog(this, "Se ha creado un nuevo equipo exitosamente!", "Nuevo Equipo",
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizarEquipo() {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL EQUIPO
		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar este equipo?", "Actualizar Equipo",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR UN NUEVO EQUIPO
			FacadeEquipos.updateEquipo(Integer.parseInt(txtIdEquipo.getText()), txtDescripcion.getText(),
				txtMarca.getText(), txtModelo.getText(),
				txtColor.getText(), Integer.parseInt(txtCantidad.getText()), txtDepartamento.getText());
			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN EQUIPO NUEVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el equipo"
				+ txtDescripcion.getText() + "exitosamente!", "Actualizar Equipo",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void guardarPrestamo() throws ParseException {
		//GUARDAR UN NUEVO PRESTAMO
		int cantidad = Integer.parseInt(txtCantidadEquipos.getText());
		int totalEquipos = getEquipoPrestamo().getCantidad();
		if (cantidad <= totalEquipos) { //VERIFICAR SI HAY EQUIPOS DISPONIBLES
			//GUARDAR UN NUEVO PRESTAMO
			FacadePrestamos.savePrestamo(getEquipoPrestamo(), getUsuarioPrestamo(),
				cantidad,
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaSalida.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaEntrega.getText()));

			//ACTUALIZAR LA CANTIDAD DE EQUIPOS
			int id_equipo = getEquipoPrestamo().getIdEquipo();
			String _descripcion = getEquipoPrestamo().getDescripcion();
			String _marca = getEquipoPrestamo().getMarca();
			String _modelo = getEquipoPrestamo().getModelo();
			String _color = getEquipoPrestamo().getColor();
			int _cantidad_restante = totalEquipos - cantidad;
			String _departamento = getEquipoPrestamo().getPropiedad();
			//ACTUALIZAR EL NUMERO DE EQUIPOS RESTANTES
			FacadeEquipos.updateEquipo(id_equipo, _descripcion, _marca, _modelo,
				_color, _cantidad_restante, _departamento);

			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN PRESTAMO NEUVO
			JOptionPane.showMessageDialog(this, "Se ha creado un nuevo PRESTAMO exitosamente!",
				"Nuevo Prestamo",
				JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void actualizarPrestamo() throws ParseException {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL PRESTAMO
		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar este préstamo?",
			"Actualizar Prestamo",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR PRESTAMO
			FacadePrestamos.updatePrestamo(Integer.parseInt(String.valueOf(txtIdPrestamo.getText())),
				getEquipoPrestamo(), getUsuarioPrestamo(), Integer.parseInt(txtCantidadEquipos.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaSalida.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaEntrega.getText()));

			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN PRESTAMO NUEVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el equipo préstamo exitosamente!",
				"Actualizar Equipo",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void devolverEquipos() {
		//OBTENER LA CANTIDAD DE EQUIPOS PRESTADOS
		Prestamo actual = FacadePrestamos.getPrestamo(Integer.parseInt(txtIdPrestamo.getText()));
		int cantidadPrestados = actual.getCantidadEquipos();
		//OBTENER EL EQUIPO QUE VAMOS A DEVOLVER
		Equipo equipo = actual.getIdEquipo();
		//OBTENER TODOS LOS CAMPOS DE NUESTRO EQUIPO EN BASE A UN IDEQUIPO EXCEPTO LA CANTIDAD
		int idEquipo = equipo.getIdEquipo();
		String descripcion = equipo.getDescripcion();
		String marca = equipo.getMarca();
		String modelo = equipo.getModelo();
		String color = equipo.getColor();
		int cantidadNueva = equipo.getCantidad() + cantidadPrestados;
		String propiedad = equipo.getPropiedad();
		//ACTUALIZAR LA CANTIDAD DEL EQUIPO QUE SE DEVUELVE
		FacadeEquipos.updateEquipo(idEquipo, descripcion, marca, modelo, color, cantidadNueva, propiedad);
	}

}
