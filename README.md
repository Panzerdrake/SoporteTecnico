¡Bienvenido al sistema de gestion de tickets de soporte tecnico!

Descripcion: 
Este proyecto consiste en un prototipo de un sistema de gestion y seguimiento de tickets
de soporte tecnico, desarrollado para la empresa ficticia Córdoba Microelectrónica S.A.

Este sistema permite que un cliente inicie sesion, registre solicitudes de soporte técnico relacionadas
con productos y consulte los tickets que haya generado.

Este proyecto fue realizado para el seminario de practica de la licenciatura en informatica de la Universidad Siglo 21.

Tecnologias utilizadas:

Java
MySQL
JDBC
Eclipse IDE
MySQL Workbench

Funcionalidades implementadas:

Inicio de sesion mediante correo electronico y contraseña.
Identificacion de usuarios según su rol
Registro de tickets de soporte técnico
Consulta de tickets asociados al cliente
Consulta de productos registrados
Manejo de errores de conexion con la base de datos.

Estructura del proyecto:

src/app: punto de entrada de la aplicacion
src/modelo: clases que representan los objetos del sistema
src/datos: conexion con MySQL y clase de acceso a datos
src/vista: interfaces graficas hechas con Swing
database: script SQL para crear la base de datos.
lib: controlador JDBC de MySQL

Requisitos para ejecutar el prototipo

* Java JDK compatible con el proyecto
* Eclipse IDE
* MySQL server
* MySQL Connector/J

Instalacion e ejecucion

1. Clonar o descargar el repositorio
2. Importar el proyecto Java en eclipse.
3. Ejecutar el script database/seminarioSQL.sql en MySQL Workbench para crear la abse de datos y sus tablas
4. Configurar las variables del entorno MYSQL_USER y MYSQL_PASSWORD con las credenciales de acceso a MySQL
5. Comprobar que el controlador JDBC de MySQL este configurado en el proyecto
6. Verificar que el servidor MySQL este iniciado
7. Ejecutar la clase app.Main desde eclipse

Alcance/Proposito

Este proyecto corresponde a un prototipo funcional desarrollado con fines academicos.
Se implementaron las funcionalidades principales relacionadas con el acceso al sistema, el registro y la consulta de tickets.

Las funcionalidades administrativas y de gestion avanzada del soporte tecnico forman parte del diseño general,
pero no están implementadas completamente en esta version. 