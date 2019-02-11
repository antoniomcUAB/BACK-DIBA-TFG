# DIPUTACIÓ DE BARCELONA API
DIPUTACIÓ DE BARCELONA API

# JDK version
1.8

# Maven 3

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


### Initialize DDBB
api.db-init=true

### Schema DDBB creation
spring.datasource.initialization-mode=always


# URL REST Services
http://localhost:<port>/context/swagger-ui.html


# DESARROLLO


### Iniciar Oracle

/etc/init.d/oracle-xe-18c start/stop

### Weblogic

Start: ```sh /home/weblogic/wls/config/domains/dsdiba/bin/startWebLogic.sh ```

Stop: ```sh /home/weblogic/wls/config/domains/dsdiba/bin/stopWebLogic.sh ```

[Admin console](http://dsdiba-api.demo.in2.es/console)

User: weblogic
Password: in2pass.2019

### Swagger

http://dsdiba-api.demo.in2.es/dsdiba/swagger-ui.html



