package com.car.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PicController extends HttpServlet {


	/**
	 * Constructor of the object.
	 */
	public PicController() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String result= "";
		try {
			download("http://localhost:8090/?action=snapshot",
					"image"+sdf.format(new Date())+".jpg", "/home/pi/CarPic");
			result = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "fail";
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	
public static void download(String urlString, String filename,String savePath) throws Exception {  
    // 构造URL  
    URL url = new URL(urlString);  
    // 打开连接  
    URLConnection con = url.openConnection();  
    //设置请求超时为5s  
    con.setConnectTimeout(5*1000);  
    // 输入流  
    InputStream is = con.getInputStream();  
  
    // 1K的数据缓冲  
    byte[] bs = new byte[1024];  
    // 读取到的数据长度  
    int len;  
    // 输出的文件流  
   File sf=new File(savePath);  
   if(!sf.exists()){  
       sf.mkdirs();  
   }  
   OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename);  
    // 开始读取  
    while ((len = is.read(bs)) != -1) {  
      os.write(bs, 0, len);  
    }  
    // 完毕，关闭所有链接  
    os.close();  
    is.close();  
}   
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
