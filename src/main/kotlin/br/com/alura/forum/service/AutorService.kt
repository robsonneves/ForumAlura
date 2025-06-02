package br.com.alura.forum.service

import br.com.alura.forum.model.Autor
import br.com.alura.forum.repository.AutorRepository
import org.springframework.stereotype.Service

@Service
class AutorService (private val repositary: AutorRepository){

    fun buscarAutorPorId(id: Long): Autor {
        return repositary.getReferenceById(id)
    }
}
