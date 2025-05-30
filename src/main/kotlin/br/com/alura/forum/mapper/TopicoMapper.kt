package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.AutorService
import br.com.alura.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoMapper (
    private var curso: CursoService,
    private var autor: AutorService){

    fun toTopicoView(topicoForm: Topico): TopicoView {
        return TopicoView(
            topicoForm.id,
            topicoForm.titulo,
            topicoForm.mensagem,
            topicoForm.status,
            topicoForm.dataCriacao
        )
    }

    fun toTopico(topicoView: TopicoForm): Topico {
        return Topico(
            titulo = topicoView.titulo,
            mensagem = topicoView.mensagem,
            curso = curso.buscarPorId(topicoView.idCurso),
            autor = autor.buscarAutorPorId(topicoView.idAutor)
        )
    }
}