import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a response from the News API containing a status and a list of articles.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponse
{
@JsonProperty("status")
private String status;

@JsonProperty("articles")
private NewsApiArticle[] articles;

/**
 * Gets the status of the API response.
 *
 * @return a string representing the status of the response
 */
public String getStatus()
{
    return status;
}

/**
 * Gets a list of articles from the API response.
 *
 * @return a list of NewsApiArticle objects. If there are no articles, an empty list is returned.
 */
public List<NewsApiArticle> getArticles()
{
    return articles != null ? Arrays.asList(articles) : List.of();
}
}

