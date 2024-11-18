import java.io.IOException;

/**
 * The JsonVisitor interface defines the visitor pattern for parsing JSON data
 * from different JSON parser types (e.g., NewsApiJsonParser, SimpleJsonParser).
 * The implementing visitor classes will define the logic for processing the parsed data
 * from these different sources.
 */
public interface JsonVisitor
{
/**
 * Visits a NewsApiJsonParser instance and processes its parsed data.
 * This method is called when the visitor visits a `NewsApiJsonParser` to process
 * the parsed data (either from a file or URL).
 *
 * @param newsApiJsonParser The `NewsApiJsonParser` instance that will be visited to parse NewsAPI data.
 * @throws IOException If an error occurs while reading from the input file or URL.
 */
void visit(NewsApiJsonParser newsApiJsonParser) throws IOException;

/**
 * Visits a SimpleJsonParser instance and processes its parsed data.
 * This method is called when the visitor visits a `SimpleJsonParser` to process
 * the parsed SIMPLE JSON data.
 *
 * @param simpleJsonParser The `SimpleJsonParser` instance that will be visited to parse SIMPLE data.
 */
void visit(SimpleJsonParser simpleJsonParser);
}
