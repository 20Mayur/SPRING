package com.Springboot.MySql.Service;

import java.util.List;

import com.Springboot.MySql.Entities.CourseModel;

public interface CourseService {
	public List<CourseModel> getCourse();

	public CourseModel getCourse(long CourseId);

	public CourseModel addCourse(CourseModel courseModel);
	
	public CourseModel updateCourse(CourseModel courseModle);
	
	public void  deleteCourse(long parselong);
}
