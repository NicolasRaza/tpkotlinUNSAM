import com.google.gson.Gson


interface ServiceDestino {
    fun getDestinos(): String
}

object Updater {
    lateinit var service: ServiceDestino
    lateinit var repositorio : RepositorioDeDestino



    fun actualizar() {
        this.jsonAListDestinos().forEach { repositorio.actualizar(it) }
    }

    fun jsonAListDestinos(): List<Destino> {
        val listaJson = Gson().fromJson(service.getDestinos(), ArrayList<String>()::class.java)
        return listaJson.map { Gson().fromJson(it, Destino::class.java)}
    }
}
