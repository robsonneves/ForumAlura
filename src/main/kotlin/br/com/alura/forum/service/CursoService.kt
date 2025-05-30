package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private var cursos: List<Curso> = ArrayList()) {

    init {
        val curso = Curso(id = 1, nome = "Kotlin", categoria = "Categoria UM")
        cursos = Arrays.asList(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter({ it -> it.id == id }).findFirst().get()
    }
}
