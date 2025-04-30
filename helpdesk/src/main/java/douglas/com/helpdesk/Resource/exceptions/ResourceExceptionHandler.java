package douglas.com.helpdesk.Resource.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import douglas.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import douglas.com.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandardError> objectnotFoundException(ObjectnotFoundException e, HttpServletRequest request) {
        System.out.println("Entrou no handler de erro: " + e.getMessage());
        // Cria um objeto StandardError com os detalhes do erro.
        StandardError err = new StandardError(System.currentTimeMillis(), Response.SC_NOT_FOUND, "Object Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(Response.SC_NOT_FOUND).body(err);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        System.out.println("Entrou no handler de erro de duplicacao: " + e.getMessage());
        // Cria um objeto StandardError com os detalhes do erro.
        StandardError err = new StandardError(System.currentTimeMillis(), Response.SC_BAD_REQUEST, "Violação de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(Response.SC_BAD_REQUEST).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> ValidationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        System.out.println("Entrou no handler de erro de duplicacao: " + e.getMessage());
        // Cria um objeto StandardError com os detalhes do erro.
        ValidationError errors = new ValidationError(System.currentTimeMillis(), Response.SC_BAD_REQUEST, "Validation Error", "Erro de validação dos campos", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()) {
            errors.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(Response.SC_BAD_REQUEST).body(errors);
    }

}
