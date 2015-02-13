
import java.util.List;

import com.mongodb.WriteResult;

// TODO: Auto-generated Javadoc
/**
 * The Interface QuestionTemplate.
 */
public interface QuestionTemplate {

	/**
	 * Gets the all questions.
	 * 
	 * @return the all questions
	 */
	public List<Question> getAllQuestions();

	/**
	 * Save question.
	 * 
	 * @param Question
	 *            the question
	 */
	public void saveQuestion(Question Question);

	/**
	 * Gets the question.
	 * 
	 * @param id
	 *            the id
	 * @return the question
	 */
	public Question getQuestion(String id);

	/**
	 * Update question.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @return the write result
	 */
	public WriteResult updateQuestion(String id, String name);

	/**
	 * Delete question.
	 * 
	 * @param id
	 *            the id
	 */
	public void deleteQuestion(String id);

	/**
	 * Creates the collection.
	 */
	public void createCollection();

	/**
	 * Drop collection.
	 */
	public void dropCollection();
}
