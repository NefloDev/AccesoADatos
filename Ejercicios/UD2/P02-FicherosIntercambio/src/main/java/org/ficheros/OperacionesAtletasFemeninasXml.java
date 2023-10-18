/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ficheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.ficheros.entities.AtletaFemenina;

/**
 *
 * @author alnef
 */
public class OperacionesAtletasFemeninasXml {
    
    public static void writeObjectListXml(List<AtletaFemenina> a, Path ruta){
        try{
            Files.deleteIfExists(ruta);
            XmlMapper mapper =  new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(ruta.toFile(), a);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static String readObjectListAsString(List<AtletaFemenina> a){
        try{
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(a);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static List<AtletaFemenina> readObjectListXml(Path ruta){
        try{
            XmlMapper mapper =  new XmlMapper();
            return mapper.readValue(ruta.toFile(), new TypeReference<>(){});
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        Path atletasXml = Path.of(".","src","main","resources", "atletas_femeninas.xml");
        
        AtletaFemenina atleta1 = new AtletaFemenina("Mireya", 19, "Inglaterra", List.of("Carrera de vallas", "Salto de longitud", "Salto de altura"));
        AtletaFemenina atleta2 = new AtletaFemenina("Irene", 22, "España", List.of("500m lisos", "100m lisos", "Carrera de vallas"));
        
        List<AtletaFemenina> atletas = List.of(atleta1, atleta2);
        
        System.out.println("Escribiendo xml de atletas...");
        writeObjectListXml(atletas, atletasXml);
        
        System.out.println("############# Atletas #############");
        readObjectListXml(atletasXml).forEach(System.out::println);
        
        //He añadido este porque puedo, no se
        System.out.println("############# ATLETAS_FEMENINAS.XML #############");
        System.out.println(readObjectListAsString(readObjectListXml(atletasXml)));
    }
    
}
