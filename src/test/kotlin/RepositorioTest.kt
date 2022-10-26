import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalTime

class RepositorioTest : DescribeSpec({
    describe("TEST DE RESPOSITORIO DE DESTINO CON ACTUALIZAR"){
        val destino1 = Destino("Argentina", "Mar del Plata", 3000.0)
        val destino2 = Destino("Argentina", "buenos aires", 2000.0)
        val respositorioDeDestino = RepositorioDeDestino()
        respositorioDeDestino.crear(destino1) //id = 1
        respositorioDeDestino.crear(destino2) //  id = 2
    }


    describe("TESdT DE REPOSITORIOS con destinos ") {
        val destino1 = Destino("Argentina", "cordoba", 3000.0)
        val destino2 = Destino("Argentina", "buenos aires", 2000.0)
        val repo1 = RepositorioDeDestino()
        repo1.crear(destino1)
        repo1.crear(destino2)
        it("Agregar Y remover destino a la lista de repositorios de destinos") {
            repo1.borrar(destino1)
            repo1.repositorio.contains(destino1).shouldBe(false)

        }
        it("buscar el destino1 con el ID 2") {
            destino2.id.shouldBe(2)
            repo1.buscarPorId(2).shouldBe(destino2)
        }
        /*it("el id del destino no existe asi que lanza una exception "){
            repo1.buscarPorId(32)
        }*/
        it("actualizar lista de destinos con otro ciudad ") {
            repo1.buscarPorId(2).ciudad.shouldBe("buenos aires")
            destino2.ciudad = "cordoba"
            //destino2.id = 5
            repo1.actualizar(destino2)
            repo1.buscarPorId(2).ciudad.shouldBe("cordoba")

        }
        it("buscar destino con la ciudad cordoba asi que devuelve Destino1") {

            repo1.buscarPorContenido("cordo").shouldBe(listOf(destino2))
        }
        it("buscar destino que coincide parcialmente con el pais Argentina asi que devuvelve ambos destinos") {
            repo1.buscarPorContenido("Argentin").shouldBe(listOf( destino2))
        }
    }
    describe("test de repositorios de vehiculos") {
        var vehiculoHonda = Auto(
            marca = "Honda",
            modelo = "model1",
            añoDeFabricacion = 2010,
            costoDiario = 1000.0,
            kilometrajeLibre = true,
            hatchback = true,
        )
        var vehiculoChebrolet = Auto(
            marca = "Chebrolet",
            modelo = "Civic",
            añoDeFabricacion = 2010,
            costoDiario = 1000.0,
            kilometrajeLibre = true,
            hatchback = true
        )
        val repo = RepositorioDeVehiculo()
        repo.crear(vehiculoHonda)
        repo.crear(vehiculoChebrolet)
        it("buscar un vehiculo con la marca Honda devuelve el vehiculoHonda") {
            repo.buscarPorContenido("Honda").shouldBe(listOf(vehiculoHonda))
        }
        it("buscar un vehiculo honda sin que coincida espesificamente devuelve Nada ") {
            repo.buscarPorContenido("Hon").shouldBe(listOf())
        }
        it("buscar vehiculo que empieza con la el modelo Civic") {
            repo.buscarPorContenido("Civ").shouldBe(listOf(vehiculoChebrolet))
        }
    }
    describe("test de repositorios de usuarios"){
        val relajado = Relajado()
        val neofilo = Neofilo()

        val usuarioJuan = Usuario(
            nombre = "Juan",
            apellido = "Lopez",
            username = "pepeto",
            paisDeResidencia = "Argentina",
            diasParaViajar = 1,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"

        )
        val repo = RepositorioDeUsuario()
        repo.crear(usuarioJuan)
        it("busqueda de un usuario con su nombre parcialmente parecido a juan devuelve usuarioJuan "){
                repo.buscarPorContenido("jua").shouldBe(listOf(usuarioJuan))
        }
        it("busqueda de un usuario con su apellido  parcialmente parecido a lopez devuelve usuarioJuan" ){
            repo.buscarPorContenido("lope").shouldBe(listOf(usuarioJuan))
        }
        it("busqueda de un usuario con su usurname exactamente igual a pepeto devuelve usuarioJuan"){
            repo.buscarPorContenido("pepeto").shouldBe(listOf(usuarioJuan))
        }
        it("busqueda de un usuario con su usurname que no es exactamente igual a pepeto devuelve una lista vacia"){
            repo.buscarPorContenido("pepe").shouldBe(listOf())
        }

    }
    describe("test de repositorios de Itinerarios"){
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
        val destino1 = Destino("Argentina", ciudad = "Chubut", costoBase = 5010.0)
        val dia1iti1 = DiaDeItinerario(mutableListOf(actividad1,actividad2))
        val itinerario = Itinerario(usuarioLocal, mutableListOf(dia1iti1), destino1)
        val repo = RepositorioDeItinerario()
        repo.crear(itinerario)
        it("se busca un itinerario con el pais que coincide parcialmente con lo ingresado "){
            repo.buscarPorContenido("Arg").shouldBe(listOf(itinerario))
        }
        it("se busca un itinerario con el ciudad que coincide parcialmente con lo ingresado "){
            repo.buscarPorContenido("Chub").shouldBe(listOf(itinerario))
        }
        it("se busca un itinerario con una actividad que coincide parcialmente con lo ingresado "){
            repo.buscarPorContenido("activid").shouldBe(listOf(itinerario))
        }
    }
})