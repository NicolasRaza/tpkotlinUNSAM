import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalTime

class DiaItinerarioTest :  DescribeSpec({
    describe("testeo de dia de activadades") {

        val dia1Itinerario1 = DiaDeItinerario()
        val actividad1 = Actividad(
             LocalTime.of(10, 30, 0),
            LocalTime.of(11, 30, 30), Dificultad.BAJA,
            3000.0, "esta actividad es muy divertida"
        )
        val actividad2 = Actividad(
             LocalTime.of(12, 0, 0),
            LocalTime.of(13, 0, 0), Dificultad.BAJA,
            2000.0, "esta actividad es graciosa"
        )
        val actividad3 = Actividad(
             LocalTime.of(15, 0, 0),
            LocalTime.of(16, 0, 0), Dificultad.ALTA,
            1000.0, "esta actividad es agotadora"
        )
        val actividad4 = Actividad(
             LocalTime.of(10, 30, 0),
            LocalTime.of(11, 30, 0), Dificultad.ALTA,
            1000.0, "esta actividad es agotadora"
        )
        dia1Itinerario1.agregarActividad(actividad1)
        it("Se testea agregar actividades") {
            dia1Itinerario1.listaDeActividades.contains(actividad1).shouldBe(true)
        }
        it("Se testea quitar actividades") {
            dia1Itinerario1.listaDeActividades.contains(actividad1).shouldBe(true)
            dia1Itinerario1.quitarActividad(actividad1)
            dia1Itinerario1.listaDeActividades.contains(actividad1).shouldBe(false)
        }}
        describe("testeo de actuvidades") {
            val dia1Itinerario1 = DiaDeItinerario()
            val actividad1 = Actividad(
                 LocalTime.of(10, 30, 0),
                LocalTime.of(11, 30, 30), Dificultad.BAJA,
                3000.0, "esta actividad es muy divertida"
            )
            val actividad2 = Actividad(
                 LocalTime.of(12, 0, 0),
                LocalTime.of(13, 0, 0), Dificultad.BAJA,
                2000.0, "esta actividad es graciosa"
            )
            val actividad3 = Actividad(
                 LocalTime.of(15, 0, 0),
                LocalTime.of(16, 0, 0), Dificultad.ALTA,
                1000.0, "esta actividad es agotadora"
            )
            val actividad4 = Actividad(
                 LocalTime.of(10, 30, 0),
                LocalTime.of(11, 30, 0), Dificultad.ALTA,
                1000.0, "esta actividad es agotadora"
            )

        it("Se testea duracion del dia de actividades") {
            dia1Itinerario1.agregarActividad(actividad2)
            dia1Itinerario1.agregarActividad(actividad3)
            dia1Itinerario1.duracionDeActividadDelDia().shouldBe(120)
        }
        it("Se testea costo de activadad actividades") {
            dia1Itinerario1.agregarActividad(actividad2)
            dia1Itinerario1.agregarActividad(actividad3)
            dia1Itinerario1.costoActividadPorDia().shouldBe(6000.0)
        }}

        describe("testeo de actuvidades 4") {
                val dia1Itinerario1 = DiaDeItinerario()
                val actividad1 = Actividad(
                     LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 30), Dificultad.BAJA,
                    3000.0, "esta actividad es muy divertida"
                )
                val actividad2 = Actividad(
                     LocalTime.of(12, 0, 0),
                    LocalTime.of(13, 0, 0), Dificultad.BAJA,
                    2000.0, "esta actividad es graciosa"
                )
                val actividad3 = Actividad(
                     LocalTime.of(15, 0, 0),
                    LocalTime.of(16, 0, 0), Dificultad.ALTA,
                    1000.0, "esta actividad es agotadora"
                )
                val actividad4 = Actividad(
                     LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0), Dificultad.ALTA,
                    1000.0, "esta actividad es agotadora"
                )
        it("se testee cantidadDeActividades") {
            dia1Itinerario1.cantidadDeActividades().shouldBe(0)
            dia1Itinerario1.agregarActividad(actividad2)
            dia1Itinerario1.agregarActividad(actividad3)
            dia1Itinerario1.cantidadDeActividades().shouldBe(2)
        }
        it("se testee hay activadades") {
            //acordate jeje
            dia1Itinerario1.hayActividades().shouldBe(true)
            dia1Itinerario1.agregarActividad(actividad1)
            dia1Itinerario1.hayActividades().shouldBe(true)
        }
        it("se testea hayActividadSolapadas") {
            dia1Itinerario1.agregarActividad(actividad1)
            //dia1Itinerario1.hayActividadSolapadas().shouldBe(false)
            dia1Itinerario1.agregarActividad(actividad2)
            dia1Itinerario1.agregarActividad(actividad4)
            dia1Itinerario1.hayActividadSolapadas().shouldBe(true)

        }

        //


    }})