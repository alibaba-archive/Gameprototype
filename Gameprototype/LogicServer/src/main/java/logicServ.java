import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoubo on 15-9-1.
 */
public class logicServ {

    private static ConfigurableApplicationContext ctx;
    private static Logger logger=Logger.getLogger(logicServ.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        int core=Runtime.getRuntime().availableProcessors()+1;
        System.setProperty("coreCount", core+"");
        ctx=new ClassPathXmlApplicationContext("server.xml");
        ctx.registerShutdownHook();
    }
}
