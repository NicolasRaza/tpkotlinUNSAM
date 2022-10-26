abstract class Repositorio<T : Entidad>() {

    val repositorio: MutableList<T> = mutableListOf()
    var ultimoId = 1

    fun crear(contenido: T) {
        contenido.id = ultimoId
        repositorio.add(contenido)
        ultimoId++
    }

    fun borrar(contenido: T) {
        repositorio.remove(contenido)
    }

    open fun actualizar(contenido: T) {
        //try{
        //    this.borrar(this.buscarPorId(contenido.id))
        //    repositorio.add(contenido)
        //    }catch(RuntimeExeption){
        //      this.crear(contenido : T)
        //    }
        this.borrar(this.buscarPorId(contenido.id))
        repositorio.add(contenido)
    }

    fun buscarPorId(id: Int?): T {
        if (repositorio.find { it.id == id } != null) {
            return repositorio.find { it.id == id }!!
        } else {
            throw RuntimeException("Id no encontrado")
        }
    }

    abstract fun buscarPorContenido(contenido: String): List<T>


}

class RepositorioDeDestino() : Repositorio<Destino>() {
    override fun buscarPorContenido(contenido: String): List<Destino> {
        return repositorio.filter { it.ciudad.contains(contenido) || it.pais.contains(contenido) }
    }
}


class RepositorioDeVehiculo() : Repositorio<Vehiculo>() {

    override fun buscarPorContenido(contenido: String): List<Vehiculo> {
        return repositorio.filter { it.marca == contenido || it.modelo[0] == contenido[0] }
    }
}

class RepositorioDeUsuario() : Repositorio<Usuario>() {

    override fun buscarPorContenido(contenido: String): List<Usuario> {
        return repositorio.filter {
            it.nombre.contains(
                contenido,
                true
            ) || it.apellido.contains(contenido, true) || it.username.equals(contenido, false)
        }
    }
}

class RepositorioDeItinerario() : Repositorio<Itinerario>() {

    override fun buscarPorContenido(contenido: String): List<Itinerario> {
        return repositorio.filter {
            it.destino.ciudad.contains(contenido, true) || it.destino.pais.contains(contenido,
                true) || it.listaDeActividades().any{it.descripcionDeActividad.equals(contenido,true)}
        }

    }
}







