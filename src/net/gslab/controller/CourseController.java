package net.gslab.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.dao.CourseDao;
import net.gslab.entity.Course;
import net.gslab.tools.FileUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Resource(name="courseDaoImpl")
	private CourseDao courseDao;
	@RequestMapping("getAll")
	public ModelAndView getAll(String view)
	{
		ModelAndView mav=new ModelAndView(view);
		mav.addObject("list",courseDao.loadAll());
		return mav;
	}
	@RequestMapping("add")
	public void add(Course c,HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		String filePath=request.getServletContext().getRealPath("/teaFiles");
		File file=FileUtil.renameDir(new File(filePath,c.getName()));
		if(!file.exists()) file.mkdirs();
		System.out.println(file.getPath());
		c.setFileDir(file.getName());
		if(courseDao.save(c))
		response.getWriter().println("添加成功");
		else response.getWriter().println("添加失败");
	}
	@RequestMapping("/delete")
	public void delete(String []id,HttpServletResponse response) throws IOException
	{
		if(id!=null)
		for(String i:id)
		{
			courseDao.remove(i);
		}
		response.getWriter().println("删除成功");
	}
	@RequestMapping("getOne")
	public ModelAndView getOne(String id,String view) throws UnsupportedEncodingException
	{
		ModelAndView mav=new ModelAndView(view);
		id=new String(id.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("Id:"+id);
		mav.addObject("one",courseDao.get(id));
		return mav;
	}
	@RequestMapping(value="update")
	public void update(Course c,int tId,HttpServletResponse response)throws IOException
	{
		c.settId(tId);
		if(!courseDao.update(c))
		{
			response.getWriter().println("设置过程中出错，请仔细检查你提交的申请");
			return;
		}
		response.getWriter().println("设置成功");
	}
}
