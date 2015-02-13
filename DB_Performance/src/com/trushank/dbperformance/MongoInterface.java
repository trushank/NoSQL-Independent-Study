package com.trushank.dbperformance;


import java.util.List;

import com.mongodb.WriteResult;

public interface MongoInterface {

	public List<Student> getAllStudents();

	public void saveStudent(Student student);

	public Student getStudent(String id);

	public WriteResult updateStudent(String id, String name);

	public void deleteStudent(String id);

	public void createCollection();

	public void dropCollection();
}
