package br.com.alura.forum.repository

import br.com.alura.forum.model.Autor
import org.springframework.data.jpa.repository.JpaRepository

interface AutorRepository: JpaRepository<Autor, Long> {}