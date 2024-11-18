import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Abstract class representing a generic JSON parser. It is the base class for specific
 * parsers (e.g., SimpleJsonParser, NewsApiJsonParser) that can handle different formats
 * of JSON data. This class is responsible for setting up a logger to record warnings
 * during parsing and initializing the ObjectMapper used for deserialization.
 */
public abstract class JsonParser
{
protected static final Logger logger = Logger.getLogger(JsonParser.class.getName());

protected final ObjectMapper mapper = new ObjectMapper();

static {
    try {
        FileHandler fileHandler = new FileHandler("parser_warnings.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * Abstract method for accepting a JsonVisitor that will perform operations on
 * the specific JSON parser. This method should be implemented by concrete
 * subclasses of JsonParser.
 *
 * @param visitor The JsonVisitor that will process the parsed JSON data.
 *                The visitor typically implements actions such as parsing
 *                or data processing.
 * @throws IOException If an error occurs during the parsing process or file handling.
 */
public abstract void accept(JsonVisitor visitor) throws IOException;
}
