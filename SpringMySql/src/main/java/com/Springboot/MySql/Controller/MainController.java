package com.Springboot.MySql.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.MySql.Dao.CourseDao;
import com.Springboot.MySql.Entities.CourseModel;
import com.Springboot.MySql.Service.CourseService;

@RestController
@RequestMapping()
public class MainController {
	
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseService courseService;
	
	Logger logger=LoggerFactory.getLogger(MainController.class);
	@GetMapping("/course")
	public List<CourseModel> getCourses(){
		logger.info("[getCourse]");
		return this.courseService.getCourse();
	}
	 
	@GetMapping("/Course/{CourseId}")
	public CourseModel getCourse(@PathVariable String CourseId) {
		logger.info("[getCourse] info message");
		return this.courseService.getCourse(Long.parseLong(CourseId));
	}
	@PostMapping("/Course")
	public CourseModel AddCourse(@RequestBody CourseModel courseModel) {
		return this.courseService.addCourse(courseModel);
	}
    @PatchMapping("/Course")
	public CourseModel updateCourse(@RequestBody CourseModel courseModel) {
		return this.courseService.updateCourse(courseModel);
		
	}
    @DeleteMapping("/delete")
    public void deleteCourse(@PathVariable String CourseId) {
    	 this.courseService.deleteCourse(Long.parseLong(CourseId));
    }
    
    @GetMapping("/getbyuuid/{uuid}")
    public List<CourseModel> getByUuid(@PathVariable UUID uuid)
    {
    	return courseDao.findByUuid(uuid);
    }
    
    @GetMapping("/getbycreatedon/{uuid}")
    public CourseModel getCourseLatestVersion(@PathVariable UUID uuid)
    {
        List<CourseModel>latest=courseDao.findByUuid(uuid);
        return latest.stream().max(Comparator.comparing(CourseModel::getVersion)).
        orElse(null);
    }
    @GetMapping("/latestbyquery/{uuid}")
    public CourseModel getByLatestVersion(@PathVariable UUID uuid)
    {
    	 return courseDao.getlatestVersion(uuid);
    }
   
}

