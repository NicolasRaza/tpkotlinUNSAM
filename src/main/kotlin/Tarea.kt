abstract class Tarea(
    val realizador: Usuario,
    val nombre: String,
) {
    lateinit var mailSender: MailSender

    abstract fun ejecutar()

    fun realizarTarea(){
        ejecutar()
        enviarMail()
    }

    fun enviarMail() { //implementar un template method
        mailSender.enviarMail(Mail(from = nombre,
            to = "  " + realizador.mail,
            subject = "Se realizo tarea: $nombre",
            cuerpo = "Se realizo tarea: $nombre"))
    }
}

class HacerAmigo(
    realizador: Usuario,
    val repoAmigos: RepositorioDeUsuario,
    val destinoEspecifico: Destino, nombre: String,
) : Tarea(realizador, nombre) {

    override fun ejecutar() {
        val usuarioConDestinoEspecifico = repoAmigos.repositorio.filter { it.conoceDestino(destinoEspecifico) }
        usuarioConDestinoEspecifico.forEach { realizador.agregarAmigo(it) }
    }

}

class PuntuarItinerario(
    realizador: Usuario,
    val puntaje: Int, nombre: String,
) : Tarea(realizador, nombre) {
    override fun ejecutar() {
        realizador.itinerariosAPuntuar.forEach { realizador.puntuarItinerario(it, puntaje) }
    }

}


class AgregarDestinosDeseado(
    realizador: Usuario,
    nombre: String,
) : Tarea(realizador, nombre) {
    override fun ejecutar() {
        realizador.amigos.forEach { realizador.agregarDestinosDeseados(it.destinoDeseadoMasCaro()!!) }
    }

}

class TransferirItinerario(
    realizador: Usuario,
    nombre: String,
) : Tarea(realizador, nombre) {

    override fun ejecutar() {
        val amigoConMenosDestinos = realizador.amigos.minByOrNull { it.destinosVisitados.count() }
        amigoConMenosDestinos?.itinerariosAPuntuar?.addAll(realizador.itinerariosAPuntuar)
    }
}



