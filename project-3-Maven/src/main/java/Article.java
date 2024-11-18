/**
 * Represents an article in the system.
 * This interface defines the contract for any class that implements an article,
 * requiring it to provide a method for accepting an {@link ArticleVisitor}.
 */
public interface Article
{

/**
 * Accepts an {@link ArticleVisitor} that performs operations on this article.
 *
 * @param visitor the visitor that will perform operations on the article
 */
void accept(ArticleVisitor visitor);
}
