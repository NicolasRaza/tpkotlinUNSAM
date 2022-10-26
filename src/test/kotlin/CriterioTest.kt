import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalTime

class CriterioTest : DescribeSpec({
    describe("Test Criterios") {
        val neofilo = Neofilo()
        val relajado = Relajado()
        val precavido = Precavido()
        val localista = Localista()
        val soñador = Soñador()
        val activo = Activo()
        val exigente = Exigente(5.0, Dificultad.ALTA)


        val usuarioLocal = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 3,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val usuarioLocal2 = Usuario(
            nombre = "Guido",
            apellido = "Bobis",
            username = "ponelarepe",
            paisDeResidencia = "Brasil",
            diasParaViajar = 3,
            criterio = soñador,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"

        )
        val actividad1 = Actividad(
            LocalTime.of(1, 0, 0),
            LocalTime.of(5, 0, 0), Dificultad.BAJA,
            3000.0, "esta actividad es muy divertida"
        )
        val actividad2 = Actividad(
            LocalTime.of(7, 0, 0),
            LocalTime.of(10, 0, 0), Dificultad.ALTA,
            2000.0, "esta actividad es graciosa"
        )
        val actividad3 = Actividad(
            LocalTime.of(12, 0, 0),
            LocalTime.of(15, 10, 0), Dificultad.ALTA,
            1000.0, "esta actividad es agotadora"
        )
        val actividad4 = Actividad(
            LocalTime.of(18, 0, 0),
            LocalTime.of(20, 0), Dificultad.ALTA,
            5000.0, "esta actividad es relajante"

        )

        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 5010.0)
        val destino2 = Destino("Brasil", ciudad = "Rio de Janeiro", costoBase = 8300.0)
        val destino3 = Destino("Uruguay", ciudad = "Montevideo", costoBase = 10000.0)
        val destino4 = Destino("Chile", ciudad = "Santiago", costoBase = 13666.0)
        val dia4 = DiaDeItinerario(mutableListOf(actividad1, actividad2, actividad3, actividad4))
        val dia5 = DiaDeItinerario(mutableListOf(actividad1, actividad2, actividad3, actividad4))
        val dia6 = DiaDeItinerario(mutableListOf(actividad1, actividad3))
        val diaSinActividad = DiaDeItinerario(mutableListOf())
        val itinerario1 = Itinerario(usuarioLocal, mutableListOf(), destino1)
        val itinerario2 = Itinerario(usuarioLocal2, mutableListOf(), destino2)
        val itinerario3 = Itinerario(usuarioLocal2, mutableListOf(dia4), destino3)
        val itinerario4 = Itinerario(usuarioLocal2, mutableListOf(dia5), destino4)
        val itinerario5 = Itinerario(usuarioLocal2, mutableListOf(dia6), destino2)
        val itinerario6 = Itinerario(usuarioLocal2, mutableListOf(dia4, dia5, dia6), destino2)
        val itinerario7 = Itinerario(usuarioLocal2, mutableListOf(diaSinActividad, dia5, dia6), destino2)

        it("usuario con criterio relajo cualquier itinerario le viene bien") {
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(true)
        }
        it("usuario con criterio precavido conoce el destino") {
            usuarioLocal.cambiarCriterio(precavido)
            usuarioLocal.agregarDestinosVisitados(destino1)
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(true)
        }
        it("usuario con criterio precavido no conoce el destino") {
            usuarioLocal.cambiarCriterio(precavido)
            usuarioLocal.agregarDestinosVisitados(destino1)
            usuarioLocal.puedeRealizarItinerario(itinerario2).shouldBe(false)
        }
        it("usuario con criterio localista ") {
            usuarioLocal.cambiarCriterio(localista)
            usuarioLocal.puedeRealizarItinerario(itinerario2).shouldBe(false)
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(true)
        }
        it("usuario con criterio soñador") {
            usuarioLocal.cambiarCriterio(soñador)
            usuarioLocal.agregarDestinosDeseados(destino3)
            //testeo con destino deseados
            usuarioLocal.puedeRealizarItinerario(itinerario3).shouldBe(true)
            usuarioLocal.puedeRealizarItinerario(itinerario1).shouldBe(false)
            //testeo con destino deseado mas caro
            usuarioLocal.puedeRealizarItinerario(itinerario4).shouldBe(true)
        }

        it("usuario con criterio activo") {
            usuarioLocal.cambiarCriterio(activo)
            //itinerario6 tiene activades todos los dias
            usuarioLocal.puedeRealizarItinerario(itinerario6).shouldBe(true)
            //itinerario7 tiene 3 dias , 2 con actividades y 1 sin actividad
            usuarioLocal.puedeRealizarItinerario(itinerario7).shouldBe(false)
        }

        it("usuario exigente") {
            usuarioLocal.cambiarCriterio(exigente)
            itinerario5.listaDeActividades().contains(actividad1).shouldBe(true)
            itinerario5.listaDeActividades().contains(actividad3).shouldBe(true)
            usuarioLocal.puedeRealizarItinerario(itinerario5).shouldBe(true)
            dia6.quitarActividad(actividad1)
            dia6.agregarActividad(actividad2)
            usuarioLocal.puedeRealizarItinerario(itinerario5).shouldBe(true)


        }

    }

})
