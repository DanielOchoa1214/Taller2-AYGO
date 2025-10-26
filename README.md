# Taller 2 AREP - Daniel Sebastian Ochoa Urrego

Este proyecto construye una arquitectura con memoria sincronizada a traves de difrentes backends donde al enviar un nuevo requistro atravez de un frontend, este se envia a un balanceador de carga, donde usando la estrategia RoundRobin envia la peticion a uno de los 3 backends para que ellos se sincronicen usando JGroups.

## Iniciando

Estas instrucciones te ayudarán a tener una copia de este proyecto corriendo en tu máquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

* Docker
* Git
* Java

### Instalando el proyecto

Para tener una imagen local del proyecto solo debes correr el siguiente comando

```
docker run -d -p 33025:33025 --name ec2aygocontainer dano1214/taller1-aygo:latest
```

Ya que la aplicacián haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en `http://localhost:42000/greeting` para ver la app corriendo

<img width="348" height="136" alt="Screenshot 2025-10-18 at 2 24 29 PM" src="https://github.com/user-attachments/assets/abb75b7d-6edc-405e-a4f9-311e5e542afa" />

## Diseño

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
