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
version: "3.7"
services:
  mariadb:
    image: mariadb:10.7
    restart: always
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_PASSWORD: root
      MYSQL_USER: root
      MYSQL_DATABASE: algalog
```

**Para testar a API locamente no Insomnia** 

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=log-api&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fgasfgrv%2Flog-api%2Fmaster%2Falga-log-api.json)

