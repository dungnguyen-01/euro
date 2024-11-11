package net.brian.dbms.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.brian.dbms.GenericTable;
import net.brian.dbms.TableIdentifier;

   
@XmlRootElement(name = "Table")                // Annotation JAXB để ánh xạ phần tử gốc
@XmlAccessorType(XmlAccessType.FIELD)          // Annotation JAXB để ánh xạ các field trực tiếp
@Getter                                        // Lombok annotation để tạo getter
@Setter                                        // Lombok annotation để tạo setter
@Accessors(fluent = true)                      // Lombok annotation để truy cập kiểu fluent
public class CoutryTable extends GenericTable{

    @XmlElement(name = "ID_PAIS")
    private String idPais;

    @XmlElement(name = "PAIS")
    private String pais;

    @XmlElement(name = "PREFIJO")
    private String prefijo;

    @XmlElement(name = "PAIS_PORTUGUES")
    private String paisPortugues;

    @XmlElement(name = "CODIGO_IATA")
    private String codigoIata;

    @XmlElement(name = "PAIS_INGLES")
    private String paisIngles;

    @XmlElement(name = "IDIOMA")
    private String idioma;

    @XmlElement(name = "PAIS_RUSO")
    private String paisRuso;

    @XmlElement(name = "ID_HOTELSTON")
    private Integer idHotelston;

    @XmlElement(name = "MONEDA")
    private String moneda;

    @XmlElement(name = "PAIS_JAPONES")
    private String paisJapones;

    @XmlElement(name = "PAIS_ARABE")
    private String paisArabe;

    @XmlElement(name = "PAIS_BAHASA")
    private String paisBahasa;

    @XmlElement(name = "PAIS_CHINO")
    private String paisChino;

}
