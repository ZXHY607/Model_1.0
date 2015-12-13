package net.gslab.tools;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;

import com.mchange.io.FileUtils;

public class FileUtil {
	public static String[] vSuff={"mp4","wmv","avi","3gp","ogg","flv"};
	public static File renameDir(String filePath)
	{
		int i=1;
		File f=new File(filePath);
		while(f.exists())
		{
			f=new File(filePath+i);
			i++;
		}
		return f;
	}
	
	public static File renameDir(File f)
	{
		return renameDir(f.getPath());
	}
	public static File renameFile(String filePath,String suffix)
	{
		int i=1;
		File f=new File(filePath+suffix);
		while(f.exists())
		{
			f=new File(filePath+i+suffix);
			i++;
		}
		return f;
	}
	public static void main(String args[]) throws IOException{
		/*for(int i=0;i<5;i++)
		{
			File file=renameFile("E:/FileUtil/test",".txt");
			file.createNewFile();
			System.out.println(file.getPath());
		}*/
		System.out.println(getSuffix(new File("E:\\test\\1.3gp")));
		
	}
	public static boolean isVideo(File file)
	{
		String suf=getSuffix(file);
		for(String s:vSuff) if(s.equals(suf)) return true;
		return false;
	}
	public static String getSuffix(File file) {
        String fileName=file.getName();
        return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
    }
	public static String changeToServerURL(String fileUrl,String serverRealPath)
	{
		String ret="/Model/"+fileUrl.substring(serverRealPath.length());
		return ret.replace('\\', '/');
		
	}
	
}
