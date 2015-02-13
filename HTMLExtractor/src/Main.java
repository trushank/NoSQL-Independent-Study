
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext.xml");

		QuestionTemplate repository = context.getBean(QuestionImpl.class);

		String URL = "http://www.programmableweb.com/apis/directory?page=";
		for (int num = 1; num < 80; num++) {
			String apiURL = URL + num;
			String stackQueryString = "http://stackoverflow.com/search?page=1&tab=relevance&q=";
			try {
				Document apiPage = Jsoup.connect(apiURL).get();
				Elements apiElements = apiPage
						.getElementsByClass("views-field-title");
				System.out.println(apiElements.size()
						+ "**********************");
				int w = 0;
				for (Element element : apiElements) {


					if (element.toString().contains("mashup"))
						continue;
					System.out.println("\nPage: " + num + " Item no:" + (++w));
					String apiName = element.text();
					System.out.println("API: " + apiName);
					Thread.sleep(100);
					Document stackSearchPage = Jsoup.connect(
							stackQueryString + apiName + "+api").get();
					Elements searchResults = stackSearchPage
							.getElementsByClass("result-link");

					for (Element element2 : searchResults) {
						String queriedURL = "http://stackoverflow.com/"
								+ element2.getElementsByTag("a").first()
										.attr("href");

						Document e;
						try {

							e = Jsoup.connect(queriedURL).get();
							System.out.println(queriedURL);
							repository.saveQuestion(new Question(e));

						} catch (Exception e1) {
							Thread.sleep(30000);
							e1.printStackTrace();
						}

					}
				}

			} catch (IOException e2) {
				
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				e2.printStackTrace();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}

	}
}
