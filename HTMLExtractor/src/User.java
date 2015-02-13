import org.jsoup.nodes.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/** The name. */
	String name;

	/** The reputation. */
	String reputation = "";

	/** The gold. */
	long gold = 0;

	/** The silver. */
	long silver = 0;

	/** The bronze. */
	long bronze = 0;

	/** The url. */
	String url = "";

	/** The user tag. */
	Element userTag;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Username: " + name + ", Reputation: " + reputation + ", Gold: "
				+ gold + ", Silver: " + silver + ", Bronze: " + bronze
				+ ", URL: " + url;
	}

	/**
	 * Instantiates a new user.
	 */
	public User() {

	}

	/**
	 * Instantiates a new user.
	 * 
	 * @param userTag
	 *            the user tag
	 */
	public User(Element userTag) {
		if (userTag.getElementsByTag("a").last() == null)
			return;
		this.name = userTag.getElementsByTag("a").last().text();
		this.url = userTag.getElementsByTag("a").last().attr("href");
		if (userTag.getElementsByClass("badgecount").size() > 2)
			this.bronze = Long.parseLong(userTag
					.getElementsByClass("badgecount").get(2).text());

		if (userTag.getElementsByClass("badgecount").size() > 1)
			this.silver = Long.parseLong(userTag
					.getElementsByClass("badgecount").get(1).text());

		if (userTag.getElementsByClass("badgecount").size() > 0)
			this.gold = Long.parseLong(userTag.getElementsByClass("badgecount")
					.get(0).text());
		this.reputation = userTag.getElementsByClass("reputation-score").text();
	}
}
