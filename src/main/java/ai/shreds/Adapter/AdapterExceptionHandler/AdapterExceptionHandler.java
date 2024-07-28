import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class AdapterExceptionHandler {

    @ExceptionHandler(ApplicationPostNotFoundException.class)
    public ResponseEntity<ApplicationPostNotFoundException> handlePostNotFoundException(ApplicationPostNotFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationInternalServerException.class)
    public ResponseEntity<ApplicationInternalServerException> handleInternalServerException(ApplicationInternalServerException ex) {
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}