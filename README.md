# Le&eacute;me

## Tecnolog&iacute;as
1. Java 11
2. Springboot
3. Jasypt
4. MySQL
5. Swagger
6. Flyway
7. Gradle

# Configuraci&oacute;n Ambiente Local
# 1. Descargar e instalar Servidor XAMPP 
1.1. Antes de desplegar, se debe configurar el servidor donde estarï¿½ la base de datos
1.2. Descargar e Instalar XAMMP -> MySQL (https://www.apachefriends.org/es/index.html)

# 2. Configurar Base de datos:<br/>
2.1. Asignar nueva clave al usuario root ya que el proyecto implementa JASYPT, 
si el usuario root no tiene la clave correcta, nunca desplegará: password_jasypt(3jDOP2%12rv21)<br/>

	>ALTER USER 'root'@'localhost' IDENTIFIED BY 'S1mPl2021.*+';
2.2. Crear base de datos "evalart_reto" en el motor de base de datos mysql<br/>

	>CREATE DATABASE IF NOT EXISTS "evalart_reto" /*!40100 DEFAULT CHARACTER SET utf8 */;

# 3. Run Application - Desplegar Aplicaci&oacute;n
3.1. Se puede correr la aplicaci&oacute;n desde el metodo Main-> CenaClientesMsApplication.java
teniendo presente que el punto 1 y 2 deben estar configurados correctamente.

3.2. Sino se puede configurar la clave del usuario root en mysql, eliminar la clave y el atributo password en el archivo
application.yml

# 4. Consumir la API Rest

4.1 Para ejecutar la funcionalidad principal del programa se debe ejecutar la aplicaci&oacute;n y consumir el siguiente servicio:
	
	>Enviar como parametro el archivo.txt (entrada.txt) con los criterios de selección:
	Parametros: {criteriosSeleccion: entrada.txt}
	>URL Servicio: http://localhost:8080/cenaclientesms/api/v1/invitados
