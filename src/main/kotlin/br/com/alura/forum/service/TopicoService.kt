package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoAtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(

    private val mapper: TopicoMapper,
    private val notFoundMessage: String = "Tópico não encontrado!",
    private val repository: TopicoRepository) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if(nomeCurso == null){
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { it -> mapper.toTopicoView(it) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow({NotFoundException(notFoundMessage)})
        return mapper.toTopicoView(topico)
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val topico = mapper.toTopico(topicoForm)
        return mapper.toTopicoView(repository.save(topico))
    }

    fun atualizar(topicoAtualizaForm: TopicoAtualizaForm): TopicoView {
        val topico = repository.findById(topicoAtualizaForm.id).orElseThrow({NotFoundException(notFoundMessage)})
        topico.titulo = topicoAtualizaForm.titulo
        topico.mensagem = topicoAtualizaForm.mensagem
        return mapper.toTopicoView(repository.save(topico))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}