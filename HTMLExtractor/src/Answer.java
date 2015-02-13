import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// TODO: Auto-generated Javadoc
/**
 * The Class Answer.
 */
public class Answer {

	/** The Answer text. */
	String AnswerText;

	/** The Answer code. */
	String AnswerCode;

	/** The answeredby. */
	User answeredby;

	/** The editedby. */
	User editedby;

	/** The comments. */
	ArrayList<Comment> comments;

	/** The accepted answer. */
	boolean acceptedAnswer = false;

	/** The votes. */
	int votes = 0;

	/** The date. */
	String date = "";

	/** The content. */
	Element content;

	/**
	 * Instantiates a new answer.
	 */
	public Answer() {

	}

	/**
	 * Instantiates a new answer.
	 * 
	 * @param content
	 *            the content
	 */
	public Answer(Element content) {
		if (!content.getElementsByClass("vote-accepted-on").isEmpty()) {
			this.acceptedAnswer = true;

		}
		if (!content.getElementsByClass("post-signature").last()
				.getElementsByClass("relativetime").isEmpty())
			this.date = content.getElementsByClass("post-signature").last()
					.getElementsByClass("relativetime").last().text();
		if (!content.getElementsByTag("code").isEmpty())
			this.AnswerCode = content.getElementsByTag("code").first().html();
		this.AnswerText = content.getElementsByTag("p").text();
		this.answeredby = new User(content.getElementsByClass("post-signature")
				.last());
		this.votes = Integer.parseInt(content.getElementsByClass(
				"vote-count-post").text());
		// this.editedby
		Elements commentElements = content.getElementsByClass("comments")
				.first().getElementsByClass("comment-text");
		comments = new ArrayList<Comment>();
		for (Element comcontent : commentElements) {

			comments.add(new Comment(comcontent));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ret = "**********************************************************\n"
				+ "Answer: "
				+ AnswerText
				+ "\nCode: "
				+ AnswerCode
				+ "\nAccepted: "
				+ acceptedAnswer
				+ "\nAnswered By: "
				+ answeredby + "\nAnswer Votes: " + votes + "\nDate: " + date;
		for (Comment comment : comments) {
			ret += "\n" + comment.toString();
		}
		return ret;
	}

	/**
	 * Prints the Answer.
	 */
	public void print() {
		System.out.println(toString());
	}

}
