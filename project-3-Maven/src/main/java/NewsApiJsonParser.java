import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A parser for extracting valid articles from the News API response in various formats.
 *
 * This class provides methods to parse JSON data from multiple sources, including files, input streams, strings,
 * and URLs, into a list of valid `NewsApiArticle` objects. It validates articles by checking for required fields
 * such as title, description, published date-time, and URL. Invalid articles are logged and excluded from the final
 * list, while valid articles are written to a text file for storage or further processing.
 *
 * @see NewsApiArticle
 * @see NewsApiResponse
 */
public class NewsApiJsonParser extends JsonParser
{
/**
 * Parses a JSON file to extract valid articles.
 *
 * @param jsonFile the JSON file containing the articles.
 * @return a list of valid NewsApiArticle objects.
 * @throws IOException if an I/O error occurs while reading the file.
 */
public List<NewsApiArticle> parse(File jsonFile) throws IOException {
    return parseResponse(mapper.readValue(jsonFile, NewsApiResponse.class));
}

/**
 * Parses a JSON input stream to extract valid articles.
 *
 * @param inputStream the input stream containing the JSON data.
 * @return a list of valid NewsApiArticle objects.
 * @throws IOException if an I/O error occurs while reading the input stream.
 */
public List<NewsApiArticle> parse(InputStream inputStream) throws IOException {
    return parseResponse(mapper.readValue(inputStream, NewsApiResponse.class));
}

/**
 * Parses a JSON string to extract valid articles.
 *
 * @param jsonString the JSON string containing the articles.
 * @return a list of valid NewsApiArticle objects.
 * @throws IOException if an error occurs while reading the JSON string.
 */
public List<NewsApiArticle> parse(String jsonString) throws IOException {
    return parseResponse(mapper.readValue(jsonString, NewsApiResponse.class));
}

/**
 * Parses JSON data from a URL to extract valid articles.
 *
 * @param url the URL containing the JSON data.
 * @return a list of valid NewsApiArticle objects.
 * @throws IOException if an I/O error occurs while reading from the URL.
 */
public List<NewsApiArticle> parse(URL url) throws IOException {
    return parseResponse(mapper.readValue(url, NewsApiResponse.class));
}

private List<NewsApiArticle> parseResponse(NewsApiResponse response) {
    List<NewsApiArticle> validArticles = new ArrayList<>();
    
    if (!"ok".equals(response.getStatus())) {
        logger.warning("NewsAPI error: " + response.getStatus());
        return validArticles;
    }
    
    for (NewsApiArticle article : response.getArticles()) {
        try {
            String validationMessage = validateArticle(article);
            if (validationMessage == null) {
                validArticles.add(article);
            } else {
                logger.warning("Skipping invalid article: " + validationMessage + " - Valid fields: " + getValidFields(article));
            }
        } catch (Exception e) {
            logger.warning("Skipping invalid article: " + e.getMessage());
        }
    }
    
    logger.info("Total valid articles: " + validArticles.size());
    try {
        writeValidArticlesToFile(validArticles, new File("valid_articles.txt"));
    } catch (IOException e) {
        logger.severe("Error writing articles to file: " + e.getMessage());
    }
    
    return validArticles;
}
protected String validateArticle(NewsApiArticle article) {
    if (article.getTitle() == null || article.getTitle().isEmpty()) {
        return "Title is missing.";
    }
    if (article.getDescription() == null || article.getDescription().isEmpty()) {
        return "Description is missing.";
    }
    if (article.getPublishedAt() == null || article.getPublishedAt().isEmpty()) {
        return "Published date-time is missing.";
    }
    if (article.getUrl() == null || article.getUrl().isEmpty()) {
        return "URL is missing.";
    }
    return null;
}

private String getValidFields(NewsApiArticle article) {
    StringBuilder validFields = new StringBuilder();
    if (article.getTitle() != null && !article.getTitle().isEmpty()) {
        validFields.append("Title: ").append(article.getTitle()).append("; ");
    }
    if (article.getDescription() != null && !article.getDescription().isEmpty()) {
        validFields.append("Description: ").append(article.getDescription()).append("; ");
    }
    if (article.getPublishedAt() != null && !article.getPublishedAt().isEmpty()) {
        validFields.append("Published At: ").append(article.getPublishedAt()).append("; ");
    }
    if (article.getUrl() != null && !article.getUrl().isEmpty()) {
        validFields.append("URL: ").append(article.getUrl()).append("; ");
    }
    return validFields.toString();
}

private void writeValidArticlesToFile(List<NewsApiArticle> articles, File outputFile) throws IOException {
    try (FileWriter writer = new FileWriter(outputFile)) {
        for (NewsApiArticle article : articles) {
            writer.write(article.toString() + System.lineSeparator());
        }
    }
}
/**
 * Accepts a JsonVisitor to allow the visitor to perform operations on this parser.
 * This method implements the Visitor design pattern, enabling different actions to
 * be performed on the `NewsApiJsonParser` object based on the type of the visitor.
 *
 * @param visitor The visitor that will visit this `NewsApiJsonParser` instance and perform the necessary actions.
 * @throws IOException If an I/O error occurs while processing the visitor's actions.
 */
@Override
public void accept(JsonVisitor visitor) throws IOException
{
    visitor.visit(this);
}

}