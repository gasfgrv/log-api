# Log API<!-- omit from toc -->

Api REST usando java e Spring Boot, feita durante workshop MSR da algaworks. Alem disso, possui a documentaçao usando
OpenAPI 3.0 e requisições HTTPS.

## Tabela de conteúdos<!-- omit from toc -->

- [Features](#features)
- [Contrato da API](#contrato-da-api)
- [Endpoints da Aplicação](#endpoints-da-aplicação)
  - [/entregas/{entregaId}/finalizacao](#entregasentregaidfinalizacao)
    - [PUT](#put)
      - [Summary:](#summary)
      - [Description:](#description)
      - [Parameters](#parameters)
      - [Responses](#responses)
  - [/clientes/{clienteId}](#clientesclienteid)
    - [GET](#get)
      - [Summary:](#summary-1)
      - [Description:](#description-1)
      - [Parameters](#parameters-1)
      - [Responses](#responses-1)
    - [PUT](#put-1)
      - [Summary:](#summary-2)
      - [Description:](#description-2)
      - [Parameters](#parameters-2)
      - [Responses](#responses-2)
    - [DELETE](#delete)
      - [Summary:](#summary-3)
      - [Description:](#description-3)
      - [Parameters](#parameters-3)
      - [Responses](#responses-3)
  - [/entregas](#entregas)
    - [GET](#get-1)
      - [Summary:](#summary-4)
      - [Description:](#description-4)
      - [Responses](#responses-4)
    - [POST](#post)
      - [Summary:](#summary-5)
      - [Description:](#description-5)
      - [Responses](#responses-5)
  - [/entregas/{entregaId}/ocorrencias](#entregasentregaidocorrencias)
    - [GET](#get-2)
      - [Summary:](#summary-6)
      - [Description:](#description-6)
      - [Parameters](#parameters-4)
      - [Responses](#responses-6)
    - [POST](#post-1)
      - [Summary:](#summary-7)
      - [Description:](#description-7)
      - [Parameters](#parameters-5)
      - [Responses](#responses-7)
  - [/clientes](#clientes)
    - [GET](#get-3)
      - [Summary:](#summary-8)
      - [Description:](#description-8)
      - [Responses](#responses-8)
    - [POST](#post-2)
      - [Summary:](#summary-9)
      - [Description:](#description-9)
      - [Responses](#responses-9)
  - [/entregas/{entregaId}](#entregasentregaid)
    - [GET](#get-4)
      - [Summary:](#summary-10)
      - [Description:](#description-10)
      - [Parameters](#parameters-6)
      - [Responses](#responses-10)
- [Pré-requisitos e como rodar a aplicação/testes](#pré-requisitos-e-como-rodar-a-aplicaçãotestes)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
  - [Dependencias](#dependencias)
  - [Licença](#licença)
  - [Autor](#autor)

## Features

- [X]  CRUD completo de clientes e entregas
- [X]  Listagem e registro de ocorrências a partir de uma entrega

## Contrato da API

<details>
  <summary>Arquivo para criar a API no Swagger</summary>

```json
{
  "openapi":"3.0.1",
  "info":{
    "title":"Log Api",
    "description":"Aplicação desenvolvida para testes.",
    "contact":{
      "name":"gasfgrv",
      "url":"https://github.com/gasfgrv",
      "email":"gustavo_almeida11@hotmail.com"
    },
    "license":{
      "name":"Apache License Version 2.0",
      "url":"https://www.apache.org/licenses/LICENSE-2.0"
    },
    "version":"v1"
  },
  "servers":[
    {
      "url":"https://localhost:8443/",
      "description":"Generated server url"
    }
  ],
  "tags":[
    {
      "name":"Ocorrências",
      "description":"Endpoint para tratamento de Ocorrências relacionadas a uma entrega"
    },
    {
      "name":"Clientes",
      "description":"Endpoint para manipulação dos dados de um cliente"
    },
    {
      "name":"Entregas",
      "description":"Endpoint para tratamento das entregas"
    }
  ],
  "paths":{
    "/entregas/{entregaId}/finalizacao":{
      "put":{
        "tags":[
          "Entregas"
        ],
        "summary":"Finalizar entrega",
        "description":"Finalizar uma determinada entregas a partir do id",
        "operationId":"finalizar",
        "parameters":[
          {
            "name":"entregaId",
            "in":"path",
            "description":"Id da entrega",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Entrega finalizada"
          },
          "204":{
            "description":"No Content"
          }
        }
      }
    },
    "/clientes/{clienteId}":{
      "get":{
        "tags":[
          "Clientes"
        ],
        "summary":"Buscar cliente",
        "description":"Buscar determinado cliente a partir do id",
        "operationId":"buscar_1",
        "parameters":[
          {
            "name":"clienteId",
            "in":"path",
            "description":"Id do cliente",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "responses":{
          "404":{
            "description":"Cliente não encontrado",
            "content":{
              "application/json":{

              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Cliente encontrado",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Cliente"
                }
              }
            }
          }
        }
      },
      "put":{
        "tags":[
          "Clientes"
        ],
        "summary":"Atualizar cliente",
        "description":"Atualizar dados do cliente",
        "operationId":"atualizar",
        "parameters":[
          {
            "name":"clienteId",
            "in":"path",
            "description":"Id do cliente",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "requestBody":{
          "description":"Formulário de atualização",
          "content":{
            "application/json":{
              "schema":{
                "$ref":"#/components/schemas/ClienteInput"
              }
            }
          },
          "required":true
        },
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Cliente atualizado",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/ClienteModel"
                }
              }
            }
          }
        }
      },
      "delete":{
        "tags":[
          "Clientes"
        ],
        "summary":"Deletar cliente",
        "description":"Deletar dados do cliente",
        "operationId":"remover",
        "parameters":[
          {
            "name":"clienteId",
            "in":"path",
            "description":"Id do cliente",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "204":{
            "description":"Cliente apagado",
            "content":{
              "application/json":{

              }
            }
          }
        }
      }
    },
    "/entregas":{
      "get":{
        "tags":[
          "Entregas"
        ],
        "summary":"Listar entregas",
        "description":"Listar todas as entregas salvas",
        "operationId":"listar",
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Todas as entregas",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/EntregaModel"
                }
              }
            }
          }
        }
      },
      "post":{
        "tags":[
          "Entregas"
        ],
        "summary":"Solicitar entrega",
        "description":"Vincula um clienate à entrega e cria a mesma",
        "operationId":"solicitar",
        "requestBody":{
          "description":"Formulário de cadastro",
          "content":{
            "application/json":{
              "schema":{
                "$ref":"#/components/schemas/EntregaInput"
              }
            }
          },
          "required":true
        },
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "201":{
            "description":"Entrega solicitada",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/EntregaModel"
                }
              }
            }
          }
        }
      }
    },
    "/entregas/{entregaId}/ocorrencias":{
      "get":{
        "tags":[
          "Ocorrências"
        ],
        "summary":"Listar ocorrências",
        "description":"Listar todas as ocorrências relacionadas a uma entrega",
        "operationId":"listar_1",
        "parameters":[
          {
            "name":"entregaId",
            "in":"path",
            "description":"Id da entrega",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Todos as ocorrências",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/OcorrenciaModel"
                }
              }
            }
          }
        }
      },
      "post":{
        "tags":[
          "Ocorrências"
        ],
        "summary":"Registrar ocorrencias",
        "description":"Vincula uma ocorrência a uma entrega",
        "operationId":"registrar",
        "parameters":[
          {
            "name":"entregaId",
            "in":"path",
            "description":"Id da entrega",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "requestBody":{
          "description":"dados da ocorrência",
          "content":{
            "application/json":{
              "schema":{
                "$ref":"#/components/schemas/OcorrenciaInput"
              }
            }
          },
          "required":true
        },
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "201":{
            "description":"Ocorrência registrada",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/OcorrenciaModel"
                }
              }
            }
          }
        }
      }
    },
    "/clientes":{
      "get":{
        "tags":[
          "Clientes"
        ],
        "summary":"Listar clientes",
        "description":"Listar todos os clientes salvos",
        "operationId":"listar_2",
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Todos os clientes",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/ClienteResumoModel"
                }
              }
            }
          }
        }
      },
      "post":{
        "tags":[
          "Clientes"
        ],
        "summary":"Salvar cliente",
        "description":"Salvar cliente na base",
        "operationId":"adicionar",
        "requestBody":{
          "description":"Formulário de cadastro",
          "content":{
            "application/json":{
              "schema":{
                "$ref":"#/components/schemas/ClienteInput"
              }
            }
          },
          "required":true
        },
        "responses":{
          "404":{
            "description":"Not Found",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "201":{
            "description":"Cliente Salvo",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Cliente"
                }
              }
            }
          }
        }
      }
    },
    "/entregas/{entregaId}":{
      "get":{
        "tags":[
          "Entregas"
        ],
        "summary":"Buscar entrega",
        "description":"Buscar determinada entrega a partir do id",
        "operationId":"buscar",
        "parameters":[
          {
            "name":"entregaId",
            "in":"path",
            "description":"Id da entrega",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int64"
            }
          }
        ],
        "responses":{
          "404":{
            "description":"Entrega não encontrada",
            "content":{
              "application/json":{

              }
            }
          },
          "400":{
            "description":"Bad Request",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/Problema"
                }
              }
            }
          },
          "200":{
            "description":"Entrega encontrada",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/EntregaModel"
                }
              }
            }
          }
        }
      }
    }
  },
  "components":{
    "schemas":{
      "Campo":{
        "type":"object",
        "properties":{
          "nome":{
            "type":"string"
          },
          "mensagem":{
            "type":"string"
          }
        }
      },
      "Problema":{
        "type":"object",
        "properties":{
          "status":{
            "type":"integer",
            "format":"int32"
          },
          "dataHora":{
            "type":"string",
            "format":"date-time"
          },
          "titulo":{
            "type":"string"
          },
          "campos":{
            "type":"array",
            "items":{
              "$ref":"#/components/schemas/Campo"
            }
          }
        }
      },
      "ClienteInput":{
        "required":[
          "email",
          "nome",
          "telefone"
        ],
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          },
          "nome":{
            "maxLength":60,
            "minLength":0,
            "type":"string"
          },
          "email":{
            "maxLength":255,
            "minLength":0,
            "type":"string"
          },
          "telefone":{
            "maxLength":20,
            "minLength":0,
            "type":"string"
          }
        }
      },
      "ClienteModel":{
        "type":"object",
        "properties":{
          "nome":{
            "type":"string"
          },
          "email":{
            "type":"string"
          },
          "telefone":{
            "type":"string"
          }
        }
      },
      "ClienteIdInput":{
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          }
        }
      },
      "DestinatarioInput":{
        "required":[
          "bairro",
          "complemento",
          "logradouro",
          "nome",
          "numero"
        ],
        "type":"object",
        "properties":{
          "nome":{
            "type":"string"
          },
          "logradouro":{
            "type":"string"
          },
          "numero":{
            "type":"string"
          },
          "complemento":{
            "type":"string"
          },
          "bairro":{
            "type":"string"
          }
        }
      },
      "EntregaInput":{
        "required":[
          "cliente",
          "destinatario",
          "taxa"
        ],
        "type":"object",
        "properties":{
          "cliente":{
            "$ref":"#/components/schemas/ClienteIdInput"
          },
          "destinatario":{
            "$ref":"#/components/schemas/DestinatarioInput"
          },
          "taxa":{
            "type":"number"
          }
        }
      },
      "ClienteResumoModel":{
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          },
          "nome":{
            "type":"string"
          }
        }
      },
      "DestinatarioModel":{
        "type":"object",
        "properties":{
          "nome":{
            "type":"string"
          },
          "logradouro":{
            "type":"string"
          },
          "numero":{
            "type":"string"
          },
          "complemento":{
            "type":"string"
          },
          "bairro":{
            "type":"string"
          }
        }
      },
      "EntregaModel":{
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          },
          "cliente":{
            "$ref":"#/components/schemas/ClienteResumoModel"
          },
          "destinatario":{
            "$ref":"#/components/schemas/DestinatarioModel"
          },
          "taxa":{
            "type":"number"
          },
          "status":{
            "type":"string",
            "enum":[
              "PENDENTE",
              "FINALIZADA",
              "CANCELADA"
            ]
          },
          "dataPedido":{
            "type":"string",
            "format":"date-time"
          },
          "dataFinalizacao":{
            "type":"string",
            "format":"date-time"
          }
        }
      },
      "OcorrenciaInput":{
        "required":[
          "descricao"
        ],
        "type":"object",
        "properties":{
          "descricao":{
            "type":"string"
          }
        }
      },
      "OcorrenciaModel":{
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          },
          "descricao":{
            "type":"string"
          },
          "dataRegistro":{
            "type":"string",
            "format":"date-time"
          }
        }
      },
      "Cliente":{
        "type":"object",
        "properties":{
          "id":{
            "type":"integer",
            "format":"int64"
          },
          "nome":{
            "type":"string"
          },
          "email":{
            "type":"string"
          },
          "telefone":{
            "type":"string"
          }
        }
      }
    }
  }
}
```

</details>

## Endpoints da Aplicação

<details>
  <summary>Documentação dos endpoints</summary>

### /entregas/{entregaId}/finalizacao

#### PUT

##### Summary:

Finalizar entrega

##### Description:

Finalizar uma determinada entregas a partir do id

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| entregaId | path       | Id da entrega | Yes      | long   |

##### Responses

| Code | Description        |
| ---- | ------------------ |
| 200  | Entrega finalizada |
| 204  | No Content         |
| 400  | Bad Request        |
| 404  | Not Found          |

### /clientes/{clienteId}

#### GET

##### Summary:

Buscar cliente

##### Description:

Buscar determinado cliente a partir do id

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| clienteId | path       | Id do cliente | Yes      | long   |

##### Responses

| Code | Description            |
| ---- | ---------------------- |
| 200  | Cliente encontrado     |
| 400  | Bad Request            |
| 404  | Cliente não encontrado |

#### PUT

##### Summary:

Atualizar cliente

##### Description:

Atualizar dados do cliente

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| clienteId | path       | Id do cliente | Yes      | long   |

##### Responses

| Code | Description        |
| ---- | ------------------ |
| 200  | Cliente atualizado |
| 400  | Bad Request        |
| 404  | Not Found          |

#### DELETE

##### Summary:

Deletar cliente

##### Description:

Deletar dados do cliente

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| clienteId | path       | Id do cliente | Yes      | long   |

##### Responses

| Code | Description     |
| ---- | --------------- |
| 204  | Cliente apagado |
| 400  | Bad Request     |
| 404  | Not Found       |

### /entregas

#### GET

##### Summary:

Listar entregas

##### Description:

Listar todas as entregas salvas

##### Responses

| Code | Description       |
| ---- | ----------------- |
| 200  | Todas as entregas |
| 400  | Bad Request       |
| 404  | Not Found         |

#### POST

##### Summary:

Solicitar entrega

##### Description:

Vincula um clienate à entrega e cria a mesma

##### Responses

| Code | Description        |
| ---- | ------------------ |
| 201  | Entrega solicitada |
| 400  | Bad Request        |
| 404  | Not Found          |

### /entregas/{entregaId}/ocorrencias

#### GET

##### Summary:

Listar ocorrências

##### Description:

Listar todas as ocorrências relacionadas a uma entrega

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| entregaId | path       | Id da entrega | Yes      | long   |

##### Responses

| Code | Description          |
| ---- | -------------------- |
| 200  | Todos as ocorrências |
| 400  | Bad Request          |
| 404  | Not Found            |

#### POST

##### Summary:

Registrar ocorrencias

##### Description:

Vincula uma ocorrência a uma entrega

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| entregaId | path       | Id da entrega | Yes      | long   |

##### Responses

| Code | Description           |
| ---- | --------------------- |
| 201  | Ocorrência registrada |
| 400  | Bad Request           |
| 404  | Not Found             |

### /clientes

#### GET

##### Summary:

Listar clientes

##### Description:

Listar todos os clientes salvos

##### Responses

| Code | Description       |
| ---- | ----------------- |
| 200  | Todos os clientes |
| 400  | Bad Request       |
| 404  | Not Found         |

#### POST

##### Summary:

Salvar cliente

##### Description:

Salvar cliente na base

##### Responses

| Code | Description   |
| ---- | ------------- |
| 201  | Cliente Salvo |
| 400  | Bad Request   |
| 404  | Not Found     |

### /entregas/{entregaId}

#### GET

##### Summary:

Buscar entrega

##### Description:

Buscar determinada entrega a partir do id

##### Parameters

| Name      | Located in | Description   | Required | Schema |
| --------- | ---------- | ------------- | -------- | ------ |
| entregaId | path       | Id da entrega | Yes      | long   |

##### Responses

| Code | Description            |
| ---- | ---------------------- |
| 200  | Entrega encontrada     |
| 400  | Bad Request            |
| 404  | Entrega não encontrada |


</details>

## Pré-requisitos e como rodar a aplicação/testes

Criar o arquivo `docker-compose.yaml`:

```yaml
version: "3.7"
services:
  mariadb:
    image: mariadb:10.7
    container_name: algalog_db
    restart: always
    networks:
      - algalog-network
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_PASSWORD: root
      MYSQL_USER: root
      MYSQL_DATABASE: algalog

networks:
  algalog-network:
    driver: bridge
```

rodar os seguintes comandos:

```shell
# Sobe o banco de dados e monta a rede para acesso
docker-compose up -d

# baixa a imagem da API no dockerhub
docker pull gustosilva/log-api:latest 

# Monta o container e sobe a aplicação
docker run --detach \
  --network log-api_algalog-network \
  --env MARIADB_USER=root \
  --env MARIADB_PASS=root \
  --publish 8443:8443 gustosilva/log-api

# logs da aplicação
docker container logs --follow <id_container>
```

Para quem quiser rodar localmente:

* Instalar o mkcert para ajudar na parte da SSL: [Repositório do mkcert](https://github.com/FiloSottile/mkcert);
* Seguir passo-a-passo para configurar o
  certificado: [Enabling Https for local Spring Boot development with mkcert](https://shekhargulati.com/2019/01/19/enabling-https-for-local-spring-boot-development-with-mkcert/);
* Configurar as variáveis de ambiente MARIADB_PASS e MARIADB_USER.
* Subir a base de dados pelo docker compose

Coleção para testar os endpoints da aplicação

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=log-api&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fgasfgrv%2Flog-api%2Fmaster%2Falga-log-api.json)

Obs: Caso ocorra o seguinte erro `SSL peer certificate or SSH remote key was not OK` a solução mais simples é desativar
a validação dos certificados, conforme a imagem:

![img.png](docs/img.png)

## Tecnologias utilizadas

Projeto feito usando **Java 11** e **Maven 3.8** como ferramenta de build.

### Dependencias

* spring-boot-starter-data-jpa
* spring-boot-starter-validation
* spring-boot-starter-web
* spring-boot-devtools
* spring-boot-starter-test
* spring-boot-starter-cache
* flyway-core
* flyway-mysql
* modelmapper
* lombok
* mariadb-java-client
* springdoc-openapi-ui

Licença
Apache License Version 2.0

### Licença

[Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)

### Autor

<div>
    <img style="border-radius: 10%; float: left; margin-right: 20px" src="https://avatars.githubusercontent.com/u/34608751?v=4" width="100px;" alt=""/>
    <p>Feito por Gustavo Silva:</p>
    <a href="https://www.linkedin.com/in/gustavo-silva-69b84a15b/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" height=25></a>
    <a href="https://discordapp.com/users/616994765065420801"><img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white" height=25></a>
    <br>
    <a href="mailto:gustavoalmeidasilva41@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" height=25></a>
    <a href="mailto:gustavo_almeida11@hotmail.com"><img src="https://img.shields.io/badge/Microsoft_Outlook-0078D4?style=for-the-badge&logo=microsoft-outlook&logoColor=white" height=25></a>
</div>
