package uam.mx.sintra.articulos.models;

import javax.validation.constraints.NotNull;

public record ArticuloResponse(
        String titulo,
        String contenido,
        String descripcion,
        CategoriaEnum categoriaEnum,
        String foto,
        String requisitos
) {
    public enum CategoriaEnum {
        ANALISIS_MEDICO,

        ESTUDIOS_MEDICOS,

        SALUD_MENTAL_Y_EMOCIONAL,

        RECOMENDACIONES_MEDICAS,

        EJERCICIO_Y_ACTIVIDAD_FISICA
    }
}
