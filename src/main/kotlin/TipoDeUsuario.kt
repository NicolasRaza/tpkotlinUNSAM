interface TipoDeUsuario {

    fun leGustaVehiculo(vehiculo: Vehiculo): Boolean

}

class Neofilo() : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return vehiculo.antiguedad() < 2
    }

}

class Supersticioso() : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return vehiculo.añoDeFabricacion % 2 == 0
    }

}

class Caprichoso() : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return vehiculo.marca[0] == vehiculo.modelo[0]
    }

}


class Selectivo(val marca: String) : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return vehiculo.marca == marca
    }

}

class SinLimite() : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return vehiculo.kilometrajeLibre
    }

}

class Combinado(val setDeTipos: Set<TipoDeUsuario>) : TipoDeUsuario {

    override fun leGustaVehiculo(vehiculo: Vehiculo): Boolean {
        return setDeTipos.all { it.leGustaVehiculo(vehiculo) }
    }

}






