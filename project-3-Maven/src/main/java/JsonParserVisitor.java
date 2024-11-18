import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * The JsonParserVisitor class implements the JsonVisitor interface and provides the logic for
 * visiting and processing JSON data for different types of parsers (e.g., NewsApiJsonParser, SimpleJsonParser).
 * It processes the parsed data according to the provided configuration, either from a file or a URL,
 * and delegates further processing to an ArticleProcessor.
 *
 * @see NewsApiJsonParser
 * @see SimpleJsonParser
 */
public class JsonParserVisitor implements JsonVisitor
{
private final ArticleProcessor processor = new ArticleProcessor();

private final ParserConfig config;

/**
 * Constructs a JsonParserVisitor with the given configuration.
 *
 * @param config The configuration that defines the source and format of the JSON data.
 */
public JsonParserVisitor(ParserConfig config) {
    this.config = config;
}

/**
 * Visits a NewsApiJsonParser instance and processes its parsed data.
 * The method reads the input (either a file or a URL) based on the configuration,
 * parses the NewsAPI articles, and then processes each article using an ArticleProcessor.
 *
 * @param newsApiJsonParser The NewsApiJsonParser instance that will be visited to parse NewsAPI data.
 * @throws IOException If an error occurs during parsing from the file or URL.
 * @throws UnsupportedOperationException If the source is not supported for NewsAPI format (other than FILE or URL).
 */
@Override
public void visit(NewsApiJsonParser newsApiJsonParser) throws IOException
{
    List<NewsApiArticle> newsArticles;
    
    if (config.getSource() == ParserSource.FILE)
    {
        File jsonFile = new File(config.getInput());
        newsArticles =  newsApiJsonParser.parse(jsonFile);
    }
    else if (config.getSource() == ParserSource.URL)
    {
        URL newsApiUrl = new URL(config.getInput());
        newsArticles = newsApiJsonParser.parse(newsApiUrl);
    }
    else
    {
        throw new UnsupportedOperationException("Unsupported source for NewsAPI format.");
    }
    
    for (Article article : newsArticles) {
        article.accept(processor);
    }
}

/**
 * Visits a SimpleJsonParser instance and processes its parsed data.
 * The method reads the input from a file, parses the Simple JSON data,
 * and processes the article using an ArticleProcessor.
 * URL source is not supported for SIMPLE format.
 *
 * @param simpleJsonParser The SimpleJsonParser instance that will be visited to parse SIMPLE data.
 * @throws UnsupportedOperationException If the source is a URL (SIMPLE format does not support URL source).
 */
@Override
public void visit(SimpleJsonParser simpleJsonParser)
{
    Article simpleArticle;
    
    if (config.getSource() == ParserSource.FILE)
    {
        File simpleJsonFile = new File(config.getInput());
        simpleArticle = simpleJsonParser.parse(simpleJsonFile);
    }
    else
    {
        throw new UnsupportedOperationException("SIMPLE format does not support URL source.");
    }
    
    simpleArticle.accept(processor);
}
}
