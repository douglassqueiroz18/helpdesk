package douglas.com.helpdesk.Resource.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import douglas.com.helpdesk.services.exceptions.ObjectDuplicateException;
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
    @ExceptionHandler(ObjectDuplicateException.class)
    public ResponseEntity<StandardError> duplicatedDataException(ObjectDuplicateException e, HttpServletRequest request) {
        System.out.println("Entrou no handler de erro de duplicacao: " + e.getMessage());
        // Cria um objeto StandardError com os detalhes do erro.
        StandardError err = new StandardError(System.currentTimeMillis(), Response.SC_BAD_REQUEST, "Duplicated Data", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(Response.SC_BAD_REQUEST).body(err);
    }

}
