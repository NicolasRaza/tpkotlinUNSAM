import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class RepositorioDestino : DescribeSpec({

    describe("TEST UNITARIO DE ACTUALIZAR DESTINO CON STUB AND MOCKK"){

        val destino1 = Destino(pais = "Argenti",ciudad = "Cordoba", costoBase = 2000.0)
        val destino2 = Destino(pais = "Argentina",ciudad = "Mar del plata", costoBase = 3000.0)
        val respositorioDeDestino = RepositorioDeDestino()
        respositorioDeDestino.crear(contenido = destino1) //id1
        respositorioDeDestino.crear(contenido = destino2) //id2
        Updater.service = stubService()
        it("recibo un json y actualizo mi repositorio de destino , "){
            respositorioDeDestino.buscarPorId(1).esLocal()shouldBe  false
            respositorioDeDestino.buscarPorId(1).costoBase.equals(1500.0) shouldBe false
            Updater.repositorio = respositorioDeDestino
            Updater.actualizar()
            respositorioDeDestino.buscarPorId(1).esLocal()shouldBe true
            respositorioDeDestino.buscarPorId(1).costoBase.equals(1500.0) shouldBe true
        }
    }
})
val json  = "{id: 1, pais : \"Argentina\", ciudad = \"Cordoba\", costoBase = 3000.0 }" +
            "{id: 2 ,pais : \"Argentina\", ciudad = \"Mar del plata\", costoBase = 1500.0 } "
//stub para verificiar si un test de actualizar con json de Respositorio destino

fun stubService() : ServiceDestino {
    Updater.service = mockk<ServiceDestino>(relaxUnitFun = true)
    every { Updater.service.getDestinos() } answers {json}
    return Updater.service
}







