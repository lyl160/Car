package example;
/*
 * **********************************************************************
 * Pi4J例程
 * 注释:hbc QQ:1292381057
 * 例程出品:pi4j
 * PWM控制LED灯亮度例程 
 * 
 * 可以转载，但不要修改这里 
 * 获取更多信息，可以到Pi4J官网:  http://www.pi4j.com/
 * **********************************************************************
 */

import com.pi4j.wiringpi.SoftPwm;//加载Wiringpi中的PWM库

public class WiringPiSoftPWMExample {
    
    public static void main(String[] args) throws InterruptedException {
        
        
        com.pi4j.wiringpi.Gpio.wiringPiSetup();//初始化Wiringpi

        
        SoftPwm.softPwmCreate(1, 0, 100);//创建PWM针脚(定义了100级亮度，最小0，最大100)

        //一直循环
        while (true) {            
            //慢慢亮直到完全亮
            for (int i = 0; i <= 100; i++) {//循环100次
                SoftPwm.softPwmWrite(1, i);//控制PWM
                Thread.sleep(100);//延时0.1秒
            }

            //慢慢暗知道完全灭
            for (int i = 100; i >= 0; i--) {//循环100次
                SoftPwm.softPwmWrite(1, i);//控制PWM
                Thread.sleep(100);//延时0.1秒
            }
        }
    }
}
