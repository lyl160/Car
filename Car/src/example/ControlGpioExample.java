package example;
/*
 * **********************************************************************
 * Pi4J例程
 * 注释:hbc QQ:1292381057
 * 例程出品:pi4j
 * 控制GPIO例程 
 * 
 * 可以转载，但不要修改这里 
 * 获取更多信息，可以到Pi4J官网:  http://www.pi4j.com/
 * **********************************************************************
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
//加载Pi4J的GPIO库
/**
 * This example code demonstrates how to perform simple state
 * control of a GPIO pin on the Raspberry Pi.  
 * 
 * @author Robert Savage
 */
public class ControlGpioExample {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");
        
        //创建GPIO控制器
        final GpioController gpio = GpioFactory.getInstance();
        
        //初始化GPIO控制引脚#01，然后变为高电平
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        //为该引脚设置关机的状态
        pin.setShutdownOptions(true, PinState.LOW);
		//(开启--高电平  关闭--低电平)
        System.out.println("--> GPIO state should be: ON");
		//延时5秒
        Thread.sleep(5000);
        
        //将#01设置为低电平
        pin.low();
        System.out.println("--> GPIO state should be: OFF");
		//延时5秒
        Thread.sleep(5000);

        //切换#01引脚状态(高电平)
        pin.toggle();
        System.out.println("--> GPIO state should be: ON");
		//延时5秒
        Thread.sleep(5000);

        //切换#01引脚状态(低电平)
        pin.toggle();
        System.out.println("--> GPIO state should be: OFF");
		//延时5秒
        Thread.sleep(5000);

        //打开引脚#1 1秒
        System.out.println("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000, true); //设置为“真” 使用阻塞调用
        
        //停止所有GPIO活动/线程并关闭GPIO控制器
        //（这种方法会强行关闭所有GPIO监控线程和任务计划）
        gpio.shutdown();
    }
}
