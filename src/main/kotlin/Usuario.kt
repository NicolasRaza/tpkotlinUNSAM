import java.time.LocalDate
import java.time.Period


class Usuario(
    val nombre: String,
    val apellido: String,
    val username: String,
    var paisDeResidencia: String,
    var fechaDeAlta: LocalDate = LocalDate.now(),
    val diasParaViajar: Int,
    val amigos: MutableList<Usuario> = mutableListOf(),
    val destinosVisitados: MutableList<Destino> = mutableListOf(),
    val destinosDeseados: MutableList<Destino> = mutableListOf(),
    var criterio: Criterio,
    var tipoDeUsuario: TipoDeUsuario,
    val mail: String,
    val observers : MutableList<Observer> = mutableListOf()

) : Entidad {

    override var id: Int? = null
    var itinerariosAPuntuar: MutableList<Itinerario> = mutableListOf()

    fun antiguedad() = Period.between(fechaDeAlta, LocalDate.now()).years

    fun agregarDestinosVisitados(destino: Destino) {
        destinosVisitados.add(destino)
    }

    fun agregarDestinosDeseados(destino: Destino) {
        destinosDeseados.add(destino)
    }

    fun agregarAmigo(usuario: Usuario) {
        amigos.add(usuario)
    }

    fun cambiarCriterio(nuevoCriterio: Criterio) {
        criterio = nuevoCriterio
    }

    fun cambiarTipoUsuario(nuevoTipoDeUsuario: TipoDeUsuario) {
        tipoDeUsuario = nuevoTipoDeUsuario
    }

    fun quiereIrA(destino: Destino): Boolean = destinosDeseados.contains(destino)

    fun fueDeVisitaA(destino: Destino): Boolean = destinosVisitados.contains(destino)

    fun destinoDeseadoMasCaro(): Destino? {
        return destinosDeseados.find { it.costoBase == destinosDeseados.maxOf { it.costoBase } }                                                             // Igual que el average por que no funcionaba
    }

    fun conoceDestino(destino: Destino): Boolean = quiereIrA(destino) || fueDeVisitaA(destino)

    fun amigoReconoceDestino(destino: Destino): Boolean = amigos.any { it.conoceDestino(destino) }

    fun puedeModificarItinerario(itinerario: Itinerario): Boolean {
        return itinerario.creador == this || (itinerario.creador.amigos.contains(this) && conoceDestino(
            itinerario.destino
        ))
    }

    fun puedePuntuarItineraio(itinerario: Itinerario): Boolean {
        return this != itinerario.creador && conoceDestino(itinerario.destino) && !itinerario.usuariosQuePuntuarionItinerario.contains(
            this
        )
    }

    fun puntuarItinerario(itinerario: Itinerario, puntuacion: Int) {
        if (puedePuntuarItineraio(itinerario)) {
            itinerario.puntuaciones.add(puntuacion)
            itinerario.usuariosQuePuntuarionItinerario.add(this)
        }
        itinerariosAPuntuar.remove(itinerario)
    }

    fun esValido(): Boolean {
        return nombre.isNotEmpty() && apellido.isNotEmpty() && username.isNotEmpty() && fechaDeAlta <= LocalDate.now() && diasParaViajar > 0
                && destinosDeseados.isNotEmpty()
    }

    fun puedeRealizarItinerario(itinerario: Itinerario): Boolean {
        return criterio.puedeRealizarItinerario(
            usuario = this,
            itinerario = itinerario
        ) && diasParaViajar >= itinerario.cantidadDeDias()
    }

    fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return tipoDeUsuario.leGustaVehiculo(vehiculo)
    }


    fun realizarViaje(viaje: Viaje) {
        destinosVisitados.add(viaje.destino())
        notificarObservers(viaje)
    }

    fun agregarObserver(observer : Observer){
        observers.add(observer)
    }

    fun quitarObserver(observer : Observer){
        observers.remove(observer)
    }

    fun notificarObservers(viaje: Viaje) {
       observers.forEach{observer -> observer.excute(viaje)}
    }

}

