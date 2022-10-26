import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalTime

class UsuarioTest : DescribeSpec({
    val relajado = Relajado()
    val exigente = Exigente(dificultad = Dificultad.ALTA, porcentaje = 50.0)
    val neofilo = Neofilo()
    describe("Test de usuarios entrega 0 ") {

        val usuarioLocal2 = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 9,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioViajeNoLocalSiResidente =
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
        it("Costo de un viaje no local con mismo pais de residencia que el destino con antiguedad de 10 Años") {
            val usuarioViajeNoLocalSiResidente =
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
            val viajeNoLocalSiResidente = Destino("Chile", "Santiago", 30000.0)
            usuarioViajeNoLocalSiResidente.fechaDeAlta = LocalDate.of(2012, 2, 4)
            viajeNoLocalSiResidente.precioTotal(usuarioViajeNoLocalSiResidente).shouldBe(33000.0)
        }
        it("Usuario nuevo residente con viaje local") {


            val usuarioViajeNoLocalSiResidente =
                Usuario(
                    "dario",
                    "sanchez",
                    "dsh89",
                    "Argentina",
                    diasParaViajar = 5,
                    criterio = relajado,
                    tipoDeUsuario = neofilo,
                    mail = "usuario@gmail.com"
                )
            val viajeLocalResidente = Destino("Argentina", "Buenos Aires", 20000.0)
            viajeLocalResidente.precioTotal(usuarioViajeNoLocalSiResidente).shouldBe(20000.0)
        }


        it("Costo de un viaje local e Internacional con Usuario con antiguedad mayor a 15 años ") {
            val viajeNoLocal = Destino(pais = "Brasil", ciudad = "Rio de Janeiro", costoBase = 36000.0)
            val viajeLocal = Destino(pais = "argentina", ciudad = "buenos aires", costoBase = 10000.0)
            usuarioLocal2.fechaDeAlta = LocalDate.of(2000, 2, 4)
            viajeLocal.precioTotal(usuarioLocal2).shouldBe(12000.0)
            viajeNoLocal.precioTotal(usuarioLocal2).shouldBe(43200.0)
        }
    }
    describe("TEST DE USUARIO ENTREGA 2") {
        val neofilo = Neofilo()
        val usuarioLocal = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal2 = Usuario(
            nombre = "Pedro",
            apellido = "pepe",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 1,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 5010.0)
        val destino2 = Destino("Argentina", ciudad = "Buenos Aires", costoBase = 4000.0)
        val actividad1 = Actividad(
            LocalTime.of(19, 0, 0),
            LocalTime.of(21, 0, 0), Dificultad.BAJA,
            3000.0, "esta actividad es muy divertida"
        )
        val actividad2 = Actividad(
            LocalTime.of(21, 0, 0),
            LocalTime.of(23, 0, 0), Dificultad.BAJA,
            2000.0, "esta actividad es graciosa"
        )
        val actividad3 = Actividad(
            LocalTime.of(10, 0, 0),
            LocalTime.of(12, 0, 0), Dificultad.ALTA,
            1000.0, "esta actividad es agotadora"
        )
        val actividad4 = Actividad(
            LocalTime.of(6, 0, 0),
            LocalTime.of(12, 0, 0), Dificultad.ALTA,
            5000.0, "esta actividad es relajante"

        )

        val dia1Iti1 = DiaDeItinerario(mutableListOf(actividad1, actividad2))
        val dia2Iti1 = DiaDeItinerario(mutableListOf(actividad3))
        val dia1Iti2 = DiaDeItinerario(mutableListOf(actividad3))
        val dia2Iti2 = DiaDeItinerario(mutableListOf(actividad4, actividad2))
        val dia1Iti3 = DiaDeItinerario(mutableListOf())
        val itinerario1 = Itinerario(usuarioLocal, mutableListOf(dia1Iti1, dia2Iti1), destino2)
        val itinerario2 = Itinerario(usuarioLocal2, mutableListOf(dia1Iti2, dia2Iti2), destino1)
        val itinerario3 = Itinerario(usuarioLocal2, mutableListOf(dia1Iti3), destino2)
        it("usuario conoce un destino1 pero no conoce destino dos ya que solo desea ir al destino1") {
            usuarioLocal.destinosDeseados.add(destino1)
            usuarioLocal.conoceDestino(destino1).shouldBe(true)
            usuarioLocal.conoceDestino(destino2).shouldBe(false)
        }
        it("el usuarioLocal puede no puntuar itinerario ya que es el creador mientras que el usuarioLocal2 si ya que ademas conoce el destino ") {
            usuarioLocal.puedePuntuarItineraio(itinerario1).shouldBe(false)
            usuarioLocal2.destinosVisitados.add(destino2)
            usuarioLocal2.puedePuntuarItineraio(itinerario1).shouldBe(true)
        }
        it("usuariolocal1 puede realizar un itinerario mientras que usuariolocal2 no porque no tiene suficiente tiempo para viajar") {
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(true)
            usuarioLocal2.puedeRealizarItinerario(itinerario1).shouldBe(false)
        }
        it("usuariolocal 1 cambia su criterio y no puede modificar itinerario") {
            usuarioLocal.cambiarCriterio(exigente)
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(false)
        }
        it("usuariolocal es valido mientras que usuariolocal2 no porque no tiene destinos deseados") {
            usuarioLocal.destinosDeseados.add(destino1)
            usuarioLocal.esValido().shouldBe(true)
            usuarioLocal2.esValido().shouldBe(false)
        }
        it("usuariolocal1 no puede modificar un itinerario porque no es el creador") {
            usuarioLocal.puedeModificarItinerario(itinerario2).shouldBe(false)
        }
        it("usuariolocal2 puede modificar un itinerario porque es amigo creador y conoce el destino") {
            usuarioLocal2.destinosDeseados.add(destino2)
            usuarioLocal.amigos.add(usuarioLocal2)
            usuarioLocal2.puedeModificarItinerario(itinerario1).shouldBe(true)
        }

    }

})
