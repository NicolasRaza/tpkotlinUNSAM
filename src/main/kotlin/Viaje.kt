
class Viaje(
    val itinerario: Itinerario,
    val vehiculo: Vehiculo,
) {
    fun costoDeVIaje(diasDeAlquilerVehiculo: Int, viajero : Usuario): Double {
        return itinerario.costo() + vehiculo.costoAlquiler(diasDeAlquilerVehiculo) +
                itinerario.destino.precioTotal(viajero)
    }

    fun esLocal(): Boolean {
        return itinerario.destino.esLocal()
    }

    fun destino() = itinerario.destino


}