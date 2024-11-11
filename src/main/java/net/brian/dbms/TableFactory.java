package net.brian.dbms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import org.reflections.Reflections;


public class TableFactory {
    private static final String PACKAGE_NAME = "net.brian.dbms.dto";

    public static Optional<Class<? extends GenericTable>> findMatchingTable(String xmlContent) {
        // Use Reflections to scan for classes annotated with @TableIdentifier
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(TableIdentifier.class);

        for (Class<?> clazz : annotatedClasses) {
            TableIdentifier identifier = clazz.getAnnotation(TableIdentifier.class);
            for (String tag : identifier.tag()) {
                if (xmlContent.contains("<" + tag + ">")) {
                    return Optional.of(clazz.asSubclass(GenericTable.class));
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<Class<? extends GenericTable>> findMatchingTable(String xmlContent, Class<? extends GenericTable> specificClass) {

        // Get XML tags from fields of the specific class
        List<String> xmlTags = getXmlTagsFromFields(specificClass);

        // Loop through the tags and check if any of them are present in the XML content
        for (String tag : xmlTags) {
            if (xmlContent.contains("<" + tag + ">")) {
                // If a tag is found in the XML content, return the corresponding class
                return Optional.of(specificClass);
            }
        }

        // If no tags match, return Optional.empty()
        return Optional.empty();


           // Limit reflections to a specific class
           // Reflections reflections = new Reflections(specificClass.getPackage().getName()); // Scan the package of the class
        //    TableIdentifier tableIdentifier = specificClass.getAnnotation(TableIdentifier.class);

        //     Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(TableIdentifier.class);

        //     for (Class<?> clazz : annotatedClasses) {
        //         TableIdentifier identifier = clazz.getAnnotation(TableIdentifier.class);
        //         for (String tag : identifier.tag()) {
        //             if (xmlContent.contains("<" + tag + ">")) {
        //                 return Optional.of(clazz.asSubclass(GenericTable.class));
        //             }
        //         }
        //     }
        //     return Optional.empty();

    }

    // This method returns the XML tags from fields annotated with @XmlElement in the class passed
    public static List<String> getXmlTagsFromFields(Class<?> clazz) {
        List<String> tags = new ArrayList<>();
        try {
            // Iterate over all declared fields of the class
            for (Field field : clazz.getDeclaredFields()) {
                // Check if the field is annotated with @XmlElement
                XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                if (xmlElement != null) {
                    // Add the name from @XmlElement to the list of tags
                    tags.add(xmlElement.name());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }
}
    // Method to extract XML tags from both class-level and field-level annotations
//     public static List<String> getXmlTagsFromFields(Class<?> clazz) {
//         List<String> tags = new ArrayList<>();

//         try {
//             // First, get tags from @TableIdentifier annotation on the class
//             TableIdentifier tableIdentifier = clazz.getAnnotation(TableIdentifier.class);
//             if (tableIdentifier != null) {
//                 tags.addAll(Arrays.asList(tableIdentifier.tag()));
//             }

//             // Then, get tags from @XmlElement annotations on fields
//             for (Field field : clazz.getDeclaredFields()) {
//                 XmlElement xmlElement = field.getAnnotation(XmlElement.class);
//                 if (xmlElement != null) {
//                     tags.add(xmlElement.name());
//                 }
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return tags;  // Return all the tags found
//     }
// }

