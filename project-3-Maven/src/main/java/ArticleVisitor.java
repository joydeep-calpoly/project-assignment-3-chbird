/**
 * The ArticleVisitor interface defines the visitor pattern for processing articles
 * of different types. Implementing classes must provide specific behavior for
 * each article type.
 */
public interface ArticleVisitor {

/**
 * Visits a SimpleJsonArticle and performs operations defined by the implementing class.
 *
 * @param article the SimpleJsonArticle to be processed
 */
void visit(SimpleJsonArticle article);

/**
 * Visits a NewsApiArticle and performs operations defined by the implementing class.
 *
 * @param article the NewsApiArticle to be processed
 */
void visit(NewsApiArticle article);
}
