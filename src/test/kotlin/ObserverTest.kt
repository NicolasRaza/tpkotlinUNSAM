import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify

class ObserverTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val relajado = Relajado()
    val neofilo = Neofilo()
    val mockedMailSender = mockedMailSender()

    describe("Test de Observer AgregarItinerarioAPuntuar") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 0,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )


        val motoCilindrada250 = Moto(
            marca = "Honda",
            modelo = "Twister",
            añoDeFabricacion = 2005,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 501.0)
        val destino2 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 4000.0)
        val destino3 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val itinerario2 = Itinerario(usuarioLocal1, mutableListOf(), destino2)
        val itinerario3 = Itinerario(usuarioLocal1, mutableListOf(), destino3)
        val viaje = Viaje(itinerario, motoCilindrada250)

        val agregarItinerarioAPuntuar = AgregarItinerarioAPuntuar(usuarioLocal1)


        it("Se testea que se se agrega el itinerario del viaje a puntuar") {
            agregarItinerarioAPuntuar.excute(viaje)
            usuarioLocal1.itinerariosAPuntuar.contains(itinerario) shouldBe true
            usuarioLocal1.itinerariosAPuntuar.contains(itinerario2) shouldBe false
            usuarioLocal1.itinerariosAPuntuar.contains(itinerario3) shouldBe false
        }


    }
    describe("Test de Observer EnviarMail") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 0,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario1@gmail.com"
        )
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario2@gmail.com"
        )

        val motoCilindrada250 = Moto(
            marca = "Honda",
            modelo = "Twister",
            añoDeFabricacion = 2005,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 501.0)
        val destino2 = Destino("Brasil", ciudad = "Rio De Janeiro", costoBase = 501.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val viaje = Viaje(itinerario, motoCilindrada250)

        val enviarMail = NotificarAmigos(usuarioLocal1).apply {
            mailSender = mockedMailSender
        }
        usuarioLocal2.agregarDestinosDeseados(destino1)
        usuarioLocal1.agregarAmigo(usuarioLocal2)

        it("Verifico que al realizar la tarea recibe un mail ") {
            enviarMail.excute(viaje)
            verify(exactly = 1) {
                mockedMailSender.enviarMail(
                    mail = Mail(
                        from = "usuario1@gmail.com",
                        to = " usuario2@gmail.com",
                        subject = "Visitaron un destino que te puede interesar",
                        cuerpo = "Hola Pedro su amigo Martin pepita visitó Argentina Chubut"
                    )
                )

            }

        }

    }
    describe("Test de Observer cambiar a localista") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 0,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario1@gmail.com"
        )
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario2@gmail.com"
        )

        val motoCilindrada250 = Moto(
            marca = "Honda",
            modelo = "Twister",
            añoDeFabricacion = 2005,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )

        val destino1 = Destino("Brasil", ciudad = "Rio De Janeiro", costoBase = 501.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val viaje = Viaje(itinerario, motoCilindrada250)
        val volverseLocalista = VolverseLocalista(usuarioLocal2)

        it("Al ser criterio relajo le gusta cualquier itinerario") {
            usuarioLocal2.puedeRealizarItinerario(itinerario) shouldBe true
        }


        it("volverse localista") {
            volverseLocalista.excute(viaje)
            usuarioLocal2.puedeRealizarItinerario(itinerario) shouldBe false
        }

    }
    describe("Test de Observer cambiar a selectivo ") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 0,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario1@gmail.com"
        )

        val motoCilindrada250 = Moto(
            marca = "HONDA",
            modelo = "Twister",
            añoDeFabricacion = 2021,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 501.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val viaje = Viaje(itinerario, motoCilindrada250)

        val cambiarASelectivo = VolverseSelectivo(usuarioLocal1)

        it("Le gusta la moto al ser neofilo porque el vehiculo su antiguedad no es mayor a 2 ") {
            usuarioLocal1.leGustaVehiculo(motoCilindrada250) shouldBe true
        }

        it("Cambia de tipo de usuario a selectivo y al no ser una marca de su interes , no le gusta") {

            cambiarASelectivo.excute(viaje)
            usuarioLocal1.leGustaVehiculo(motoCilindrada250) shouldBe false
        }


    }

    describe("Usuario 2 tareas , Test de Observer cambiar a selectivo y localista") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 0,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario1@gmail.com"
        )

        val motoCilindrada250 = Moto(
            marca = "HONDA",
            modelo = "Twister",
            añoDeFabricacion = 2021,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )
        val destino1 = Destino("Brasil", ciudad = "Rio de Janeiro", costoBase = 501.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val viaje = Viaje(itinerario, motoCilindrada250)

        val cambiarASelectivo = VolverseSelectivo(usuarioLocal1)
        val cambiarALocalista = VolverseLocalista(usuarioLocal1)
        usuarioLocal1.agregarObserver(cambiarALocalista)
        usuarioLocal1.agregarObserver(cambiarASelectivo)


        it("Al ser usuario neofilo y con criterio relado le gusta el vehiculo y puede realizar itinerario ") {
            usuarioLocal1.leGustaVehiculo(motoCilindrada250) shouldBe true
            usuarioLocal1.puedeRealizarItinerario(itinerario) shouldBe true
        }

        it("Cambia de tipo de usuario a selectivo y localista") {
            usuarioLocal1.notificarObservers(viaje)
            usuarioLocal1.leGustaVehiculo(motoCilindrada250) shouldBe false
            usuarioLocal1.puedeRealizarItinerario(itinerario) shouldBe false
        }


    }
})