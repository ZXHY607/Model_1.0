package net.gslab.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.dao.CourseDao;
import net.gslab.dao.TeacherDao;
import net.gslab.entity.Course;
import net.gslab.entity.Teacher;
import net.gslab.tools.FileUtil;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.Field;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	@Resource 
	private ServletContext servletContext;
	@Resource(name="teacherDaoImpl")
	private TeacherDao teacherDao;
	@Resource(name="courseDaoImpl")
	private CourseDao courseDao;
	@RequestMapping(value="/listCategory")
	public ModelAndView listCategory()
	{
		
		List<Course> list=courseDao.loadAll();
		List<String> fileList=new ArrayList<String>();
		List<String> pathList=new ArrayList<String>();
		for(int i=0;i<list.size();i++)
		{
			Course c=list.get(i);
			fileList.add(c.getName());
			pathList.add("/Model/file/listFile?filePath="+getCourseDir(c));
		}
		ModelAndView mav=new ModelAndView("../view_login/listCategory.jsp");
		mav.addObject("fileList",fileList);
		mav.addObject("pathList", pathList);
		return mav;
	}
	@RequestMapping(value="/listFile")
	public ModelAndView listFile(String filePath,HttpServletRequest request)
	{
		String msg="路径出错";
		if(!validate(filePath)) 
			return illeageAccess(msg);
		ModelAndView mav=new ModelAndView("../view_login/listFile.jsp");
		File file=new File(filePath);
		String baseDir=getBaseDir();
		String parent=file.getParent();
		if(parent.length()<=baseDir.length())
			parent="../file/listCategory";
		else parent="../file/listFile?filePath="+parent;
		if(file.isDirectory())
		{
			mav.addObject("filePath", filePath);
			mav.addObject("parent", parent);
			File []files=file.listFiles();
			mav.addObject("files", files);
			
		}
		return mav;
	}
	@RequestMapping(value="/download")
	public ModelAndView download(HttpServletResponse response,String filePath,HttpServletRequest request)
	{
		String msg="路径出错";
		if(!validate(filePath)) 
			return illeageAccess(msg);
		File file=new File(filePath);
		if(!file.exists()) return illeageAccess("要下载的文件不存在");
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment;filename="+new File(filePath).getName());

			FileInputStream fis=null;
			BufferedInputStream bis=null;
			try {
				fis = new FileInputStream(filePath);
				bis=new BufferedInputStream(fis);
				OutputStream os=response.getOutputStream();
				byte[] bytes=new byte[4096];
				int i;
				while((i=bis.read(bytes))>0)
				{
					os.write(bytes, 0, i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	@RequestMapping(value="tListFile")
	public ModelAndView tListFile(String filePath,HttpServletRequest request)
	{
		String msg="路径出错";
		Teacher t=getSessionTeacher(request);
		String baseDir=getCourseDir(t);
		File root=new File(baseDir);
		if(!root.exists()) root.mkdirs();
		if(!validate(filePath)) filePath=baseDir; 
		ModelAndView mav=new ModelAndView("/view_tea/manageFile.jsp");
		File file=new File(filePath);
		String parent=file.getParent();
		if(parent.length()<baseDir.length())
			parent="/Model/file/tListFile?filePath="+baseDir;
		else parent="/Model/file/tListFile?filePath="+parent;
		if(file.isDirectory())
		{
			mav.addObject("filePath", filePath);
			mav.addObject("parent", parent);
			File []files=file.listFiles();
			mav.addObject("files", files);
			mav.addObject("root", t.getFileDirectory());
			
		}
		return mav;
	}
	@RequestMapping(value="delete")
	public  ModelAndView delete(String [] files,HttpServletRequest request,String filePath)
	{
		Teacher t=getSessionTeacher(request);
		String baseDir=getCourseDir(t);
		for(String file:files)
		{
			if(validate(file,baseDir)) {
				File f=new File(file);
				deleteFile(f);
			}
		}
		return tListFile(filePath, request);
	}
	private void deleteFile(File file)
	{
		if(file.isFile()){file.delete();return;}
		File[] fs=file.listFiles();
		for(File child:fs)
			deleteFile(child);
		file.delete();
	}
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ModelAndView upload(MultipartFile file,String filePath,HttpServletRequest request)
	{
		String msg="路径出错";
		String fullName=file.getOriginalFilename();
		String name=fullName.substring(0,fullName.lastIndexOf("."));
		String suffix=fullName.substring(fullName.lastIndexOf('.'));
		if(!validate(filePath)) return illeageAccess(msg);
		File f=FileUtil.renameFile(filePath+"/"+name,suffix);
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis=file.getInputStream();
			fos=new FileOutputStream(f);
			int len;
			byte[] b=new byte[8*1024];
			while((len=fis.read(b))>0)
			{
				fos.write(b);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return tListFile(filePath, request);
	}
	@RequestMapping(value="newFolder")
	public ModelAndView newFolder(HttpServletRequest request,String filePath,String fileName)
	{
		Teacher t=getSessionTeacher(request);
		String baseDir=getCourseDir(t);
		if(validate(filePath, baseDir))
		{
			File f=FileUtil.renameDir(filePath+"/"+fileName);
			f.mkdir();
		}
		return tListFile(filePath, request);
	}
	@RequestMapping(value="changeRoot")
	public ModelAndView changeRoot(HttpServletRequest request,String root,String filePath)
	{
		
		Teacher t=getSessionTeacher(request);
		File f=new File(getCourseDir(t));
		if(!f.exists()) return illeageAccess("你的文件夹不存在");
		t.setFileDirectory(root);
		teacherDao.update(t);
		return tListFile(filePath, request);
	}
	private  String getBaseDir()
	{
		return servletContext.getRealPath("/teaFiles");
	}
	private String getCourseDir(Course c)
	{
		return getBaseDir()+"\\"+c.getFileDir();
	}
	private String getCourseDir(Teacher t)
	{
		return getCourseDir(courseDao.find("from Course where tId='"
				+t.getTeacherId()+"'").get(0));
	}
	private boolean validate(String filePath)
	{
		
		String baseDir=getBaseDir();
		if(filePath==null||filePath.length()<baseDir.length()) return false;
		if(!filePath.substring(0,baseDir.length()).equals(baseDir)) return false;
		
		return true;
	}
	private boolean validate(String filePath,String baseDir)
	{
		if(filePath==null||filePath.length()<baseDir.length()) return false;
		if(!filePath.substring(0,baseDir.length()).equals(baseDir)) return false;
		return true;
	}
	private ModelAndView illeageAccess(String msg)
	{
		ModelAndView mav =new ModelAndView("../view_all/result.jsp");
		mav.addObject("msg",msg);
		return mav;
	}
	private boolean hasCourseware(String filePath)
	{
		File file=new File(filePath);
		if(!file.exists()) file.mkdirs();
		String[] s=file.list();
		if(s==null||s.length==0) return false;
		return true;
	}
	
	@RequestMapping(value="/test")
	public void test()
	{
		System.out.println("in test");
	}
}
