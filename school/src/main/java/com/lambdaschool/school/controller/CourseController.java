package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private void Log(HttpServletRequest req){
        logger.info(req.getMethod() + " " + req.getRequestURI() + " Accessed");
    }
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses(HttpServletRequest req)
    {
        Log(req);
        ArrayList<Course> myCourses = courseService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses(HttpServletRequest req)
    {
        Log(req);
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long courseid, HttpServletRequest req)
    {
        Log(req);
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
