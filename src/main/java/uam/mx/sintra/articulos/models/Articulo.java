package uam.mx.sintra.articulos.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Modelo que describe los atributos del articulo.
 */
@Schema(description = "Modelo que describe los atributos del articulo.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-23T20:47:05.135139266-06:00[America/Mexico_City]")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articulos")
public class Articulo   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("titulo")
  private String titulo = null;

  @JsonProperty("contenido")
  private String contenido = null;

  @JsonProperty("descripcion")
  private String descripcion = null;

  /**
   * Categoria a la que pertenece el articulo.
   */
  public enum CategoriaEnum {
    ANALISIS_MEDICO("Analisis medico"),
    
    ESTUDIOS_MEDICOS("Estudios medicos"),
    
    SALUD_MENTAL_Y_EMOCIONAL("Salud mental y emocional"),
    
    RECOMENDACIONES_MEDICAS("Recomendaciones medicas"),
    
    EJERCICIO_Y_ACTIVIDAD_FISICA("Ejercicio y actividad fisica"),
    
    PRODUCTOS("Productos");

    private String value;

    CategoriaEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoriaEnum fromValue(String text) {
      for (CategoriaEnum b : CategoriaEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("categoria")
  private CategoriaEnum categoria = null;

  @JsonProperty("foto")
  private String foto = null;

  public Articulo id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * ID del articulo.
   * @return id
   **/
  @Schema(required = true, description = "ID del articulo.")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Articulo titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Titulo del articulo.
   * @return titulo
   **/
  @Schema(required = true, description = "Titulo del articulo.")
      @NotNull

    public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Articulo contenido(String contenido) {
    this.contenido = contenido;
    return this;
  }

  /**
   * Contenido del articulo.
   * @return contenido
   **/
  @Schema(required = true, description = "Contenido del articulo.")
      @NotNull

    public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  public Articulo descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Descripcion del articulo.
   * @return descripcion
   **/
  @Schema(required = true, description = "Descripcion del articulo.")
      @NotNull

    public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Articulo categoria(CategoriaEnum categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Categoria a la que pertenece el articulo.
   * @return categoria
   **/
  @Schema(required = true, description = "Categoria a la que pertenece el articulo.")
      @NotNull

    public CategoriaEnum getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaEnum categoria) {
    this.categoria = categoria;
  }

  public Articulo foto(String foto) {
    this.foto = foto;
    return this;
  }

  /**
   * Foto referente al articulo.
   * @return foto
   **/
  @Schema(description = "Foto referente al articulo.")
  
    public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Articulo articulo = (Articulo) o;
    return Objects.equals(this.id, articulo.id) &&
        Objects.equals(this.titulo, articulo.titulo) &&
        Objects.equals(this.contenido, articulo.contenido) &&
        Objects.equals(this.descripcion, articulo.descripcion) &&
        Objects.equals(this.categoria, articulo.categoria) &&
        Objects.equals(this.foto, articulo.foto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, titulo, contenido, descripcion, categoria, foto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Articulo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    contenido: ").append(toIndentedString(contenido)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    foto: ").append(toIndentedString(foto)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
