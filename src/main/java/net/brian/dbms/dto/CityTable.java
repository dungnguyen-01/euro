package net.brian.dbms.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.brian.dbms.GenericTable;
import net.brian.dbms.TableIdentifier;

// Custom annotation to identify the XML structure
@TableIdentifier(tag = { "Ciudades" })
@XmlRootElement(name = "Table")        // JAXB annotation to map the root element
@XmlAccessorType(XmlAccessType.FIELD)  // JAXB annotation to map fields directly
@Getter                                // Lombok annotation to generate getters
@Setter                                // Lombok annotation to generate setters
@Accessors(fluent = true)              // Lombok annotation for fluent accessors
public class CityTable extends GenericTable {

    @XmlElement(name = "Ciudades")
    private String cities;

}