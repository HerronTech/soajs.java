# soajs.java
Soajs JAVA Middle Ware (Filter)

Add this library to your restful service as an external jar (you can find it under dist)

Or through POM.xml

```
<dependency>
    <groupId>soajs</groupId>
    <artifactId>soajs.java</artifactId>
    <version>1.0.0</version>
</dependency>
```

And make sure to initialize it in your servlet tag in (web.xml), as follows:

```
<init-param>
     <param-name>com.sun.jersey.spi.container.ContainerRequestFilters</param-name>
     <param-value>soajs.filters.SoajsContainerRequestFilter</param-value>
</init-param>
```

Consequently, anywhere in your web application you can wire your context and get your soajs object, as follows:

```
(@Context HttpHeaders headers)
  headers.getRequestHeader("soajs")
```
  
Full Example:
https://github.com/soajs/soajs.java.jaxrs_jersey
