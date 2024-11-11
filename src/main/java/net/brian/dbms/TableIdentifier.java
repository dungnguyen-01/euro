package net.brian.dbms;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Custom annotation to identify XML tags
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime
@Target(ElementType.TYPE)           // The annotation can be applied to classes
public @interface TableIdentifier {
    String[] tag(); // The tags that identify the XML structure
}
