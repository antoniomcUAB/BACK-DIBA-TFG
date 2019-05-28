# DIPUTACIÓ DE BARCELONA API
DIPUTACIÓ DE BARCELONA API

# JDK version
1.8

# Maven 3

# Db query process 
Orden para lanzar las querys iniciales
* 1: tables 
* 2: contrains
* 3: secuences 
* 4: sinonimos 
* 5: grans / permisos 

# External dependencies
lombok 1.18.2

# Execute profiles
### Profile's type:
- local
- des

### Command line:
```sh
$ gradle bootRun -Dspring.profiles.active={profile}
```


### Initialize DDBB

Ejecución de los scripts en BBDD en el orden indicado:

1. create_tables.sql
2. create_constraints.sql
3. create_sequences.sql
4. En los entornos de cliente también habrá que lanzar: ```create_synonym.sql y grants_dvol.sql```

Una vez se hayan generado todos los objetos de BBDD se pueden crear los datos básicos para iniciar la aplicación

1. Añadir los datos básicos
2. Añadir las versiones de modelo disponibles

api.db-init: Tiene tres posibles valores: 

- true: Inicializa los datos básicos de frecuencia, roles, gravedad y tipos de persona
- v[n]: Carga de la versión n del modelo (por ejemplo v1 para la primera versión)

### Schema DDBB creation
spring.datasource.initialization-mode=always


# URL REST Services
http://localhost:<port>/context/swagger-ui.html


# DESARROLLO


### Iniciar Oracle

/etc/init.d/oracle-xe-18c start/stop

### Weblogic

Start: ```sh /home/weblogic/wls/config/domains/dsdiba/bin/startWebLogic.sh ```

Start: ```sh /home/weblogic/wls/config/domains/dsdiba/bin/startNodeServer.sh ```

Stop: ```sh /home/weblogic/wls/config/domains/dsdiba/bin/stopWebLogic.sh ```

[Admin console](http://10.14.1.165:8001/console)

User: weblogic
Password: in2pass.2019

### Swagger

http://dsdiba.api.demo.in2.es/dsdiba/api/swagger-ui.html

### application.properties
Un fichero por entorno

- spring.datasource. : Configuración de la BBDD
- spring.datasource.jndi-name : Conector JNDI empleado por Weblogic
- jwt. : Configuración de la autenticación con JWT
- vus. : Configuración del servicio de validación del VUS




