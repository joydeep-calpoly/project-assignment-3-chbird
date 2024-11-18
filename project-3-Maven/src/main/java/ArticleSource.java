import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the source of an article, containing a unique id and name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleSource
{
@JsonProperty("id")
private final String id;

@JsonProperty("name")
private final String name;

@JsonCreator
public ArticleSource(@JsonProperty("id") String id, @JsonProperty("name") String name)
{
    this.id = id;
    this.name = name;
}

String getId()
{
    return id;
}

String getName()
{
    return name;
}
}
