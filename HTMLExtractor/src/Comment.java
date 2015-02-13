import org.jsoup.nodes.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class Comment.
 */
public class Comment {

	/** The commenttext. */
	String commenttext = "";

	/** The commenter. */
	User commenter;

	/** The date. */
	String date = "";

	/**
	 * Instantiates a new comment.
	 */
	public Comment() {

	}

	/**
	 * Instantiates a new comment.
	 * 
	 * @param e
	 *            the e
	 */
	public Comment(Element e) {
		commenter = new User(e);
		commenttext = e.getElementsByClass("comment-copy").text();
		date = e.getElementsByClass("relativetime-clean").text();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "\n###### Comment: " + commenttext + "\n###### Commenter: "
				+ commenter + "\n###### Date: " + date;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println(toString());
	}
}
