package net.brian.dbms;

import org.tempuri.ServiceDatos;
import org.tempuri.ServiceDatosSoap;
import org.tempuri.TRANSLoginResponse2;

import java.util.List;
import java.util.Optional;

import org.tempuri.GetCiudadesResponse.GetCiudadesResult;
import org.tempuri.GetPaisesAllResponse.GetPaisesAllResult;
import org.w3c.dom.Element;

import net.brian.dbms.dto.CityTable;
import net.brian.dbms.dto.CoutryTable;

public class Test {

    public static final String USER = "1111";
    public static final String PASS = "111";

    public static void main(String[] args) {
       // getCiudades();

       getPaisesAll();
    }



    public static void getPaisesAll() {
        try {
            GetPaisesAllResult result = serviceDatosSoap().getPaisesAll(USER, PASS);

            Element anyElement = (Element) result.getAny();

            if(anyElement !=null) {
                // Convert the XML element to a string for debugging or further processing
                String xmlContent = XMLParser.convertElementToString(anyElement);

                Optional<Class<? extends GenericTable>> tableClass = TableFactory.findMatchingTable(xmlContent, CoutryTable.class);
                tableClass.ifPresent(clazz -> {
                   
                    Optional<List<? extends GenericTable>> tableList = TableParser.parseTable(anyElement, clazz);


                    tableList.ifPresent(tables -> {
                        tables.forEach(table -> {
                            if(table instanceof  CoutryTable) {
                                CoutryTable coutryTable = (CoutryTable) table;
                                System.out.println(coutryTable.idPais());       // Output: AFG
                                System.out.println(coutryTable.pais());         // Output: AFGANISTAN
                                System.out.println(coutryTable.codigoIata()); 
                            }
                            System.out.println("-----------------------------------------------");
                        });
                    });


                    if (tableList.isEmpty()) {
                        System.out.println("No tables found in the XML data.");
                    }
                    
                });



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void getCiudades() {
        try {
        GetCiudadesResult result = serviceDatosSoap().getCiudades(USER, PASS);

        Element anyElement = (Element) result.getAny();
       
        if (anyElement != null) {
            // Convert the XML element to a string for debugging or further processing
            String xmlContent = XMLParser.convertElementToString(anyElement);

            // Find the matching table class based on the XML content
            Optional<Class<? extends GenericTable>> tableClass = TableFactory.findMatchingTable(xmlContent);
            tableClass.ifPresent(clazz -> {
                // Parse the tables from the XML
                Optional<List<? extends GenericTable>> tableList = TableParser.parseTable(anyElement, clazz);

                tableList.ifPresent(tables -> {
                
                    // Iterate through each table and display the data
                    tables.forEach(table -> {
                        if (table instanceof CityTable) {
                            CityTable cityTable = (CityTable) table;
                            System.out.println(cityTable.cities());
                        }
                    });
                });

                if (tableList.isEmpty()) {
                    System.out.println("No tables found in the XML data.");
                }
            });

            if (tableClass.isEmpty()) {
                System.out.println("No matching class for XML data.");
            }
        } else {
            System.out.println("No data returned.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }

    
    

    public static void login() {

        TRANSLoginResponse2 response = serviceDatosSoap().transLogin(USER, PASS);

        System.out.println("isSuccess: "+ response.isSuccess());
        System.out.println("getIDSession: "+ response.getIDSession());
        System.out.println("getErrores: "+ response.getErrores().getString());
    }

    public static ServiceDatosSoap serviceDatosSoap() {
        ServiceDatos service = new ServiceDatos();
      return service.getServiceDatosSoap();
    }
    
}
