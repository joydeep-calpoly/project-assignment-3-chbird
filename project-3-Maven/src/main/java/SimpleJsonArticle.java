import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a simple JSON article with a title, description, published date, and URL.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleJsonArticle implements Article {
private final String title;
private final String description;
private final String publishedAt;
private final String url;
@JsonCreator
public SimpleJsonArticle(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("publishedAt") String publishedAt,
        @JsonProperty("url") String url) {
    this.title = title;
    this.description = description;
    this.publishedAt = publishedAt;
    this.url = url;
}

public String getTitle() { return title; }
public String getDescription() { return description; }
public String getPublishedAt() { return publishedAt; }
public String getUrl() { return url; }

/**
 * Accepts a visitor that performs operations on this article.
 *
 * @param visitor the visitor that will perform operations on this article
 */
@Override
public void accept(ArticleVisitor visitor) { visitor.visit(this); }

@Override
public String toString() {
    return "SimpleJsonArticle{" +
                   "title='" + title + '\'' +
                   ", description='" + description + '\'' +
                   ", publishedAt='" + publishedAt + '\'' +
                   ", url='" + url + '\'' +
                   '}' + '\n';
}
}
