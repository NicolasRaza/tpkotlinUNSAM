abstract class Criterio() {


    abstract fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean

}


class Relajado : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean = true
}

class Precavido : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean =
        usuario.conoceDestino(itinerario.destino) || usuario.amigoReconoceDestino(itinerario.destino)
}


class Localista : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean = itinerario.destino.esLocal()

}

class Soñador : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean {
        val destinoMasCaro = usuario.destinoDeseadoMasCaro()
        if (destinoMasCaro != null) {
            return usuario.quiereIrA(itinerario.destino) || itinerario.destino.costoBase > destinoMasCaro.costoBase
        } else {
            return false
        }

    }
}

class Activo : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean {
        return itinerario.tieneActividadesTodosLosDias()
    }


}

class Exigente(val porcentaje : Double, val dificultad: Dificultad) : Criterio() {
    override fun puedeRealizarItinerario(
        usuario: Usuario,
        itinerario: Itinerario,
    ): Boolean {
        return itinerario.procentajeDeDificultad(dificultad) >= porcentaje

    }
}

