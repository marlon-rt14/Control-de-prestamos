package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Equipo;
import modelo.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-31T22:39:14")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Integer> idPrestamos;
    public static volatile SingularAttribute<Prestamo, Equipo> idEquipo;
    public static volatile SingularAttribute<Prestamo, Date> fechaSalida;
    public static volatile SingularAttribute<Prestamo, Usuario> idUsuario;
    public static volatile SingularAttribute<Prestamo, Date> fechaEntrega;

}