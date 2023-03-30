# Spring Bean 
    let us take the help of spring framework in instantiating and managing the dependencies of our components.

    We need to define the information about the components of our application to the Spring Framework, so that spring can take care of instantiating and managing them.	If for any given class, if spring framework is taking care of creating the object and managing the dependencies, then the object is called "spring bean". So the information about our classes should be provided as an input to the spring framework in spring understandable format/language.

## Spring Bean configuration file
we need to define the information about our components in "spring bean configuration file", the spring bean configuration file holds the information about the classes that are instantiated & managed the spring framework so it is called "spring bean configuration file".

The Spring Bean Configuration file is an XML file and will be written using a standard name called "application-context.xml". In the spring bean configuration file we define bean definitions.

## How to write spring bean configuration file?
spring bean configuration file is an xml file, every xml starts with prolog and has one and only one root element.
prolog = prolog stands for processing instruction

application-context.xml

    <?xml version="1.0" encoding="utf-8"?>
    <beans>
        <bean id="messageWriter" class="com.sc.beans.MessageWriter"/> #bean definition or declaration
        <bean id="htmlMessageConverter" class="com.sc.converters.HTMLMessageConverterImpl"/>
        <bean id="pdfMessageConverter" class="com.sc.converters.PDFMessageConverterImpl"/>
    </beans>

Core

    |-src
        |-main
            |-java
            |-resources (non-java sourcecode)
                |-application-context.xml
        |-target
            |-*.jar
            |-classes
    |-pom.xml

## BeanFactory
    BeanFactory is an factory class, that takes care of creating the object for the bean definition we asked. To the BeanFactory we need to pass spring bean configuration file as an input, so that it can read the configuration and can identify the bean definition we have requested and create the object for it

There are 2 ways we can pass a file as an input to a class within our application
  1. absolute path = if the file or resource is located outside the project then we need to use absolute path, absolute paths are often used while production.

  2. relative path = if the file or resource is located within the classpath of the project, then we can refer the file using relative path. relative paths are more convinient during development 

BeanFactory (interface)

    |-DefaultListableBeanFactory
    |-XMLBeanFactory

    BeanFactory beanFactory = new XMLBeanFactory(new ClassPathResource("com/sc/common/application-context.xml"));
    BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("d:\\application-context.xml"));

There are 2 helper classes are provided by spring developers
Resource (interface)
- ClassPathResource  = ClassLoader.getResourceAsStream("");
- FileSystemResource = new FileInputStream(new File(""));

### What will happen when we tried creating the BeanFactory?
BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("com/cdi/common/application-context.xml"));
1. Resource = is an interface which has relevant functionality for reading a resource from the disk (filesystem)
   There are 2 implementations for the Resource interface
   1. FileSystemResource = reads the file from the physical directory location using "absolute path"
   2. ClassPathResource  = reads the file from the pysical location by using relative path from the CLASSPATH of the application and it uses ClassLoaders in reading the files of the project

here we are creating the object of Resource implementation and passing it as an input to XMLBeanFactory

2. while the XMLBeanFactory is being created, it goes to Resource object we passed and request for the underlying spring bean configuration file.

   2.1 here we are passing ClassPathResource object as an input to the XMLBeanFactory, so ClassPathResource quickly goes to the classpath of our application and locates the spring bean configuration file relative from the classpath and returns the file as an input to XMLBeanFactory

    2.2 upon taking the XML from the ClassPathResource, the XMLBeanFactory performs 2 checks

      2.2.1 well-formness check = which means the XML has been written as per the syntactic rules of XML or not

      2.2.2 validity = is the XML has been written with the valid tags defined by the spring developers or not

if these checks are failed, the XMLBeanFactory throws an exception and terminates the program

3. if the given XML is well-formed and valid then XMLBeanFactory quickly goes to the jvm memory and creates an logical memory partition within the jvm memory called "ioc container" or "core container"
   then reads the spring bean configuration file and places it as an in-memory metadata within the ioc container and returns the reference as an BeanFactory to us

2. what will happens when we call beanFactory.getBean("beanId");
   when we ask for an bean definition object, the beanFactory goes to the in-memory metadata of the ioc container searching for the bean definition with the given id and once found, it reads the fqnClass and instantiates the object for it and places the object within the ioc container as key=id and value=object and returns the reference of the object to us

by default, when we repeatedly request the same bean definition, it will not create new object rather it returns the existing object it has already created from the ioc container

MessageWriter messageWriter = (MessageWriter) beanFactory.getBean("messageWriter");
