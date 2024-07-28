import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class ApplicationInternalServerException extends RuntimeException {
    private final String message;
    private final String details;

    public ApplicationInternalServerException(String message, String details) {
        super(message);
        this.message = message;
        this.details = details;
    }
}