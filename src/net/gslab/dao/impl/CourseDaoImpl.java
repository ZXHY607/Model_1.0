package net.gslab.dao.impl;

import org.springframework.stereotype.Repository;

import net.gslab.dao.CourseDao;
import net.gslab.entity.Course;
@Repository("courseDaoImpl")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao  {

}
