import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalTime

class ItinerarioTest : DescribeSpec({
    describe("Test de itinerarios") {
        val neofilo= Neofilo()
        val relajado = Relajado()
        val usuarioLocal = Usuario(
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
        val actividad1 = Actividad(
             LocalTime.of(19, 0, 0),
            LocalTime.of(21, 0, 0), Dificultad.BAJA,
            3000.0, "esta actividad es muy divertida"
        )
        val actividad2 = Actividad(
             LocalTime.of(22, 0, 0),
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

        val dia1Iti1 = DiaDeItinerario( mutableListOf(actividad1, actividad2))
        val dia2Iti1 = DiaDeItinerario( mutableListOf(actividad3))
        val dia1Iti2 = DiaDeItinerario( mutableListOf(actividad3,actividad4))
        val dia2Iti2 = DiaDeItinerario( mutableListOf(actividad1, actividad2))
        val dia1Iti3 = DiaDeItinerario( mutableListOf())
        val itinerarioDificultadBaja = Itinerario(usuarioLocal, mutableListOf(dia1Iti1, dia2Iti1), destino2)
        val itinerarioDificultadAlta = Itinerario(usuarioLocal2, mutableListOf(dia1Iti2, dia2Iti2), destino1)
        val itinerarioInvalido = Itinerario(usuarioLocal2, mutableListOf(dia1Iti3), destino2)

        it("dificultad de un itinerario con 2 act bajas y 1 alta") {
            itinerarioDificultadBaja.dificultad().shouldBe(Dificultad.BAJA)
        }
        it("dificultad de un itinerario con 2 act bajas y 2 altas ") {
            itinerarioDificultadAlta.dificultad().shouldBe(Dificultad.ALTA)
        }
        it("Itinerario No es Valido porque la no tiene actividades  ") {
            itinerarioInvalido.esValido().shouldBe(false)
        }
        it("Itinerario es Valido") {
            itinerarioDificultadBaja.esValido().shouldBe(true)
        }
        it("Puntear itinerario 2 veces por el mismo usuario") {
            usuarioLocal.agregarDestinosVisitados(destino1)
            usuarioLocal.puntuarItinerario(itinerarioDificultadAlta, 10)
            usuarioLocal.puedePuntuarItineraio(itinerarioDificultadAlta).shouldBe(false)
        }


    }


})
