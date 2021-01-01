
package Controladores;

import app.PrestamosITCA;
import app.RegistroUsuarios;
import modelo.entidades.Equipo;
import modelo.entidades.Usuario;

//EXTENDER DE LA CLASE PRSTAMO ITCA PARA TENER ACCESO A SUS OBJETOS Y PROPIEDADES
public class AceptarDatos extends PrestamosITCA{
	
	Usuario usuario;
	Equipo equipo;

	//SI EL CONSTRUCTOR RECIBE COMO PARAMETRO UN USUARIO INICIA USUARIO CON USUARIO
	public AceptarDatos(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//SI EL CONSTRUCTOR RECIBE COMO PARAMETRO UN EQUIPO INICIA EQUIPO CON EQUIPO
	public AceptarDatos(Equipo equipo){
		this.equipo = equipo;
	}
	
	public void registrarUsuario(){
		setUsuarioPrestamo(this.usuario);
		//txtUsuario.setText(usuarioPrestamo.getCedula());
	}
	
	public void registrarEquipo(){
		setEquipoPrestamo(this.equipo);
		//System.out.println("El dato es: " + getEquipoPrestamo().getDescripcion());
		//txtEquipo.setText(getEquipoPrestamo().getDescripcion());
	}
	
}
