/**
 * Represents the configuration for a parser, including the source of the data,
 * the format of the data, and the input data itself.
 *
 * This class is used to hold the necessary configuration details that the parser
 * needs in order to process data. It encapsulates the source from which the data
 * is obtained, the format in which the data is provided, and the actual input data.
 *
 * @see ParserSource
 * @see ParserFormat
 */

public class ParserConfig
{
private final ParserSource source;
private final ParserFormat format;
private final String input;

public ParserConfig(ParserSource source, ParserFormat format, String input) {
    this.source = source;
    this.format = format;
    this.input = input;
}

public ParserSource getSource() {
    return source;
}

public ParserFormat getFormat() {
    return format;
}

public String getInput() {
    return input;
}
}
