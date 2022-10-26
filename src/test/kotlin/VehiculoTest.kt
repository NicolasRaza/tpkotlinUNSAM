import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.IsolationMode

class VehiculoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("testeo de Auto") {
        val autoHatchback = Auto(
            marca = "Honda",
            modelo = "Civic",
            añoDeFabricacion = 2010,
            costoDiario = 1000.0,
            kilometrajeLibre = true,
            hatchback = true,
        )
        val autoNoHatchback = Auto(
            marca = "Lamborghini",
            modelo = "Veneno",
            añoDeFabricacion = 2013,
            costoDiario = 5000.0,
            kilometrajeLibre = true,
            hatchback = false,
        )
        val relajado = Relajado()
        val neofilo = Neofilo()
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

        it("Auto del 2010 , este año es 2022 por lo tanto su antiguedad es 12") {
            autoHatchback.antiguedad() shouldBe 12
        }
        it("Auto del 13 , este año es 2022 por lo tanto su antiguedad es 9") {
            autoNoHatchback.antiguedad() shouldBe 9
        }
        it("Tiene convenio con la marca Honda ") {

            autoHatchback.tieneConvenio() shouldBe true
        }
        it("Calculo costo base:  es hatchback y tiene convenio , ((Costobase * CantidadDeDias) *1.1) / 0.9 = 1980") {
            autoHatchback.costoBase(5) shouldBe 5000.0
        }
        it("Calculo costo base:  es hatchback y no tiene convenio ,((Costobase * CantidadDeDias) *1.1) = 2200") {

            autoHatchback.costoBase(3) shouldBe 3000.0
        }
        it("Calculo costo base: no es hatchback y no tiene convenio, ((Costobase * CantidadDeDias) *1.25) = 12500") {
            autoNoHatchback.costoBase(7) shouldBe 35000.0
        }
        it("Calculo costo base : no es hatchback y tiene convenio , ((Costobase * CantidadDeDias) *1.25) / 0.9 = 11250 ") {
            autoNoHatchback.tieneConvenio() shouldBe false
            autoNoHatchback.costoBase(6) shouldBe 30000.0
        }

    }
    describe("testeo de Moto") {
        val motoCilindrada250 = Moto(marca = "Honda",
            modelo = "Twister",
            añoDeFabricacion = 2005,
            costoDiario = 500.0,
            kilometrajeLibre = true,
            cilindrada = 250
        )
        val motoCilindradaMayor250 = Moto(
            marca = "Bajaj",
            modelo = "Dominar",
            añoDeFabricacion = 2020,
            costoDiario = 2000.0,
            kilometrajeLibre = true,
            cilindrada = 400,
        )
        val relajado = Relajado()
        val neofilo = Neofilo()
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

        it("Moto del 2005 , este año es 2022 por lo tanto su antiguedad es 17") {
            motoCilindrada250.antiguedad() shouldBe 17
        }
        it("Moto del 2020 , este año es 2022 por lo tanto su antiguedad es 9") {
            motoCilindradaMayor250.antiguedad() shouldBe 2
        }
        it("Tiene convenio con la marca Honda ") {
            motoCilindrada250.tieneConvenio() shouldBe true
        }
        it("Calculo costo base: Cilindrada <= 250 y tiene convenio , (CostoDiario * CantidadDeDias) / 0.9 = 900") {
            motoCilindrada250.costoBase(6) shouldBe 3000.0
        }
        it("Calculo costo base: Cilindrada <= 250 y no tiene convenio , CostoDiario * CantidadDeDias = 2200") {
            motoCilindrada250.costoBase(3) shouldBe 1500.0
        }
        it("Calculo costo base: Cilindrada > 250 y no tiene convenio, CostoDiario * CantidadDeDias + CantidadDeDias*500 = 12500") {
            motoCilindradaMayor250.costoBase(1) shouldBe 2000.0
        }
        it("Calculo costo base: Cilindrada > 250 y tiene convenio , (CostoDiario * CantidadDeDias + CantidadDeDias*500) / 0.9 = 11250 ") {
            motoCilindradaMayor250.tieneConvenio() shouldBe false
            motoCilindradaMayor250.costoBase(15) shouldBe 30000.0
        }

    }
    describe("testeo de Camioneta") {
        val camionetaComun = Camioneta(marca = "Toyota",
            modelo = "Land",
            añoDeFabricacion = 2011,
            costoDiario = 5000.0,
            kilometrajeLibre = true,
            todoTerreno = false
        )
        val camioneta4x4 = Camioneta(marca = "Volkswagen",
            modelo = "Amarok",
            añoDeFabricacion = 2020,
            costoDiario = 10000.0,
            kilometrajeLibre = true,
            todoTerreno = true
        )
        val relajado = Relajado()
        val neofilo = Neofilo()
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
        // CAMIONETA COMUN
        it("Calculo costo base: Alquiler 3 dias y no tiene convenio, no 4x4, (CostoDiario * CantidadDeDias) + 10000 = 20000") {
            camionetaComun.costoBase(3) shouldBe 15000.0
            camionetaComun.costoExtra(3) shouldBe 25000.0
            camionetaComun.costoAlquiler(3) shouldBe 40000.0
        }
        it("Calculo costo base: Alquiler 8 dias y no tiene convenio, no 4x4 , ((CostoDiario * CantidadDeDias) + 10000 + cantidadDeDias * 1000 ) = 51000") {
            camionetaComun.costoBase(8) shouldBe 40000.0
            camionetaComun.costoExtra(8) shouldBe 51000.0
            camionetaComun.costoAlquiler(8) shouldBe 91000.0

        }
        // CAMIONETA 4x4
        it("Calculo costo base: Alquiler  dias y no tiene convenio, es 4x4, ((CostoDiario * CantidadDeDias) + 10000) *1.5 = 45000") {
            camioneta4x4.costoBase(3) shouldBe 30000.0
            camioneta4x4.costoExtra(3) shouldBe 60000.0
            camioneta4x4.costoAlquiler(3) shouldBe 90000.0
        }
        it("Calculo costo base: Alquiler 8 dias y no tiene convenio, es 4x4 , ((CostoDiario * CantidadDeDias) + 10000 + cantidadDeDias * 1000 ) *1.5 = 136500") {
            camioneta4x4.costoBase(8) shouldBe 80000.0
            camioneta4x4.costoExtra(8) shouldBe 136500.0
            camioneta4x4.costoAlquiler(8) shouldBe 216500.0
        }


    }


})

