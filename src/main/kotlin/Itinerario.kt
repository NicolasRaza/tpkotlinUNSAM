class Itinerario(
    val creador: Usuario,
    var listaDeDias: MutableList<DiaDeItinerario> = mutableListOf(),
    var destino: Destino,

) : Entidad{

    override var id: Int? = null

    var usuariosQuePuntuarionItinerario: MutableList<Usuario> = mutableListOf()

    val puntuaciones: MutableList<Int> = mutableListOf()

    fun agregarDia(dia: DiaDeItinerario) {
        listaDeDias.add(dia)
    }

    fun quitarDia(dia: DiaDeItinerario) {
        listaDeDias.remove(dia)
    }


    fun puedeRealizarUnItinerario(Usuario: Usuario, destino: Destino): Boolean =
        Usuario.diasParaViajar >= listaDeDias.count() //&& Usuario.condicionCriterio(destino)


    fun cantidadDeActividades(): Int {
        return listaDeDias.sumOf({ it.cantidadDeActividades() })
    }

    fun costo(): Double {
        return listaDeDias.sumOf({ it.costoActividadPorDia() })
    }

    fun promedioDeTiempoDeActividadPorDia(): Double {
        return listaDeDias.map({ it.duracionDeActividadDelDia() }).average()
    }

    fun dificultad(): Dificultad {
        val cantDeBajas = listaDeDias.sumOf { it.listaDeActividades.count({it.dificultad == Dificultad.BAJA}) }
        val cantDeMedia = listaDeDias.sumOf { it.listaDeActividades.count({it.dificultad == Dificultad.MEDIA}) }
        val cantDeAltas = listaDeDias.sumOf { it.listaDeActividades.count({it.dificultad == Dificultad.ALTA}) }
        if (cantDeAltas >= cantDeMedia && cantDeAltas >= cantDeBajas) {
            return Dificultad.ALTA
        } else if (cantDeMedia >= cantDeAltas && cantDeMedia >= cantDeBajas) {
            return Dificultad.MEDIA
        } else {
            return Dificultad.BAJA
        }


    }

    fun procentajeDeDificultad(dificultad: Dificultad): Double {
        val repeticionesDeDificultad = listaDeActividades().count { it.dificultad == dificultad }
        return repeticionesDeDificultad * 100.0 / cantidadDeActividades()
    }

    fun cantidadDeDias(): Int {
        return listaDeDias.count()
    }

    fun tieneActividadesTodosLosDias(): Boolean {
        return this.listaDeDias.all { it.hayActividades() }
    }

    fun puntuacionEntre1y10(): Boolean {
        return puntuaciones.all { it > 0 } && puntuaciones.all { it < 11 }
    }

    fun esValido(): Boolean {
        return puntuacionEntre1y10()
                && listaDeActividades().isNotEmpty() && !listaDeDias.any{it.hayActividadSolapadas()}

    }


    fun listaDeActividades() = listaDeDias.flatMap { it.listaDeActividades }



}


