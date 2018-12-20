# DIPUTACIÓ DE BARCELONA API
DIPUTACIÓ DE BARCELONA API

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

Inicialización de test
add.test=[true/false] 


# URL REST Services
http://localhost:<port>/swagger-ui.html



