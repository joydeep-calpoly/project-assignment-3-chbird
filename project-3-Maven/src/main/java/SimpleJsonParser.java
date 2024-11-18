import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Parses JSON data in the Simple JSON format into a valid SimpleJsonArticle object.
 * <p>
 * This class provides methods to parse Simple JSON data from various sources, including files,
 * input streams, strings, and URLs. The parsed data is then validated, and if valid, it's written
 * to a file and returned as a SimpleJsonArticle object. If the data is invalid or parsing fails,
 * appropriate warnings are logged.
 * </p>
 */
public class SimpleJsonParser extends JsonParser
{

/**
 * Parses a simple JSON file and converts it to a SimpleJsonArticle object.
 *
 * @param jsonFile the JSON file to parse
 * @return the parsed SimpleJsonArticle object, or null if parsing fails
 */
public SimpleJsonArticle parse(File jsonFile) {
    return parseJsonSource(() -> mapper.readValue(jsonFile, SimpleJsonArticle.class));
}

/**
 * Parses a simple JSON input stream and converts it to a SimpleJsonArticle object.
 *
 * @param jsonInputStream the input stream containing JSON data
 * @return the parsed SimpleJsonArticle object, or null if parsing fails
 */
public SimpleJsonArticle parse(InputStream jsonInputStream) {
    return parseJsonSource(() -> mapper.readValue(jsonInputStream, SimpleJsonArticle.class));
}

/**
 * Parses a simple JSON string and converts it to a SimpleJsonArticle object.
 *
 * @param jsonString the JSON string to parse
 * @return the parsed SimpleJsonArticle object, or null if parsing fails
 */
public SimpleJsonArticle parse(String jsonString) {
    return parseJsonSource(() -> mapper.readValue(jsonString, SimpleJsonArticle.class));
}
/**
 * Parses a simple JSON URL and converts it to a SimpleJsonArticle object.
 *
 * @param jsonUrl the URL pointing to the JSON data
 * @return the parsed SimpleJsonArticle object, or null if parsing fails
 */
public SimpleJsonArticle parse(URL jsonUrl) {
    return parseJsonSource(() -> mapper.readValue(jsonUrl, SimpleJsonArticle.class));
}

private SimpleJsonArticle parseJsonSource(JsonSourceReader<SimpleJsonArticle> reader) {
    SimpleJsonArticle article;
    
    try {
        article = reader.read();
    } catch (Exception e) {
        logger.warning("Failed to parse JSON source: " + e.getMessage());
        return null;
    }
    
    String validationMessage = validateArticle(article);
    if (validationMessage != null) {
        logger.warning("Skipping invalid article: " + validationMessage + " - Valid fields: " + getValidFields(article));
        return null;
    }
    
    logger.info("Successfully parsed a valid article.");
    try {
        writeValidArticleToFile(article, new File("valid_article_output.txt"));
    } catch (IOException e) {
        logger.severe("Error writing article to file: " + e.getMessage());
    }
    
    return article;
}

private static String validateArticle(SimpleJsonArticle article) {
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

private static String getValidFields(SimpleJsonArticle article) {
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

private static void writeValidArticleToFile(SimpleJsonArticle article, File outputFile) throws IOException {
    try (FileWriter writer = new FileWriter(outputFile, true)) {
        writer.write(article.toString() + System.lineSeparator());
    }
}
/**
 * Accepts a JsonVisitor to allow the visitor to perform operations on this parser.
 * This method implements the Visitor design pattern, enabling different actions to
 * be performed on the `SimpleJsonParser` object based on the type of the visitor.
 *
 * @param visitor The visitor that will visit this `SimpleJsonParser` instance and perform the necessary actions.
 * @throws IOException If an I/O error occurs while processing the visitor's actions.
 */
@Override
public void accept(JsonVisitor visitor) throws IOException
{
    visitor.visit(this);
}

@FunctionalInterface
private interface JsonSourceReader<T> {
    T read() throws IOException;
}
}
