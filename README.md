# SimpleAnnotation [![Maven Version](https://mvnhelper.potatocorp.dev/kingtux/me.kingtux/simpleannotation/badge.png)](https://mvnhelper.potatocorp.dev/kingtux/me.kingtux/simpleannotation)
Have you ever needed to do something with Reflection and an Annotation? This is the library to make your life easier!

Javadocs https://docs.kingtux.me/simpleannotation/
## Maven
```xml
   <repository>
      <id>kingtux-repo</id>
      <url>https://repo.kingtux.me/storages/maven/kingtux-repo<</url>
    </repository>
    
    <dependency>
      <groupId>me.kingtux</groupId>
      <artifactId>simpleannotation</artifactId>
      <!---Make sure you use Latest Version!-->
      <version>1.3.1</version>
      <scope>compile</scope>
    </dependency>
```
## Gradle
```groovy
repositories {
  maven { url 'https://repo.kingtux.me/storages/maven/kingtux-repo' }
}

dependencies {
   compile "me.kingtux:simpleannotation:1.3.1"
}
```
## How to use
```java

//This library is extremely simple. So reading the docs is a good way to learn However, here are some examples
Method method = MethodFinder.getFirstMethodWithAnnotation(classToCheck.class, YourAnnotation.class);
// The line above will grab the the first method found to have the Annotation Specified
Method[] methods = MethodFinder.getAllMethodsWithAnnotation(classToCheck.class, YourAnnotation.class);
//The line above will find all methods with that Annotation
//You can do the same with the FieldFinder
```
### Using the class finder
```java
Class[] ClassFinder.getClassesInsideFileWithAnnotation(fileToLookAt, YourAnnotation.class, YourClassToExtend.class, "me.kingtux");

//This will get all classes that the package starts with me.kingtux and extends YourClassToExtend; 
```