package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Prestamo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-31T22:39:14")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, String> descripcion;
    public static volatile SingularAttribute<Equipo, String> marca;
    public static volatile ListAttribute<Equipo, Prestamo> prestamoList;
    public static volatile SingularAttribute<Equipo, Integer> idEquipo;
    public static volatile SingularAttribute<Equipo, String> color;
    public static volatile SingularAttribute<Equipo, String> propiedad;
    public static volatile SingularAttribute<Equipo, Integer> cantidad;
    public static volatile SingularAttribute<Equipo, String> modelo;

}