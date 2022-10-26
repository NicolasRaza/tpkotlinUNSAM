// Pais , CIUDAD, COSTOBASE

class Destino (
    val pais: String,
    var ciudad: String,
    val costoBase: Double,
) : Entidad {
    override var id: Int? = null


    fun esLocal() = pais == "Argentina"

    fun calculoPrecioLocal(): Double {
        if (!esLocal()) {
            return (costoBase * 1.20)
        } else {
            return costoBase
        }
    }

    fun descuentoPorAntiguedad(usuario: Usuario): Double {
        if (usuario.paisDeResidencia == pais) {
            if (usuario.antiguedad() <= 15) {
                return costoBase * (usuario.antiguedad() * 0.01)
            } else {
                return costoBase * 0.15
            }
        } else {
            return 0.0
        }
    }

    fun precioTotal(usuario: Usuario): Double {
        return calculoPrecioLocal() - descuentoPorAntiguedad(usuario)
    }

    fun esValido(): Boolean {
        return costoBase > 0 && pais.isNotEmpty() && ciudad.isNotEmpty()
    }

}

