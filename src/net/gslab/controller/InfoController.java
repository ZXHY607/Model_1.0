package net.gslab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.entity.Info;
import net.gslab.service.InfoService;
import net.gslab.setting.PageBean;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info")
public class InfoController extends BaseController{

	@Resource(name="infoServiceImpl")
	private InfoService infoService;
	
	//从后台添加新闻
	@RequestMapping(value="/add")
	public @ResponseBody String add(Info info,HttpServletRequest request)
	{
		if(info==null) return "error";
		//进行格式判断,先判断是否为空
		if(getSessionType(request).equals("teacher"))
		{
			info.setWho(0);
			info.setPublishName(getSessionTeacher(request).getTeacherName());
			info.setPublishId(getSessionTeacher(request).getTeacherId());
		}
		if(getSessionType(request).equals("admin"))
		{
			info.setWho(1);
			info.setPublishName(getSessionAdmin(request).getAdminName());
			info.setPublishId(getSessionAdmin(request).getAdminId());
		}
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time=df.format(new Date());
		info.setPublishDate(time);
		infoService.save(info);
		return "success";
	}
	
	//分页例子，
       @RequestMapping(value = "/getPage")
	   public ModelAndView getPage(Integer pg) {
		/**
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
		//return infoService.getPage(pageIndex);  //使用默认的pageSize
    	int pageIndex;
    	if(pg==null) pageIndex=1;
    	else pageIndex=pg;
    	PageBean page=infoService.getPage("from Info n order by n.publishDate desc",pageIndex);  //自定义pageSize为2 
    	ModelAndView mav=new ModelAndView("/view_admin/m_infoList.jsp");
		PageBean pageBean=infoService.getPage(pageIndex);
		mav.addObject("pageBean",pageBean);
		return mav;
	}
        //分页2，返回分页，附带新闻总数，已测试，可以使用；
        //参数pageIndex指定是第几页
       @RequestMapping(value = "/getPage_2", method = RequestMethod.GET)
       public @ResponseBody PageBean<Info> list2(HttpServletRequest request,
   			HttpServletResponse response,@RequestParam(value="pageIndex")Integer pageIndex,  int pageSize)
   			{
    	   /**
   		 * @param pageIndex   请求的页码
            * @param pageSize   每页的记录条数
            * @param 
   		 */
    	   PageBean page=infoService.getPage("from Info n order by n.publishDate desc",pageIndex,pageSize);  
    	   return page;
   			}
       
       //返回totalsize，即数据库里面的新闻总数,已测试，可以使用
       @RequestMapping(value = "/getTotalcount", method = RequestMethod.GET)
       public @ResponseBody  long getTotalcount()
       {
    	   return  infoService.getPage(1).getTotalCount();
       }
       
     //分页，返回首页显示的9条最新新闻
       @RequestMapping(value = "/get9Page", method = RequestMethod.GET)
	   public @ResponseBody List<Info>  list2(HttpServletRequest request,
			HttpServletResponse response) {
    	   //,@RequestParam(value="pageIndex")Integer pageIndex
		/**
		 * 
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
    	PageBean page=infoService.getPage("from Info n order by n.publishDate desc",1,9); 	//按日期排序	
		List<Info> data=page.getData();
		//把不用的属性设置为null(主要是content，其余占用的空间小，减少占用的带宽)
		for(int i=0;i<data.size();i++)
		{
			Info temp=data.get(i);
			temp.setContent(null);
		}		
		return page.getData();
	}
       
     //分页，返回首页显示的3条最新新闻，测试过，可以用
       @RequestMapping(value = "/get6Page", method = RequestMethod.GET)
	   public @ResponseBody List<Info>  get3Page(HttpServletRequest request,
			HttpServletResponse response) {
    	   //,@RequestParam(value="pageIndex")Integer pageIndex
		/** 
		 * 
		 * @param pageIndex   请求的页码 
         * @param pageSize   每页的记录条数 
         * @param 
		 */ 
    	PageBean page=infoService.getPage("from Info n order by n.publishDate desc",1,6); 	//按日期排序	
		return page.getData();
	}
       
    //删除新闻,前台传入ID，根据ID删除新闻，删除后，检验是否删除成功
   	@RequestMapping("/delete")
   	public void delete(int []id,HttpServletResponse response) throws IOException
	{
		if(id!=null)
		{
			for(int i=0;i<id.length;i++)
				infoService.delete(id[i]);
		}
		response.getWriter().print("删除成功");
	}
   	
  //返回全部新闻 ，返回json串，这就是一个测试
   	@RequestMapping(value="/infoList") 
	public  @ResponseBody List<Info> show(Model model)  //后台往前台传输数据时用model
	{
		List<Info> info=infoService.listInfo();
		model.addAttribute("info",info);   //等效于request和respond
		return info;
	}

	//根据新闻id，获得数据库中的新闻
	@RequestMapping(value ="/getByID")
	public @ResponseBody Info getByID(int id)
	{
		return infoService.get(id);
	}
	@RequestMapping(value ="/detail")
	public ModelAndView detail(int id)
	{
		ModelAndView mav =new ModelAndView("/view_all/info_detail.jsp");
		Info n=infoService.get(id);
		mav.addObject("ele", n);
		return mav;
	}
	
}
