# DIPUTACI� DE BARCELONA API
DIPUTACI� DE BARCELONA API

# JDK version
1.8

# Gradle version
4.7

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

### Run configuration - profile in Eclipse:
VM -Dspring.profiles.active={profile}

# Generate jar file
```sh
$ gradlew bootJar -Pprofile={profile}
```

### Initialize DDBB
api.db-init=true

Inicializaci�n de test
add.test=[true/false] 


# URL REST Services
http://localhost:<port>/swagger-ui.html


# DESARROLLO

### Iniciar aplicación
java -jar /usr/dsdiba/ds-diba-api-0.0.1-SNAPSHOT.jar

### Iniciar Oracle

/etc/init.d/oracle-xe-18c start/stop

### URL servicio modelo

http://10.14.1.165:8080/model



