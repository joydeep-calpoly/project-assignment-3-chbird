import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an article from the News API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiArticle implements Article {
@JsonProperty("title")
private final String title;

@JsonProperty("description")
private final String description;

@JsonProperty("publishedAt")
private final String publishedAt;

@JsonProperty("url")
private final String url;

@JsonProperty("source")
private final ArticleSource articleSource;

@JsonProperty("author")
private final String author;

@JsonProperty("urlToImage")
private final String urlToImage;

@JsonProperty("content")
private final String content;

@JsonCreator
public NewsApiArticle(
        @JsonProperty("source") ArticleSource articleSource,
        @JsonProperty("author") String author,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("url") String url,
        @JsonProperty("urlToImage") String urlToImage,
        @JsonProperty("publishedAt") String publishedAt,
        @JsonProperty("content") String content) {
    this.title = title;
    this.url = url;
    this.description = description;
    this.publishedAt = publishedAt;
    this.articleSource = articleSource;
    this.author = author;
    this.urlToImage = urlToImage;
    this.content = content;
}

public String getTitle() { return title; }
public String getDescription() { return description; }
public String getPublishedAt() { return publishedAt; }
public String getUrl() { return url; }
public ArticleSource getArticleSource() { return articleSource; }
public String getAuthor() { return author; }
public String getUrlToImage() { return urlToImage; }
public String getContent() { return content; }

/**
 * Accepts a visitor that performs operations on this article.
 *
 * @param visitor the visitor that will perform operations on this article
 */
@Override
public void accept(ArticleVisitor visitor) { visitor.visit(this); }

@Override
public String toString() {
    return "NewsApiArticle{" +
                   "title='" + title + '\'' +
                   ", description='" + description + '\'' +
                   ", publishedAt='" + publishedAt + '\'' +
                   ", url='" + url + '\'' +
                   ", articleSource=" + (articleSource != null ? articleSource.getName() : "null") +
                   ", author='" + author + '\'' +
                   ", urlToImage='" + urlToImage + '\'' +
                   ", content='" + content + '\'' +
                   '}' + '\n';
}
}
