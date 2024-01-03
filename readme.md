参考：
https://blog.csdn.net/u012702547/article/details/131675232
https://blog.csdn.net/u012702547/article/details/131696802
https://www.jianshu.com/p/aebb925ffb3e

app1中，引入app2和app3。


如果我们在一个 Web 项目中，不单独配置 Spring 容器，直接配置 SpringMVC 容器，
然后将所有的 Bean 全部都扫描到 SpringMVC 容器中，这样做是没有问题的，项目是可以正常运行的。
但是一般项目中我们还是会把这两个容器分开，分开有如下几个好处：

1.方便管理，SpringMVC 主要处理控制层相关的 Bean，如 Controller、视图解析器、参数处理器等等，
而 Spring 层则主要控制业务层相关的 Bean，如 Service、Mapper、数据源、事务、权限等等相关的 Bean。

2.对于新手而言，两个容器分开配置，可以更好的理解 Controller、Service 以及 Dao 层的关系，
也可以避免写出来在 Service 层注入 Controller 这种荒唐代码。


注意：<br>
1.可以给非 ListableBeanFactory 容器设置父容器，父容器不可以访问子容器的 Bean，但是子容器可以访问父容器的 Bean。

2.在一个 SSM 项目中，你可以单纯使用 SpringMVC 容器，这个没问题，项目可以正常运行。但是，如果把所有的 Bean 都扫描到 Spring 容器中行不行？
可以！但是需要额外配置。

修改RequestMappingHandlerMapping的 detectHandlerMethodsInAncestorContexts属性为true


#### part2
Spring中,父子容器不是继承关系，他们是通过组合关系完成的，即子容器通过 setParent()持有父容器的引用。

父容器对子容器可见，子容器对父容器不可见。详细来说，就是 Spring 父子容器中，父容器不能访问子容器的 bean 。而子容器可以访问父容器的内容。
如果父子容器中都存在某个 bean 的情况，子容器会使用自身上下文定义的 bean，从而覆盖父容器定义的相同的 bean。（这点很重要）。
总结：父子容器的主要用途是上下文隔离。

在传统的 SpringMVC + Spring 的架构中，Spring 负责 service 和 dao 层的 bean 管理，并支持事务,aop切面等功能。

而springMVC 为子容器，直接托管 controller 层等与 web 相关的代码，在使用 service 层的 bean时，直接从 父容器中获取即可。

而现今，在使用 springboot 的场景下，我们一般只有一个上下文。父子容器的使用和概念貌似已经被开发人员遗忘了。
