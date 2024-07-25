package uam.mx.sintra.articulos.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticuloNotFoundException extends RuntimeException {
    private final String message;
}
