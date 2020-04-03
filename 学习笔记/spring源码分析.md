# spring源码分析

通过对spring的使用与学习，可以得知spring容器根容器就是beanfactory这个容器

beanfactory是spring容器的一个统一的接口观看源码可以看出来在beanfactory接口中定义了一系列的get方法和一系列的spring容器的规范和约定所有的spring容器都要按照这些约定和规范才行。

beanfactory这是一个接口，查看这个接口的继承关系可以看到一共有24个子类和实现类，其中xmlbeanfactory已经遗弃了，这里面可以看到我们最开始学习的那个classpathxmlapplicationcontext 这个类

```
        Resource resource = new ClassPathResource("spring-beans.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //BeanDefinitionRegistry
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(resource);
        person person = beanFactory.getBean("person", person.class);
        System.out.println(person);
        Resource resource = new ClassPathResource("spring-beans.xml");
```

这是我们初始化spring容器的一个大体过程就是先抽象化配置文件为一个Resource对象，在创建一个beanfactory对象，通过创建Reader对象并将工程作为参数传进去，最后使用reader的加载方法加载resource对象就创建完成了我们的工厂。这是spring3.*的创建过程。

而我们最开始学习的通过classpathxmlapplicationcontext创建容器的过程如下

```java
ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");//classPathXmlApplicationContext.refresh();person person = classPathXmlApplicationContext.getBean("person", person.class);System.out.println(person);
```

这是我们最开始学习时候使用的方式点进去看ClassPathXmlApplicationContext的初始化过程

```
public ClassPathXmlApplicationContext(String configLocation) throws BeansException {    this(new String[]{configLocation}, true, (ApplicationContext)null);}
```

它调用了自己的构造方法并将refresh定义为true我们找到这个构造方法看看

```java
   public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, @Nullable ApplicationContext parent) throws BeansException {
        super(parent);
        this.setConfigLocations(configLocations);
        if (refresh) {
            this.refresh();
        }

    }
```

和我们目前常用的annotation application context的初始化过程几乎一模一样我们看看他们的继承关系

```
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
```

他们的同一父类找到了继承了defaultresourceloader并实现了ConfigurableApplicationContext接口

这样就可以清楚为什么在着个ConfigurableApplicationContext系列的实现类中并没有显性的看到resouce对象应为他将这个过程封装了起来，并且public interface ApplicationContext 继承了 ListableBeanFactory,

所有可以看出来spring容器的初始化过程和上面的那个过程基本是一致的只不过做了封装更方便使用了