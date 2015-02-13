

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import com.mongodb.WriteResult;

public class QuestionImpl implements QuestionTemplate {

	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Question> getAllQuestions() {
		return mongoTemplate.findAll(Question.class);
	}

	public void saveQuestion(Question Question) {
		mongoTemplate.insert(Question);
	}

	public Question getQuestion(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				Question.class);
		
	}

	
	public WriteResult updateQuestion(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), Question.class);
	}

	
	public void deleteQuestion(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Question.class);
	}

	public void createCollection() {
		if (!mongoTemplate.collectionExists(Question.class)) {
			mongoTemplate.createCollection(Question.class);
		}
	}

	public void dropCollection() {
		if (mongoTemplate.collectionExists(Question.class)) {
			mongoTemplate.dropCollection(Question.class);
		}
	}
}
