# Biblioteca Project - Java Web Application

Este proyecto muestra como desarrollar una aplicacion Web con Java utilizando Servlets y JDBC.
La parte html se trabaja con bootstrap y jquery.

Si tienes alguna pregunta o consulta referente al ejemplo,
pueden ingresar al siguiente enlace el cual es el chat del proyecto:
[![Join chat https://gitter.im/dao-servlet-example/Lobby](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/dao-servlet-example/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


## Información general

### Dependencias Utilizadas

* JavaEE 7
* JSTL 1.2
* Commons Beanutils
* Log4JDBC Spy driver

### Base de datos

Para este ejemplo se esta utilizando el motor de base de datos de MySQL. Pueden utilizar el archivo [biblioteca_mysql_backup.sql](src/main/resources/biblioteca_mysql_backup.sql) para poder restaurar las tablas del ejemplo.

```
src/main/resources/biblioteca_mysql_backup.sql
```

### Configuración

Para realizar la configuración de la conexión a la base de datos, se debe ingresar los parametros en el archivo [jdbc.properties](src/main/resources/jdbc.properties)

```
src/main/resources/jdbc.properties
```

## Licencia

Este proyecto esta bajo la licencia MIT - ver el siguiente archivo para mas detalles [LICENSE.md](LICENSE)
