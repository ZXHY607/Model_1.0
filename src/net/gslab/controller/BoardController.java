package net.gslab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.entity.Board;
import net.gslab.service.BoardService;
import net.gslab.setting.CommonConstant;
import net.gslab.setting.PageBean;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="boardController")
@RequestMapping("/board")
public class BoardController extends BaseController{

	@Resource(name="boardServiceImpl")
	private BoardService boardService;
	
	//github测试，2015年7月3日21:51:36
	//github测试2，2015年7月3日22:17:13
	//github测试3，2015年7月3日22:38:42
	
	
	
	//从后台添加新闻
	@RequestMapping(value="/add")
	public @ResponseBody String add(Board board,HttpServletRequest request)
	{
		if(board==null) return "error";
		//进行格式判断,先判断是否为空
		if(getSessionType(request).equals("teacher"))
		{
			board.setWho(0);
			board.setPublishName(getSessionTeacher(request).getTeacherName());
			board.setPublishId(getSessionTeacher(request).getTeacherId());
		}
		if(getSessionType(request).equals("admin"))
		{
			board.setWho(1);
			board.setPublishName(getSessionAdmin(request).getAdminName());
			board.setPublishId(getSessionAdmin(request).getAdminId());
		}
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time=df.format(new Date());
		board.setPublishDate(time);
		boardService.save(board);
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
		//return boardService.getPage(pageIndex);  //使用默认的pageSize
    	int pageIndex;
    	if(pg==null) pageIndex=1;
    	else pageIndex=pg;
    	PageBean page=boardService.getPage("from Board n order by n.publishDate desc",pageIndex);  //自定义pageSize为2 
    	ModelAndView mav=new ModelAndView("/view_admin/m_boardList.jsp");
		PageBean pageBean=boardService.getPage(pageIndex);
		mav.addObject("pageBean",pageBean);
		return mav;
	}
        //分页2，返回分页，附带新闻总数，已测试，可以使用；
        //参数pageIndex指定是第几页
       @RequestMapping(value = "/getPage_2", method = RequestMethod.GET)
       public @ResponseBody PageBean<Board> list2(HttpServletRequest request,
   			HttpServletResponse response,@RequestParam(value="pageIndex")Integer pageIndex,  int pageSize)
   			{
    	   /**
   		 * @param pageIndex   请求的页码
            * @param pageSize   每页的记录条数
            * @param 
   		 */
    	   PageBean page=boardService.getPage("from Board n order by n.publishDate desc",pageIndex,pageSize);  
    	   return page;
   			}
       
       //返回totalsize，即数据库里面的新闻总数,已测试，可以使用
       @RequestMapping(value = "/getTotalcount", method = RequestMethod.GET)
       public @ResponseBody  long getTotalcount()
       {
    	   return  boardService.getPage(1).getTotalCount();
       }
       
     //分页，返回首页显示的9条最新新闻
       @RequestMapping(value = "/get9Page", method = RequestMethod.GET)
	   public @ResponseBody List<Board>  list2(HttpServletRequest request,
			HttpServletResponse response) {
    	   //,@RequestParam(value="pageIndex")Integer pageIndex
		/**
		 * 
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
    	PageBean page=boardService.getPage("from Board n order by n.publishDate desc",1,9); 	//按日期排序	
		List<Board> data=page.getData();
		//把不用的属性设置为null(主要是content，其余占用的空间小，减少占用的带宽)
		for(int i=0;i<data.size();i++)
		{
			Board temp=data.get(i);
			temp.setContent(null);
		}		
		return page.getData();
	}
       
     //分页，返回首页显示的3条最新新闻，测试过，可以用
       @RequestMapping(value = "/get6Page", method = RequestMethod.GET)
	   public @ResponseBody List<Board>  get3Page(HttpServletRequest request,
			HttpServletResponse response) {
    	   //,@RequestParam(value="pageIndex")Integer pageIndex
		/** 
		 * 
		 * @param pageIndex   请求的页码 
         * @param pageSize   每页的记录条数 
         * @param 
		 */ 
    	PageBean page=boardService.getPage("from Board n order by n.publishDate desc",1,6); 	//按日期排序	
		return page.getData();
	}
       
    //删除新闻,前台传入ID，根据ID删除新闻，删除后，检验是否删除成功
   	@RequestMapping("/delete")
   	public void delete(int []id,HttpServletResponse response) throws IOException
	{
		if(id!=null)
		{
			for(int i=0;i<id.length;i++)
				boardService.delete(id[i]);
		}
		response.getWriter().print("删除成功");
	}
   	
  //返回全部新闻 ，返回json串，这就是一个测试
   	@RequestMapping(value="/boardList") 
	public  @ResponseBody List<Board> show(Model model)  //后台往前台传输数据时用model
	{
		List<Board> board=boardService.listBoard();
		model.addAttribute("board",board);   //等效于request和respond
		return board;
	}

	//根据新闻id，获得数据库中的新闻
	@RequestMapping(value ="/getByID")
	public @ResponseBody Board getByID(int id)
	{
		return boardService.get(id);
	}
	@RequestMapping(value ="/detail")
	public ModelAndView detail(int id)
	{
		ModelAndView mav =new ModelAndView("/view_all/board_detail.jsp");
		
		Board n=boardService.get(id);
		mav.addObject("ele", n);
		return mav;
	}
	
}
