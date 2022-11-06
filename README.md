# API REST CLIENTE

Este proyecto se generó con [Spring Boot](https://spring.io/blog/2022/10/20/spring-boot-2-7-5-available-now) versión 2.7.5, que contiene una API REST asociada a un Objeto CLIENTE. Cuenta con la implementación de métodos para representar las operaciones CRUD del objeto cliente.

## Objeto Cliente

El objeto cliente consta de los siguientes atributos:

```
{
    "rut": 267066337,
    "dv": "7",
    "nombres": "Jenny",
    "apellidos": "Marquez",
    "domicilio": "San Pablo 1391, depto 204 Santiago",
    "telefono": "+56962234185",
    "email": "jenny@mail.com",
    "sexo": "F"
}
```

La API REST fue diseñada utilizando el rut como id único de cliente.

## Persistencia de Datos

Se implementó una base de datos cloud [MongoDB](https://www.mongodb.com/) gratuita. Desde del portal se generó el cluster de MongoDB y las credenciales de acceso que son parametrizables mediante variables de entorno reconocidas por la aplicación. 

URL de conexión:

```
mongodb+srv://DemoApiRestCliente:885Cb6epwk3e@cluster0.p8bllau.mongodb.net/?retryWrites=true&w=majority
```


<img src="./docs/imagenes/mongodb.png" alt="drawing" width="1100"/>


## Servicio Cloud - Openshift Container Platform

Para el despliegue de la API REST Springboot, se utilizó Red Hat Openshift Dedicated gratuito desde el portal [developers.redhat.com](https://developers.redhat.com/about?extIdCarryOver=true&sc_cid=7013a00000319W5AAI), Esta es una implementación Sandbox de Openshift de acceso online para pruebas de desarrollo de aplicaciones sobre contenedores.

* Despliegue de la Aplicación:

Se utiliza la estrategia source-to-image para la construcción de la imagen contenerizada de la aplicación, para el despliegue se requiere tener instalado el `oc client` y seguir los siguientes pasos los cuales se pueden aplicar sobre cluster de Openshift:

1) Desde la consola web, obtener el login command:   

```
oc login --token=<TOKEN> --server=https://api.sandbox-m2.ll9k.p1.openshiftapps.com:6443
```
2) Construcción de Imagen Contenedor y Despliegue sobre Openshift:   

```
oc new-app openshift/ubi8-openjdk-17:1.11~https://github.com/LuisMiguelOliveros/demo-api.git --name=api-rest-cliente
```
3) Exposición de API mediante Route en openshift:   

```
oc create route edge --service=api-rest-cliente
```
4) Creación de Secret, con las credenciales de acceso a base de datos y de basic auth del API:   

```
oc apply -f docs/secret.yaml
```
5) Configuración de Secret sobre Deployment de la aplicación como variables de entorno:   

```
oc set env --from=secret/credenciales-api-rest deployment/api-rest-cliente
```

Nota: Se implemento sobre el proyecto en springboot la parametrización de variables de entorno para las credenciales sensibles no estén enduro sobre el código fuente.


<img src="./docs/imagenes/openshift.png" alt="drawing" width="1300"/>


## Seguridad de la API

Se implemento el mecanismo de seguridad `Basic Authentication` sobre la API REST, el cual permite la invocación de los métodos de la API y la docs swagger mediante la autenticación de usuario. Estos valores se encuentran en el [secret.yaml](./docs/secret.yaml) el cual creado sobre el openshift e inyectado a nuestra aplicación. Estos valores son parametrizables desde el secret, sin necesidad de editar el código.

Valores por defecto:

```
username: cclh
password: apirestdemo
```

## Documentación API - Swagger

Se incorpora swagger de la implementación de la API Rest en el link: [https://api-rest-cliente-contacto-luisoliveros-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/swagger-ui.html#/](https://api-rest-cliente-contacto-luisoliveros-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/swagger-ui.html#/)

Adicionalmente para facilitar el consumo y prueba de la API, se incorpora una collection de Postman para el consumo de la API [api-rest-cliente.postman_collection](./docs/api-rest-cliente.postman_collection)
