import java.util.Calendar

abstract class Vehiculo(
    val marca: String,
    val modelo: String,
    val añoDeFabricacion: Int,
    val costoDiario: Double,
    val kilometrajeLibre: Boolean,
) : Entidad {

    override var id: Int? = null

    fun antiguedad() = Calendar.getInstance().get(Calendar.YEAR) - añoDeFabricacion

    fun costoBase(cantidaDeDias: Int): Double = cantidaDeDias * costoDiario

    fun costoAlquiler(cantidadDeDias: Int): Double = costoExtra(cantidadDeDias) + costoBase(cantidadDeDias)

    abstract fun costoExtra(cantidadDeDias: Int) : Double

    fun tieneConvenio(): Boolean = MarcaConConvenio.Honda.toString() == marca

}

//------------CLASES HIJAS------------------
class Auto(
    val hatchback: Boolean, marca: String, modelo: String, añoDeFabricacion: Int, costoDiario: Double,
    kilometrajeLibre: Boolean,
) : Vehiculo(marca, modelo, añoDeFabricacion, costoDiario, kilometrajeLibre) {

    fun precioConBaul(cantidadDeDias: Int): Double {
        return if (hatchback) costoBase(cantidadDeDias) * 1.1 else return costoBase(cantidadDeDias) * 1.25
    }

    override fun costoExtra(cantidadDeDias: Int): Double {
        return if (tieneConvenio()) precioConBaul(cantidadDeDias) * 0.90 else return precioConBaul(cantidadDeDias)
    }

}

class Moto(
    val cilindrada: Int,
    marca: String, modelo: String, añoDeFabricacion: Int,
    costoDiario: Double, kilometrajeLibre: Boolean,
) : Vehiculo(marca, modelo, añoDeFabricacion, costoDiario, kilometrajeLibre) {

    fun cantidadCilindrada(cantidadDeDias: Int): Double {
        return if (cilindrada > 250) costoBase(cantidadDeDias) + cantidadDeDias * 500 else return costoBase(
            cantidadDeDias)
    }

    override fun costoExtra(cantidadDeDias: Int): Double {
        return if (tieneConvenio()) cantidadCilindrada(cantidadDeDias) * 0.9 else return cantidadCilindrada(
            cantidadDeDias)
    }


}

class Camioneta(
    val todoTerreno: Boolean,
    marca: String, modelo: String, añoDeFabricacion: Int,
    costoDiario: Double, kilometrajeLibre: Boolean,
) : Vehiculo(marca, modelo, añoDeFabricacion, costoDiario, kilometrajeLibre) {

    fun costoSiEss4x4(cantidadDeDias: Int): Double {
        return if (todoTerreno) costoSegunDiasDeAlquiler(cantidadDeDias) * 1.5 else costoSegunDiasDeAlquiler(
            cantidadDeDias)
    }

    fun costoSegunDiasDeAlquiler(cantidadDeDias: Int): Double {
        return 10000 + if (cantidadDeDias < 8) costoBase(cantidadDeDias) else costoBase(cantidadDeDias) + (cantidadDeDias - 7) * 1000.0
    }

    override fun costoExtra(cantidadDeDias: Int): Double {
        return if (tieneConvenio()) costoSiEss4x4(cantidadDeDias) * 0.9 else costoSiEss4x4(cantidadDeDias)

    }
}
