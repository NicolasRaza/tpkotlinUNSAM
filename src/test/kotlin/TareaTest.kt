import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify

class TareaTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val relajado = Relajado()
    val neofilo = Neofilo()
    val mockedMailSender = mockedMailSender()

    describe("Test de Tarea hacer amigos ") {

        val usuarioLocal1 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 9,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal2 =
            Usuario(
                "juan",
                "lopez",
                "juan12",
                "Chile",
                diasParaViajar = 9,
                criterio = relajado,
                tipoDeUsuario = neofilo,
                mail = "usuario@gmail.com"
            )
        val usuarioLocal3 =
            Usuario(
                "juan",
                "lopez",
                "juan12",
                "Chile",
                diasParaViajar = 9,
                criterio = relajado,
                tipoDeUsuario = neofilo,
                mail = "usuario@gmail.com"
            )
        val usuarioLocal4 =
            Usuario(
                "juan",
                "lopez",
                "juan12",
                "Chile",
                diasParaViajar = 9,
                criterio = relajado,
                tipoDeUsuario = neofilo,
                mail = "usuario@gmail.com"
            )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 5010.0)

        val repoAmigos = RepositorioDeUsuario()
        repoAmigos.crear(usuarioLocal2)
        repoAmigos.crear(usuarioLocal3)
        repoAmigos.crear(usuarioLocal4)
        val hacerAmigo = HacerAmigo(usuarioLocal1, repoAmigos, destino1, "hacerAmigo").apply {
            mailSender = mockedMailSender
        }


        it("Hacer amigos de usuarios que conozcan el destino especifico ") {
            usuarioLocal2.agregarDestinosVisitados(destino1)
            usuarioLocal3.agregarDestinosVisitados(destino1)
            hacerAmigo.realizarTarea()
            usuarioLocal1.amigos.contains(usuarioLocal2) shouldBe true
            usuarioLocal1.amigos.contains(usuarioLocal3) shouldBe true
            usuarioLocal1.amigos.contains(usuarioLocal4) shouldBe false

        }
        it("Verifico que al realizar la tarea recibe un mail ") {
            usuarioLocal2.agregarDestinosVisitados(destino1)
            usuarioLocal3.agregarDestinosVisitados(destino1)
            hacerAmigo.realizarTarea()
            verify(exactly = 1) {
                mockedMailSender.enviarMail(
                    mail = Mail(
                        from = "hacerAmigo",
                        to = "  " + usuarioLocal1.mail,
                        subject = "Se realizo tarea: hacerAmigo",
                        cuerpo = "Se realizo tarea: hacerAmigo"
                    )
                )
            }
        }
    }

    describe("Test de Tarea Puntuar Itinerarios ") {

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
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 5010.0)
        val destino2 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 4000.0)


        val itinerario1 = Itinerario(usuarioLocal2, mutableListOf(), destino2)
        val itinerario2 = Itinerario(usuarioLocal2, mutableListOf(), destino1)
        val itinerario3 = Itinerario(usuarioLocal2, mutableListOf(), destino2)

        val puntuarItinerario =
            PuntuarItinerario(usuarioLocal1, 8, nombre = "puntuarItinerario").apply {
                mailSender = mockedMailSender
            }


        it("Se testea que las puntuaciones itinerarios esta vacio") {
            itinerario1.puntuaciones.size shouldBe 0
            itinerario2.puntuaciones.size shouldBe 0
            itinerario3.puntuaciones.size shouldBe 0
        }
        it("Se punteo los itinerarios 1 y 2 ") {
            // agrego los itinerarios 1 y 2 a la lista de itinerarios a puntuar
            usuarioLocal1.itinerariosAPuntuar.add(itinerario1)
            usuarioLocal1.itinerariosAPuntuar.add(itinerario2)
            // Agrego que conoce el destino para que pueda puntuar
            usuarioLocal1.agregarDestinosDeseados(destino1)
            usuarioLocal1.agregarDestinosDeseados(destino2)
            // Realizo la tarea puntuar itinerarios
            puntuarItinerario.realizarTarea()
            //verifico que se puntuo los itinerarios 1 y 2
            println(itinerario1.puntuaciones)
            println(itinerario2.puntuaciones)
            itinerario1.puntuaciones.size shouldBe 1
            itinerario2.puntuaciones.size shouldBe 1
        }

        it("Se testea que despues de puntuar los itinerarios queda vacia la lista de itineraios a puntuar") {
            usuarioLocal1.itinerariosAPuntuar.add(itinerario1)
            usuarioLocal1.itinerariosAPuntuar.add(itinerario2)
            puntuarItinerario.realizarTarea()
            usuarioLocal1.itinerariosAPuntuar.size shouldBe 0
        }

        it("Verifico que al realizar la tarea recibe un mail ") {
            usuarioLocal1.itinerariosAPuntuar.add(itinerario1)
            usuarioLocal1.itinerariosAPuntuar.add(itinerario2)
            puntuarItinerario.realizarTarea()
            verify(exactly = 1) {
                mockedMailSender.enviarMail(
                    mail = Mail(
                        from = "puntuarItinerario",
                        to = "  " + usuarioLocal1.mail,
                        subject = "Se realizo tarea: puntuarItinerario",
                        cuerpo = "Se realizo tarea: puntuarItinerario"
                    )
                )
            }
        }


    }


    describe("Test de Tarea agregar destinos a amigo con pocos destinos deseados") {

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
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal3 = Usuario(
            nombre = "Alex",
            apellido = "pepe",
            username = "Ney",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val destino2 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 4000.0)
        val destino3 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
        val destino4 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 10000.0)
        val destino5 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 40000.0)

        //agrego a los usuarios destinos deseados
        usuarioLocal2.agregarDestinosDeseados(destino2)
        usuarioLocal2.agregarDestinosDeseados(destino3)
        usuarioLocal3.agregarDestinosDeseados(destino4)
        usuarioLocal3.agregarDestinosDeseados(destino5)
        //agrego al usuariolocal1 como amigos al 2 y 3
        usuarioLocal1.agregarAmigo(usuarioLocal2)
        usuarioLocal1.agregarAmigo(usuarioLocal3)

        val agregarDestinosDeseado = AgregarDestinosDeseado(usuarioLocal1, "agregarDestinosDeseado").apply {
            mailSender = mockedMailSender
        }

        it("Realizo tarea de agregar destinos deseado mas caro de amigos ") {
            agregarDestinosDeseado.realizarTarea()
            // Verifico que se agrego los destinos deseados mas caro de los amigos
            usuarioLocal1.destinosDeseados.contains(destino3) shouldBe true
            usuarioLocal1.destinosDeseados.contains(destino5) shouldBe true

        }

        it("Verifico que al realizar la tarea recibe un mail ") {
            agregarDestinosDeseado.realizarTarea()
            verify(exactly = 1) {
                mockedMailSender.enviarMail(
                    mail = Mail(
                        from = "agregarDestinosDeseado",
                        to = "  " + usuarioLocal1.mail,
                        subject = "Se realizo tarea: agregarDestinosDeseado",
                        cuerpo = "Se realizo tarea: agregarDestinosDeseado"
                    )
                )
            }
        }


    }


    describe("Test de Tarea transeferir itinerarios") {

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
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal3 = Usuario(
            nombre = "Alex",
            apellido = "pepe",
            username = "Ney",
            paisDeResidencia = "Argentina",
            diasParaViajar = 10,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal4 =
            Usuario(
                "juan",
                "lopez",
                "juan12",
                "Chile",
                diasParaViajar = 9,
                criterio = relajado,
                tipoDeUsuario = neofilo,
                mail = "usuario@gmail.com"
            )

        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 501.0)
        val destino2 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 4000.0)
        val destino3 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
        val destino4 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 10000.0)
        val destino5 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 40000.0)
        val itinerario = Itinerario(usuarioLocal1, mutableListOf(), destino1)
        val itinerari2 = Itinerario(usuarioLocal1, mutableListOf(), destino2)
        val itinerari3 = Itinerario(usuarioLocal1, mutableListOf(), destino3)
        val tareaTransfeririItinerario = TransferirItinerario(usuarioLocal1, "tareaTransfeririItinerario").apply {
            mailSender = mockedMailSender
        }

        usuarioLocal1.amigos.addAll(listOf(usuarioLocal2, usuarioLocal3, usuarioLocal4))
        usuarioLocal1.itinerariosAPuntuar.add(itinerario)
        usuarioLocal2.destinosVisitados.addAll(listOf(destino1, destino2, destino3))
        usuarioLocal3.destinosVisitados.addAll(listOf(destino1, destino4))
        usuarioLocal4.destinosVisitados.addAll(listOf(destino5))
        it("EL usuarioLocal1 transfiere su itinerario al amigo con menos destinos visitados tenga") {
            tareaTransfeririItinerario.realizarTarea()
            usuarioLocal2.itinerariosAPuntuar.contains(itinerario) shouldBe false
            usuarioLocal3.itinerariosAPuntuar.contains(itinerario) shouldBe false
            usuarioLocal4.itinerariosAPuntuar.contains(itinerario) shouldBe true
        }
        it("El usuarioLocal1 transfiere todos sus itinerario al amigo con menos destinos visitados tenga") {
            usuarioLocal1.itinerariosAPuntuar.addAll(listOf(itinerari2, itinerari3))
            tareaTransfeririItinerario.realizarTarea()
            usuarioLocal2.itinerariosAPuntuar.containsAll(listOf(itinerari2, itinerari3)) shouldBe false
            usuarioLocal3.itinerariosAPuntuar.containsAll(listOf(itinerari2, itinerari3)) shouldBe false
            usuarioLocal4.itinerariosAPuntuar.containsAll(listOf(itinerario, itinerari2, itinerari3)) shouldBe true

        }


    }



})


fun mockedMailSender(): MailSender = mockk<MailSender>(relaxUnitFun = true)