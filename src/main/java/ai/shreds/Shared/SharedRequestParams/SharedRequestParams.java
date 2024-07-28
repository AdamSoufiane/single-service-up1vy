package ai.shreds.shared;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * SharedRequestParams is used for passing filtering parameters around different layers of the application.
 * It encapsulates the type of filter and the value for that filter to be applied when fetching posts.
 *
 * @param filterType The type of filter to apply (e.g., 'author', 'date').
 * @param filterValue The value of the filter (e.g., 'John Doe', '2023-01-01').
 */
@Data
public class SharedRequestParams {
    /**
     * Type of filter to be applied.
     */
    @NotBlank(message = "Filter type cannot be blank")
    private String filterType;
    /**
     * Value for the specified filter type.
     */
    @NotBlank(message = "Filter value cannot be blank")
    private String filterValue;

    /**
     * Constructs a new instance with specified filter type and value.
     * @param filterType the type of filter
     * @param filterValue the value of the filter
     */
    public SharedRequestParams(String filterType, String filterValue) {
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    /**
     * No-argument constructor for frameworks that require it.
     */
    public SharedRequestParams() {
    }
}