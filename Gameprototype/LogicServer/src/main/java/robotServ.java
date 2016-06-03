import io.netty.bootstrap.Bootstrap;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoubo on 15-9-1.
 */
public class robotServ {

    private static ConfigurableApplicationContext ctx;
    private static Logger logger=Logger.getLogger(robotServ.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        if(args.length==0){
            System.out.print("error,not port");
            return;
        }
        System.setProperty("ip", args[0]);
        System.setProperty("port", args[1]);
        System.setProperty("robots", args[2]);
        System.setProperty("startId", args[3]);
//		PropertyConfigurator.configure(logicServ.class.getResource("log4j.properties"));
        ctx=new ClassPathXmlApplicationContext("robot.xml");
        Bootstrap cb=(Bootstrap)ctx.getBean("robotHandler");

        for( int i=0;i<Integer.parseInt(args[2]);i++){
            cb.connect(new InetSocketAddress(args[0], Integer.parseInt(args[1])));
            logger.debug("run");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ctx.registerShutdownHook();
        logger.info("roboot startup ip:"+args[0]+"---port:"+args[1]);

    }
}
