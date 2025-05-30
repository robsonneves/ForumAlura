package br.com.alura.forum.service

import br.com.alura.forum.model.Autor
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutorService (private var autores: List<Autor> = ArrayList()){

    init {
        var autor = Autor(
            id = 1,
            nome = "Maria",
            email = "maria@gmail.com"
        )
        autores = Arrays.asList(autor)
    }

    fun buscarAutorPorId(id: Long): Autor {
        return autores.stream().filter({ it -> it.id == id }).findFirst().get()
    }
}
