package com.springbeans.test;

import com.springbeans.beans.MessageWriter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author : Apoorva Raj
 * @mailto : apoorv.vardhman@gmail.com
 * @LinkedIn : apoorv-vardhman
 **/
public class AppTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("springbeans/application-context.xml"));
        MessageWriter messageWriter = (MessageWriter) beanFactory.getBean("messageWriter");
        messageWriter.writeMessage("Welcome to Spring bean configuration.");
    }
}
