# Log API

Api REST usando java e Spring Boot

### Dependencias
* spring-boot-starter-data-jpa
* spring-boot-starter-validation
* spring-boot-starter-web
* flyway-core
* flyway-mysql
* modelmapper: 2.4.5
* spring-boot-devtools
* mariadb-java-client
* lombok

Para quem quiser o mariadb via docker-compose, segue exemplo do arquivo de configuração:

```yaml
version: '3.9'

services:
  mariadb:
    image: mariadb:10.7
    ports:
      - 3306:3306
    volumes:
      - ~/apps/mariadb:/var/lib/mysql
    environment:
      - MYSQL_ROOT_PASSWORD=<senha>
      - MYSQL_PASSWORD=<senha>
      - MYSQL_USER=<usuario>
      - MYSQL_DATABASE=algalog
```

fonte: [How to run Mariadb with Docker and Docker-Compose](https://citizix.com/how-to-run-mariadb-with-docker-and-docker-compose/#:~:text=Using%20the%20docker%2Dcompose%20tool,-We%20can%20achieve&text=With%20Compose%2C%20you%20use%20a,to%20mount%20and%20environment%20variables.&text=The%20commands%3A,up%20brings%20up%20the%20container)

**Para testar a API locamente no Insomnia** 

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=log-api&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fgasfgrv%2Flog-api%2Fmaster%2Falga-log-api.json)

