package spring.basic.BusinessFactory;   
  
import org.springframework.context.ApplicationContext;   
import org.springframework.context.support.ClassPathXmlApplicationContext;   
  
public class SpringDemo {   
    public static void main(String[] args) throws org.springframework.beans.BeansException{   
        ApplicationContext context = new ClassPathXmlApplicationContext(   
            "./businessFactoryConfig.xml");   
  
        IComputer computer1 =  (IComputer)context.getBean("Laptop");           
        computer1.save();   
        
        IComputer computer2 =  (IComputer)context.getBean("Desktop");           
        computer2.save();
    }   
}  


