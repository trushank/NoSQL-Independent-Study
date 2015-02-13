package com.trushank.dbperformance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;

// TODO: Auto-generated Javadoc
/**
 * CouchAccess.java
 * 
 * @author Trushank Date: Sep 25, 2014
 */

@SuppressWarnings("deprecation")
public class DB_Performance {
    
    /** The java. */
    static Course java;
    
    /** The Parallel. */
    static Course Parallel;
    
    /** The Distributed. */
    static Course Distributed;
    
    /** The dem. */
    static Course DEM;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String args[]) {
	java = new Course(1, 4, "Java", "HPB");
	Parallel = new Course(2, 4, "Parallel Computing", "Alan Kaminsky");
	DEM = new Course(3, 4, "DEM", "Xumin Liu");
	Distributed = new Course(4, 4, "Distributed Systems", "Kwon");

	// createDBCouch();
	// fillDBCouch(10000);
	// getAllDocs();
	// makeView();
	// getView();
	insertmySQL(100000);
	// insertMongo(100000);
	// getMongo();
	// getMySQL();
	// getAllDocsCouch();

    }

    // ***********************************COUCHDB***************************************************************
    /**
     * Gets the all docs couch.
     *
     * @return the all docs couch
     */
    public static void getAllDocsCouch() {
	String dbname = "test";
	Session dbSession = new Session("localhost", 5984);
	Database db = dbSession.getDatabase(dbname);

	long start = System.currentTimeMillis();
	System.out.println("Start Time" + start);
	ViewResults vr = db.getAllDocuments();
	System.out.println(vr.toString());
	long end = System.currentTimeMillis();
	System.out.println("End Time" + end);
	System.out.println("\n\n\nTotal Time" + (end - start));
    }

    /**
     * Creates the db couch.
     */
    public static void createDBCouch() {
	String dbname = "test";
	Session dbSession = new Session("localhost", 5984);
	dbSession.createDatabase(dbname);
    }

    /**
     * Fill db couch.
     *
     * @param size the size
     */
    public static void fillDBCouch(int size) {
	String dbname = "test";
	Session dbSession = new Session("localhost", 5984);
	Database db = dbSession.getDatabase(dbname);

	long start = System.currentTimeMillis();
	System.out.println("Start Time" + start);
	for (int i = 0; i < size; i++) {
	    Document doc = new Document();
	    // doc.setId(i+"");
	    doc.put("student_id", i);
	    doc.put("student_name", "Student_" + i);
	    doc.put("student_last_name", "Last_" + i);
	    doc.put("type", "student");
	    doc.put("course",
		    "{   \"array\": [     {     \"Name\": \"Java\",     \"Instructor\": \"HPB\",     \"Credits\": \"4\",     \"id\":\"1\"   },   {     \"Name\": \"Distributed Systems\",     \"Instructor\": \"Kwon\",     \"Credits\": \"4\",     \"id\":\"2\"   },      {     \"Name\": \"Parallel Computing\",     \"Instructor\": \"Alan Kaminsky\",     \"Credits\": \"4\",     \"id\":\"3\"   }, {     \"Name\": \"DEM\",     \"Instructor\": \"Xumin Liu\",     \"Credits\": \"4\",     \"id\":\"4\"   }   ]    }");
	    db.saveDocument(doc);
	}
	long end = System.currentTimeMillis();
	System.out.println("End Time" + end);
	System.out.println("Total Time" + (end - start));
    }

    // ********************************MYSQL***************************************************
    /**
     * Make view.
     */
    public static void makeView() {
	String dbname = "test";

	Session dbSession = new Session("localhost", 5984);
	Database db = dbSession.getDatabase(dbname);
	Document doc = new Document();
	doc.setId("_design/sumview");

	// JSON/JavaScript Map-Reduce function
	String str = "{ \"sum\": { \"map\": \"function(doc) { if (doc.type == 'Car') emit(doc.Name, doc.Price); }\", \"reduce\": \"function(keys,price){returnsum(prices); }\" } }";
	// String str =
	// "{\"javalanguage\": {\"map\": \"function(doc) { if (doc.Language == 'Java')  emit(null, doc) } \"}, \"java_and_se\": {\"map\": \"function(doc) { if (doc.Language == 'Java' & doc.Designation == 'SE')  emit(null, doc) } \"}}";

	doc.put("views", str);
	db.saveDocument(doc);

    }

    /**
     * Gets the my sql.
     *
     * @return the my sql
     */
    public static void getMySQL() {
	String query = "SELECT * FROM performanceevaluation.student inner join performanceevaluation.student_course on stud_id=student_id  inner join course on course.id=course_id;";
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	try {
	    connection = ConnectionConfiguration.getConnection();
	    long start = System.currentTimeMillis();
	    System.out.println("Start Time" + start);

	    preparedStatement = connection.prepareStatement(query);
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		System.out.println("Name: " + rs.getString(1) + " "
			+ rs.getString(2) + "\t Course: Name:"
			+ rs.getString(8) + " Credits:" + rs.getString(9)
			+ " Instructor:" + rs.getString(10) + " ");
	    }
	    long end = System.currentTimeMillis();
	    System.out.println("End Time" + end);
	    System.out.println("Total Time" + (end - start));
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }

	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * Gets the view.
     *
     * @return the view
     */
    public static void getView() {
	try {
	    HttpClient httpclient = new DefaultHttpClient();

	    HttpGet get = new HttpGet(
		    "http://localhost:5984/car/_design/sum/_view/sum");

	    HttpResponse response = httpclient.execute(get);
	    HttpEntity entity = response.getEntity();
	    InputStream instream = entity.getContent();

	    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    instream));
	    String strdata = null;

	    while ((strdata = reader.readLine()) != null) {
		System.out.println(strdata);
	    }
	} catch (Exception e) {
	}
    }

    /**
     * Insertmy sql.
     *
     * @param size the size
     */
    public static void insertmySQL(int size) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	try {
	    connection = ConnectionConfiguration.getConnection();
	    preparedStatement = connection
		    .prepareStatement("Delete from student");
	    long strt = System.currentTimeMillis();
	    preparedStatement.execute();
	    System.out.println("Time Delete: "
		    + (System.currentTimeMillis() - strt));
	    long start = System.currentTimeMillis();
	    System.out.println("Start Time" + start);
	    for (int i = 0; i < size; i++) {
		preparedStatement = connection
			.prepareStatement("INSERT INTO student (first_name,last_name)"
				+ "VALUES (?, ?)");
		preparedStatement.setString(1, "student" + i);
		preparedStatement.setString(2, "last" + i);
		preparedStatement.executeUpdate();

		for (int j = 1; j < 5; j++) {
		    preparedStatement = connection
			    .prepareStatement("INSERT INTO student_course (course_id,student_id)"
				    + "VALUES (?, ?)");
		    preparedStatement.setInt(1, j);
		    preparedStatement.setInt(2, i);
		    preparedStatement.executeUpdate();
		}
	    }
	    long end = System.currentTimeMillis();
	    System.out.println("End Time" + end);
	    System.out.println("Total Time" + (end - start));
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (preparedStatement != null) {
		try {
		    preparedStatement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }

	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    // **************************MONGODB*****************************************
    /**
     * Insert mongo.
     *
     * @param size the size
     */
    public static void insertMongo(int size) {
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/spring/applicationContext.xml");

	MongoInterface repository = context.getBean(MongoImpl.class);
	long strt = System.currentTimeMillis();
	repository.dropCollection();
	System.out.println("Time Delete: "
		+ (System.currentTimeMillis() - strt));

	repository.createCollection();
	long start = System.currentTimeMillis();
	System.out.println("Start: " + start);

	for (int i = 0; i < size; i++) {
	    Student stud = new Student(i + "", "Student" + i, "Last" + i);
	    stud.getCourses().add(java);
	    stud.getCourses().add(DEM);
	    stud.getCourses().add(Distributed);
	    stud.getCourses().add(Parallel);

	    repository.saveStudent(stud);

	}
	long end = System.currentTimeMillis();
	System.out.println("End: " + end);
	System.out.println("Total Time: " + (end - start));

    }

    /**
     * Gets the mongo.
     *
     * @return the mongo
     */
    public static void getMongo() {
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/spring/applicationContext.xml");

	MongoInterface repository = context.getBean(MongoImpl.class);

	long start = System.currentTimeMillis();
	System.out.println("Start: " + start);
	List<Student> students = repository.getAllStudents();

	for (int i = 0; i < students.size(); i++) {
	    Student stud = students.get(i);
	    System.out.println("Name: " + stud.getFirst_name() + " "
		    + stud.getLast_name());
	    List<Course> courses = stud.getCourses();
	    for (int j = 0; j < courses.size(); j++) {
		Course c = courses.get(j);
		System.out.println("Course:\tName: " + c.name + " Instructor: "
			+ c.instructor + " Credits: " + c.credits);
	    }
	}
	System.out.println(students.size());
	long end = System.currentTimeMillis();
	System.out.println("End: " + end);
	System.out.println("Total Time: " + (end - start));
    }

}
