import java.util.*

abstract class Observer(
    val usuario: Usuario,
) {
    abstract fun excute(viaje: Viaje)
}


class NotificarAmigos(usuario: Usuario) : Observer(usuario) {
    lateinit var mailSender: MailSender

    override fun excute(viaje: Viaje) {
        usuario.amigos.filter { it.quiereIrA(viaje.destino()) }.forEach { amigo ->
            mailSender.enviarMail(
                Mail(
                    from = usuario.mail, to = " " + amigo.mail,
                    subject = "Visitaron un destino que te puede interesar",
                    cuerpo = "Hola " + amigo.nombre + " su amigo " + usuario.nombre + " " + usuario.apellido + " visitó "
                            + viaje.destino().pais + " " + viaje.destino().ciudad
                )
            )
        }
    }
}

class VolverseLocalista(usuario: Usuario) : Observer(usuario) {

    override fun excute(viaje: Viaje) {
        if (!viaje.esLocal()) {
            usuario.cambiarCriterio(Localista())
        }
    }
}

class VolverseSelectivo(usuario: Usuario) : Observer(usuario) {

    override fun excute(viaje: Viaje) {
        val selectivo =
            Selectivo(MarcaConConvenio.values()[Random().nextInt(MarcaConConvenio.values().size)].toString())
        if (!viaje.vehiculo.tieneConvenio()) {
            usuario.cambiarTipoUsuario(selectivo)
        }
    }
}

class AgregarItinerarioAPuntuar(usuario: Usuario) : Observer(usuario) {
    override fun excute(viaje: Viaje) {
        usuario.itinerariosAPuntuar.add(viaje.itinerario)
    }
}





