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

public class TestController extends HttpServlet {

	// create gpio controller
    final GpioController gpio = GpioFactory.getInstance();
    
    final GpioPinDigitalOutput motor0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyGPIO0", PinState.LOW);
    final GpioPinDigitalOutput motor1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyGPIO1", PinState.LOW);
    final GpioPinDigitalOutput motor2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyGPIO2", PinState.LOW);
    final GpioPinDigitalOutput motor3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "MyGPIO3", PinState.LOW);

	/**
	 * Constructor of the object.
	 */
	public TestController() {
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
		try {
			//创建GPIO控制器
	        
	        motor0.high();
	        motor1.low();
	        System.out.println("--> motor0.high motor1.low");
			//延时5秒
	        Thread.sleep(2000);
	        motor0.low();
	        motor1.low();
	        
	        
	        motor2.high();
	        motor3.low();
	        System.out.println("--> motor0.high motor1.low");
	        Thread.sleep(2000);
			//延时5秒
	        motor2.low();
	        motor3.low();
	        gpio.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("hello");
		out.flush();
		out.close();
	}

	
	private  void front(){
		
		
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
