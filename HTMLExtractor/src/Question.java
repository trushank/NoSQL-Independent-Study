import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// TODO: Auto-generated Javadoc
/**
 * The Class Question.
 */
public class Question {

	/** The id. */
	String id;

	/** The title. */
	String title;

	/** The text. */
	String text;

	/** The askedby. */
	User askedby;

	/** The date. */
	String date;

	/** The rating. */
	long rating;

	/** The tags. */
	String[] tags;

	/** The answers. */
	ArrayList<Answer> answers;

	/** The comments. */
	ArrayList<Comment> comments;

	/**
	 * Instantiates a new question.
	 */
	public Question() {

	}

	/**
	 * Instantiates a new question.
	 * 
	 * @param e
	 *            the e
	 */
	public Question(Document e) {
		int firstIndex = e.getElementsByClass("question-hyperlink")
				.attr("href").toString().indexOf('/', 2);
		int secondIndex = e.getElementsByClass("question-hyperlink")
				.attr("href").toString().indexOf('/', firstIndex + 1);

		this.id = (e.getElementsByClass("question-hyperlink").attr("href")
				.toString().substring(firstIndex + 1, secondIndex));
		this.title = e.getElementsByClass("question-hyperlink").first().text();
		this.text = e.getElementsByClass("post-text").first().text();
		Elements eles = e.getElementsByClass("post-tag");
		tags = eles.text().split(" ");
		rating = Long.parseLong(e.getElementsByClass("vote-count-post").first()
				.text());
		date = e.getElementsByClass("owner").first()
				.getElementsByClass("relativetime").text();
		askedby = new User(e.getElementsByClass("owner").first());
		Elements answerElements = e.getElementsByClass("answer");
		Elements commentElements = e.getElementsByClass("comments").first()
				.getElementsByClass("comment-text");
		answers = new ArrayList<Answer>();

		for (Element content : answerElements) {

			answers.add(new Answer(content));

		}
		comments = new ArrayList<Comment>();
		for (Element content : commentElements) {

			comments.add(new Comment(content));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ret = "**********************************************************\n"
				+ "ID: "
				+ id
				+ "\nTitle: "
				+ title
				+ "\nQuestion: "
				+ text
				+ "\nRating: "
				+ rating
				+ "\nAsked By: "
				+ askedby
				+ "\nDate: "
				+ date + "\nTags: " + Arrays.toString(tags);
		for (Answer answer : answers) {
			ret += "\n" + answer.toString();
		}
		for (Comment comment : comments) {
			ret += "\n" + comment.toString();
		}
		return ret;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println(toString());
	}
}
