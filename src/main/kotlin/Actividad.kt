import java.time.LocalTime
import java.time.temporal.ChronoUnit



class Actividad(
    var inicio: LocalTime,
    var fin: LocalTime,
    var dificultad: Dificultad,
    var costo: Double = 10.000,
    var descripcionDeActividad: String,
) {

    fun duracion(): Int {
        return ChronoUnit.MINUTES.between(inicio, fin)
            .toInt() //compara los minutos entre el inicio y el fin de la actividad
    }

    fun esValido(): Boolean {
        return descripcionDeActividad.isNotEmpty() && inicio < fin && costo >= 0
    }

    fun noSeSolapan(listaDeActividades : MutableList<Actividad>): Boolean{
        return !listaDeActividades.filter { it != this }.all { this.inicio >= it.fin || this.fin <= it.inicio  }
    }



}


