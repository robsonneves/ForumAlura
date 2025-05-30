package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoAtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService (

    private var topicos: List<Topico> = ArrayList(),
    private val mapper: TopicoMapper,
    private val notFoundMessage: String = "Tópico não encontrado!") {

    fun listar(): List<TopicoView> {
        return topicos.stream()
            .map { it -> mapper.toTopicoView(it) }
            .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        return mapper.toTopicoView(filterTopicoId(id))
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val topico = mapper.toTopico(topicoForm)
        topico.id = topicos.size.toLong()+1;
        topicos = topicos.plus(topico)
        return mapper.toTopicoView(topico)
    }

    fun atualizar(topicoAtualizaForm: TopicoAtualizaForm): TopicoView {
        val topico = filterTopicoId(topicoAtualizaForm.id)
        val topicoAtualizado = Topico(
            id = topicoAtualizaForm.id,
            titulo = topicoAtualizaForm.titulo,
            mensagem = topicoAtualizaForm.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return mapper.toTopicoView(topico)
    }

    fun deletar(id: Long) {
        topicos = topicos.minus(filterTopicoId(id))
    }

    fun filterTopicoId(id: Long): Topico{
        return topicos.stream().filter({ it -> it.id == id }).findFirst()
            .orElseThrow({NotFoundException(notFoundMessage)})
    }
}