import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalTime

class ActividadTest : DescribeSpec({
    describe("testeo de actuvidades") {
        val actividadValida = Actividad(LocalTime.of(20, 0, 0),
            LocalTime.of(22, 0, 0), Dificultad.BAJA, descripcionDeActividad =  "esta actividad es muy divertida"
        )
        val actividadNoValida = Actividad(LocalTime.of(11, 0, 0),
            LocalTime.of(13, 0, 0), Dificultad.BAJA,
            3000.0, ""
        )
        it("duracion de la actividad") {
            actividadValida.duracion().shouldBe(120)
        }
        it("actividad es valida") {
            actividadValida.esValido().shouldBe(true)
        }
        it("actividad es invalida") {
            actividadNoValida.esValido().shouldBe(false)
        }
    }


})
