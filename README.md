# tp-holamundo-2022-2022-grupo-10

Entrega 1

En esta iteración, agregaremos nuevas entidades y funcionalidades necesarias para cumplir con los requerimientos que nos dieron.

Itinerarios

Los usuarios del sistema pueden crear itinerarios para realizar actividades en un Destino determinado. 
Los itinerarios, sólo pueden ser editados por el usuario creador, o por un amigo, si es que este último conoce el destino perteneciente al itinerario.
Necesitamos poder determinar la cantidad de días, actividades, duración promedio por día, dificultad y costo del mismo.
La dificultad de un itinerario está dada por las dificultades de las actividades diarias que lo componen, es decir que si prevalecen más actividades de un tipo de dificultad, esta debería ser la del itinerario, llegado el caso que las cantidades sean iguales, se establece la de mayor dificultad.

Actividades
Cuentan con :
Costo: un valor en pesos.
Descripción: un detalle resumido de la actividad a realizar.
Inicio: es el horario del día, para comenzar la actividad.
Fin: es el horario del día, para finalizar la actividad.
Duración: se desprende de los minutos transcurridos entre el inicio y fin de la actividad
Dificultad: existen 3 tipos de dificultades
BAJA
MEDIA
ALTA


Usuarios

A lo que ya sabíamos de los usuarios se agrega lo siguiente: 
Días para viajar: es la cantidad de días que dispone para realizar un viaje
Amigos: son usuarios de la aplicación, que un usuario considera amigo. La relación de amistad no necesariamente debe ser bidireccional.
Destinos deseados: todos los usuarios tienen destinos que desean visitar.
Destinos visitados: son los destinos que visitaron, en algún viaje realizado.

Es necesario determinar si un usuario conoce a un destino, esto sucede cuando el usuario desea ir al destino, o ya lo visitó.

Itinerarios a puntuar: solo puede puntuar itinerarios de los cuales no sea el creador y con destinos que conoce.

Para saber si un usuario puede realizar un itinerario, el itinerario debe cubrir los días para viajar que tiene el usuario, es decir que :
días para viajar >= días de itinerario
A su vez, identificamos distintos criterios para terminar de definir si el usuario puede realizar el itinerario.
Al momento conocemos a los siguientes:
los relajados, no lo dudan, cualquier itinerario le viene bien
el precavido: solo acepta si el destino ya lo conoce o si lo conoce algún amigo. 
el localista solo le gusta viajar dentro de Argentina.
hay algunos soñadores que solo quieren viajar a los destinos deseados o a destinos de mayor valor que el del destino deseado más caro.
están los activos, estos quieren tener todos los días actividades para realizar.
otros son exigentes y quieren garantizarse que, por lo menos un determinado porcentaje de actividades del itinerario, sean de una determinada Dificultad. (Ejemplo el 50% de las actividades sean de una Dificultad ALTA).

El sistema debe permitirle al usuario, cambiar de criterio en el momento que lo desee.

Validaciones

Para que un destino sea válido:
Debe tener tanto país como ciudad, no deben ser nulos o vacíos.
El costo base debe ser mayor a cero.

Las actividades son válidas si:

La descripción no es nula o vacía.
El horario de inicio < horario fin.
Deben tener una Dificultad establecida.
El costo debe ser mayor o igual a cero.

Los itinerario son válidos si:
Tienen creador y destino no nulos.
Deben tener al menos 1 día con actividades.
Las actividades del día no deben solaparse en horarios.
Las puntuaciones deben ser del 1 al 10 y no pueden recibir más de una por usuario.

Los usuarios son válidos si:
Todos los campos String no deben ser nulos o vacíos.
La fecha de alta no puede ser posterior a la del día.
Los días para viajar, deben ser mayores a cero.
Todos los usuarios deben tener al menos un destino deseado.


Se pide: 

1 Diseñar e implementar el modelo de objetos de dominio. 

2 Diseñar e implementar los casos de prueba correspondientes.
    a)Armar el juego de datos necesario para realizar las pruebas
    b)Codificar los tests unitarios 














