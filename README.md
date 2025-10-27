# Taller 2 AREP - Daniel Sebastian Ochoa Urrego

Este proyecto construye una arquitectura con memoria sincronizada a traves de difrentes backends donde al enviar un nuevo requistro atravez de un frontend, este se envia a un balanceador de carga, donde usando la estrategia RoundRobin envia la peticion a uno de los 3 backends para que ellos se sincronicen usando JGroups.

## Iniciando

Estas instrucciones te ayudarán a tener una copia de este proyecto corriendo en tu máquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

* Git 
* Java
* Maven
* Docker

Si aún no tienes instaladas estas tecnologias, los siguientes tutoriales te pueden ayudar

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU
* Docker: https://www.youtube.com/watch?v=ZO4KWQfUBBc

### Instalando el proyecto

Para hacer una copia local del proyecto, debes abrir tu terminal, dirigirte al directorio donde quieras que este el proyecto y usar el siguiente comando

```
git clone 
```

Luego muevete al directorio creado y desde ahi ejecuta este comando

```
docker-compose up -d
```

Ya que la aplicación haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en http://localhost:8081 para ver la app corriendo, en ella encontraras una muy bonita página que cree con mucho esfuerzo donde puedes enviar los nombres

<img width="318" height="392" alt="Screenshot 2025-10-26 at 6 52 12 PM" src="https://github.com/user-attachments/assets/cabfa907-2f74-4b2f-8a22-95e72034a07c" />

## Diseño

### Arquitectura

El punto de referencia central de la arquitectura se basa en el siguiente diagrama

<img width="723" height="490" alt="Screenshot 2025-10-26 at 6 53 49 PM" src="https://github.com/user-attachments/assets/9b006a70-aebb-4d78-8bdf-3d881d0f2f62" />

En un principio la idea era depliegar una EC2 por cada Backend y una cuarta para el balanceador de cargas y el cliente JS. Pero debido a problemas de configuraciones, ya que JGroup no funciona bien out-of-the-box en AWS lo que se termino haciendo es desplegar la misma arquitectura en una EC2 separando las aplicaciones en contenedores diferentes, descritos en el diquiente `docker-compose.yml`

```
services:
  facade:
    image: dano1214/backend-lab2-aygo:latest
    container_name: facade
    ports:
      - "8081:8080"
  back-1:
    image: dano1214/backend-lab2-aygo:latest
    container_name: back-1
  back-2:
    image: dano1214/backend-lab2-aygo:latest
    container_name: back-2
  back-3:
    image: dano1214/backend-lab2-aygo:latest
    container_name: back-3
```

### Despliegue

Para desplegar en AWS se siguieron los mismos pasos que en el laboratorio anterior para instalar docker, pero tambien se tuvo que instalar el plugin de docker compose siguiendo las oinstrucciones de esta [pagina](https://docs.docker.com/compose/install/linux/#install-the-plugin-manually). Luego de eso, simplemente corrimos el comando `docker compose up -d` para lanzar los contenedores y listo! La app quedo desplegado como puedes ver en estas imagenes y en el video

<img width="1535" height="174" alt="Screenshot 2025-10-26 at 6 59 59 PM" src="https://github.com/user-attachments/assets/ce1462da-84c5-4f41-b9f5-04e2ffb46bb3" />
<img width="1782" height="755" alt="Screenshot 2025-10-26 at 7 00 15 PM" src="https://github.com/user-attachments/assets/00c955dc-1aa3-4234-8ed2-1729e1c15bfa" />

## Video

Puedes ver el video en el siguiente [link](https://youtu.be/D25cAN7_6S8)

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - [DanielOchoa1214](https://github.com/DanielOchoa1214)

## Licencia

GNU General Public License family

## Agradecimientos

* Figo
