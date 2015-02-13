package com.trushank.dbperformance;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {

	@Id
	private String id;
	


	private String first_name;
	private String last_name;

	public List<Course> courses;
	public Student(String id, String first_name, String last_name) {
	    super();
	    this.id = id;
	    this.first_name = first_name;
	    this.last_name = last_name;
	    courses=new ArrayList<Course>();
	}
	public String getId() {
	    return id;
	}
	public void setId(String id) {
	    this.id = id;
	}
	public String getFirst_name() {
	    return first_name;
	}
	public void setFirst_name(String first_name) {
	    this.first_name = first_name;
	}
	public String getLast_name() {
	    return last_name;
	}
	public void setLast_name(String last_name) {
	    this.last_name = last_name;
	}
	public List<Course> getCourses() {
	    return courses;
	}
	public void setCourses(List<Course> courses) {
	    this.courses = courses;
	}

	
	
	

	}
