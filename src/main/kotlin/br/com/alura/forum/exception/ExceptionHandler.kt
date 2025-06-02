package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: NotFoundException, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            erro = HttpStatus.NOT_FOUND.name,
            menssagem = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            erro = HttpStatus.INTERNAL_SERVER_ERROR.name,
            menssagem = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(exception: MethodArgumentNotValidException, request: HttpServletRequest): ErrorView {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { e -> errorMessage.put(e.field, e.defaultMessage) }
        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = HttpStatus.BAD_REQUEST.name,
            menssagem = errorMessage.toString(),
            path = request.servletPath
        )
    }
}