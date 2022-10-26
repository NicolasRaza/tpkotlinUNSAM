
class DiaDeItinerario(
    val listaDeActividades: MutableList<Actividad> = mutableListOf(),
) {

    fun agregarActividad(actividad: Actividad) {
        listaDeActividades.add(actividad)
    }

    fun quitarActividad(actividad: Actividad) {
        listaDeActividades.remove(actividad)
    }

    fun duracionDeActividadDelDia(): Int {
        return listaDeActividades.sumOf { it.duracion() }
    }

    fun costoActividadPorDia(): Double {
        return listaDeActividades.sumOf { it.costo }
    }

    fun hayActividadSolapadas(): Boolean = listaDeActividades.any { it.noSeSolapan(listaDeActividades) }


    fun cantidadDeActividades() = listaDeActividades.count()

    fun hayActividades(): Boolean = listaDeActividades.isNotEmpty()


}