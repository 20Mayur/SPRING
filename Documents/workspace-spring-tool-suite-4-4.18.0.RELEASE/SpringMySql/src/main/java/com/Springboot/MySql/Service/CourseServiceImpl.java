package com.Springboot.MySql.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.MySql.Dao.CourseDao;
import com.Springboot.MySql.Entities.CourseModel;


@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	List<CourseModel>list;
	
	public CourseServiceImpl() {
     list=new ArrayList<>();
   /*list.add(new CourseModel(1435,"Mayur","Java Course","This course contains the basic learning of java",));
     list.add(new CourseModel(1436,"Pratik","Python Course","This course contains the basic learning of python"));*/
	}

	@Override
	public List<CourseModel> getCourse() {
		return courseDao.findAll();
	}

	@Override
	public CourseModel getCourse(long CourseId) {
	  
		/*CourseModel c=null;
		for(CourseModel course:list) {
			if(course.getId()==CourseId) {
				c=course;
				break;
			}
			
		}*/
		
		
		return courseDao.getOne(CourseId);
	}

	@Override
	public CourseModel addCourse(CourseModel courseModel) {
	 // list.add(courseModel);
		return courseDao.save(courseModel);
	}

	@Override
	public CourseModel updateCourse(CourseModel courseModel) {
		/*list.forEach(e ->{
			if(e.getId()==courseModel.getId()) {
				e.setTitle(courseModel.getTitle());
				e.setDescription(courseModel.getDescription());
			}
		}
		
				);*/
		
		return courseDao.save(courseModel);
	}

	@Override
	public void deleteCourse(long parselong) {
		CourseModel entity=courseDao.getOne(parselong);
		 courseDao.delete(entity);
		
	}
	

	


	

}
