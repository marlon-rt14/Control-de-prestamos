# Control-de-prestamos
Sistema para el control de prestamos de equipos multimedia

1.- Ejecutar el script SQL que se encuentra en la carpteta SQL.

2.- Abrir el proyecto usando NetBeans

3.- Concectar nuestro proyecto con la base de datos previamente creada.

- Para conectarse a la base de datos, dirígase a la carpeta "META-INF" y abra el archivo "persistence.xml". (También puede usar cualquier editor de texto para abrir este archivo.)

- Identifice el siguiente código y siga los pasos.
<properties>
	/* Cambie el "localhost" por el host donde está alojada la base de datos, generalmente es localhot, o con la dirección IP del host. Reemplace "3307" por el puerto de gestor de base de datos, por defecto en MySQL es 3306. Reemplace "control_prestamos" por el nombre de la base de datos que creó. */
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3307/control_prestamos?	zeroDateTimeBehavior=CONVERT_TO_NULL"/> 
	/*Reemplace "root" por el nombre de usuario de su gestor de base de datos*/
      <property name="javax.persistence.jdbc.user" value="root"/>
	/*Reemplace "root" por la contraseña de su gestor de base de datos.
      <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value="root"/>
</properties>

-Guarde los cambios

4.- Ejecutar el proyecto desde NetBeans

TECNOLOGÍAS USUADAS
Apache NetBeans 11.3
XAMPP 7.4.8.0
MariaDB

