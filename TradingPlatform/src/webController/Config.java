package webController;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fdmgroup.Initializer;

/**
 * Application Lifecycle Listener implementation class thingfuck
 *
 */
@WebListener
public class Config implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Config() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
        new Initializer();
    }
	
}
