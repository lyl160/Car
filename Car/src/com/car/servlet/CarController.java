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

public class CarController extends HttpServlet {

	// create gpio controller
	final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput sign0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyGPIO0", PinState.LOW);
    final GpioPinDigitalOutput sign1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyGPIO1", PinState.LOW);
    final GpioPinDigitalOutput sign2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyGPIO2", PinState.LOW);
    final GpioPinDigitalOutput sign3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "MyGPIO3", PinState.LOW);

	/**
	 * Constructor of the object.
	 */
	public CarController() {
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
		gpio.shutdown();
	}

	private void leftMotor(int direct){
		if (direct>0) {
			sign0.high();
			sign1.low();
		}else if(direct<0){
			sign0.low();
			sign1.high();
		}else {
			sign0.low();
			sign1.low();
		}
	}
	
	private void rightMotor(int direct){
		if (direct>0) {
			sign2.high();
			sign3.low();
		}else if(direct<0){
			sign2.low();
			sign3.high();
		}else {
			sign2.low();
			sign3.low();
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
