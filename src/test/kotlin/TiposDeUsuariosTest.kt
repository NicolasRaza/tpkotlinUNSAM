import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TiposDeUsuariosTest : DescribeSpec({
    //INSTANCIA DE OBJETOS PARA TODOS LOS TES TE TIPO DE USUARIOS
    val relajado = Relajado()
    describe("Tests neofilo") {
        val neofilo = Neofilo()
        val usuarioNeofilo = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = neofilo,
            mail = "usuario@gmail.com"
        )
        val autoNuevo = Auto(
            marca = "Honda",
            modelo = "Civic",
            añoDeFabricacion = 2021,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val autoViejo = Auto(
            marca = "Honda",
            modelo = "Civic",
            añoDeFabricacion = 2019,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("tipo de usuario es neofilo con un auto de 1 año de antiguedad , se entiende que deberia dar vverdadero que le gusta el auto") {
            usuarioNeofilo.leGustaVehiculo(autoNuevo).shouldBe(true)
        }
        it("tipo de usuario es neofilo con un auto de mas 2 años de antiguedad, se entiende que no deberia gustarle el auto") {
            usuarioNeofilo.leGustaVehiculo(autoViejo).shouldBe(false)
        }
    }//out describe

    describe("TEST Supersticioso") {
        val supersticioso = Supersticioso()
        val usuarioSupersticioso = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = supersticioso,
            mail = "usuario@gmail.com"
        )

        val autoAñoPar = Auto(
            marca = "Honda",
            modelo = "Civic",
            añoDeFabricacion = 2018,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val autoAñoImpar = Auto(
            marca = "Honda",
            modelo = "Civic",
            añoDeFabricacion = 2019,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("Tipo de usuario es Supersticioso con un auto de de año de fabricacion en 2018 deberia gustarle el auto") {
            usuarioSupersticioso.leGustaVehiculo(autoAñoPar).shouldBe(true)
        }

        it("Tipo de usuario Supersticioso con un auto de año de Fabricacion en 2019 no deberia gustarle el auto") {
            usuarioSupersticioso.leGustaVehiculo(autoAñoImpar).shouldBe(false)
        }

    }//out describe Supersticioso

    describe("Test tipo de usuario Caprichoso") {
        val caprichoso = Caprichoso()
        val usuarioCaprichoso = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = caprichoso,
            mail = "usuario@gmail.com"
        )
        val autoModeloHrv = Auto(
            marca = "Honda",
            modelo = "Hrv",//este es el unico dato que importa
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val autoModeloCivic = Auto(
            marca = "Honda",
            modelo = "Civic",//este es el unico dato que importa
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("Tipo de Usuario Caprichoso con un auto Honda Modelo HRV deberia gustarle el auto") {
            usuarioCaprichoso.leGustaVehiculo(autoModeloHrv).shouldBe(true)
        }
        it("Tipo de Usuario Caprichoso con un auto Honda Modelo Civic deberia gustarle el auto") {
            usuarioCaprichoso.leGustaVehiculo(autoModeloCivic).shouldBe(false)
        }
    }//out describe Caprichoso

    describe("Test Selectivo") {
        val selectivoHonda = Selectivo("honda")
        val selectivoFiat = Selectivo("Fiat")
        val usuarioSelectivo = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = selectivoHonda,
            mail = "usuario@gmail.com"
        )
        val autoHonda = Auto(
            marca = "Honda",//este es el unico dato que importa
            modelo = "Hrv",
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val autoFiat = Auto(
            marca = "Fiat",//este es el unico dato que importa
            modelo = "Punto",
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("Usuario Selectivo que le gusta honda con un auto honda deberia gustarle el auto") {
            usuarioSelectivo.leGustaVehiculo(autoHonda).shouldBe(true)
        }
        it("Usuario Selectivo que le gusta fiat con un auto honda NO deberia gustarle el auto") {
            usuarioSelectivo.leGustaVehiculo(autoFiat).shouldBe(false)
        }

    }// out describe Selectivo

    describe("Test Sin Limite") {
        val sinLimite = SinLimite()
        val usuarioSinLimite = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = sinLimite,
            mail = "usuario@gmail.com"
        )
        val autoConKilometrajeLibre = Auto(
            marca = "Fiat",
            modelo = "Punto",
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = true,//este es el unico dato que importa
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val autoSinKilometrajeLibre = Auto(
            marca = "Fiat",
            modelo = "Punto",
            añoDeFabricacion = 2019,
            costoDiario = 300.0,
            kilometrajeLibre = false,//este es el unico dato que importa
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("usuario Sin limite con un auto con kilomentraje libre deberia gustarle el vehiculo") {
            usuarioSinLimite.leGustaVehiculo(autoConKilometrajeLibre).shouldBe(true)
        }
        it("usuario Sin limite con un auto sin kilomentraje libre deberia gustarle el vehiculo") {
            usuarioSinLimite.leGustaVehiculo(autoSinKilometrajeLibre).shouldBe(false)
        }
    }//out Describe SinLimite

    describe("Test Combinado") {
        val neofilo = Neofilo()
        val supersticioso = Supersticioso()
        val combinado= Combinado(setOf(neofilo,supersticioso))
        val usuarioCombinado = Usuario(
            nombre = "Martin",
            apellido = "pepita",
            username = "pepita21",
            paisDeResidencia = "Argentina",
            diasParaViajar = 5,
            criterio = relajado,
            tipoDeUsuario = combinado,
            mail = "usuario@gmail.com"
        )
        val auto = Auto(
            marca = "Fiat",
            modelo = "Punto",
            añoDeFabricacion = 2022,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        val auto1 = Auto(
            marca = "Fiat",
            modelo = "Punto",
            añoDeFabricacion = 2020,//este es el unico dato que importa
            costoDiario = 300.0,
            kilometrajeLibre = true,
            hatchback = true
            //lista de convenios no es necesario completarlo para este test
        )
        it("Usuario combinado que es neofilo y supersticioso,como el auto es del 2022 deberia gustarle el auto "){
            usuarioCombinado.leGustaVehiculo(auto).shouldBe(true)
        }
        it("Usuario combinado que es neofilo y supersticioso,como el auto es del 2020 NO deberia gustarle el auto "){
            usuarioCombinado.leGustaVehiculo(auto1).shouldBe(false)
        }


    }//out Describe Combinado


})//out test
