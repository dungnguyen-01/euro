package net.brian.dbms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Class for parsing XML elements into Java objects
public class TableParser {

    @SuppressWarnings("unchecked")
    public static <T extends GenericTable> Optional<List<? extends GenericTable>> parseTable(Element anyElement, Class<T> tableClass) {
        List<T> resultList = new ArrayList<>();

        try {
            // Initialize JAXB context and unmarshaller for the specified tableClass
            JAXBContext context = JAXBContext.newInstance(tableClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Select "Table" elements from the XML
            NodeList tables = anyElement.getElementsByTagName("Table");
            for (int i = 0; i < tables.getLength(); i++) {
                Element tableElement = (Element) tables.item(i);

                // Unmarshal each <Table> element into an instance of T (tableClass)
                T table = (T) unmarshaller.unmarshal(tableElement);
                resultList.add(table);
            }

            // Return result as Optional<List<? extends GenericTable>>
            return Optional.of(resultList);
        } catch (JAXBException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
