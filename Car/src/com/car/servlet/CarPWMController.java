package com.car.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.StringUtil;
import com.pi4j.wiringpi.SoftPwm;

public class CarPWMController extends HttpServlet {

	private static int speed = 60;
	// create gpio controller
	static{
		com.pi4j.wiringpi.Gpio.wiringPiSetup();//初始化Wiringpi
		SoftPwm.softPwmCreate(0, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)
		SoftPwm.softPwmCreate(1, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)
		SoftPwm.softPwmCreate(2, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)
		SoftPwm.softPwmCreate(3, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)
	}
	/**
	 * Constructor of the object.
	 */
	public CarPWMController() {
		super();
		//pwm 控制  GPIO1支持PWM
//	    com.pi4j.wiringpi.Gpio.wiringPiSetup();//初始化Wiringpi
//	    SoftPwm.softPwmCreate(1, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)
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
		String direction = request.getParameter("direction");
		if (request.getParameter("speed")!=null) {
			int speedparm = Integer.parseInt(request.getParameter("speed"));
			speed = speedparm;
		}
		System.out.println(direction);
		if ("up".equals(direction)) {
			front();
		}else if ("down".equals(direction)) {
			back();
		}else if ("left".equals(direction)) {
			left();
		}else if ("right".equals(direction)) {
			right();
		}else if ("stop".equals(direction)) {
			stop();
		}else {
			stop();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("hello");
		out.flush();
		out.close();
	}
	
	private void front(){
		leftMotor(1);
		rightMotor(1);
	}
	
	private void back(){
		leftMotor(-1);
		rightMotor(-1);
	}
	
	private void left(){
		leftMotor(0);
		rightMotor(1);
	}
	
	private void right(){
		leftMotor(1);
		rightMotor(0);
	}
	
	private void stop(){
		leftMotor(0);
		rightMotor(0);
//		gpio.shutdown();
	}

	private void leftMotor(int direct){
		if (direct>0) {
			SoftPwm.softPwmWrite(0, speed);
			SoftPwm.softPwmWrite(1, 0);
		}else if(direct<0){
			SoftPwm.softPwmWrite(0, 0);
			SoftPwm.softPwmWrite(1, speed);
		}else {
			SoftPwm.softPwmWrite(0, 0);
			SoftPwm.softPwmWrite(1, 0);
		}
	}
	
	private void rightMotor(int direct){
		if (direct>0) {
			SoftPwm.softPwmWrite(2, speed);
			SoftPwm.softPwmWrite(3, 0);
		}else if(direct<0){
			SoftPwm.softPwmWrite(2, 0);
			SoftPwm.softPwmWrite(3, speed);
		}else {
			SoftPwm.softPwmWrite(2, 0);
			SoftPwm.softPwmWrite(3, 0);
		}
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
