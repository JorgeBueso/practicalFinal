//package proyectofinaljorgebueso;
//
//
//import java.io.File;
//import java.util.List;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Unmarshaller;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author jorge
// */
//public class Jaxb 
//{  
//    private Personajes misPersonajes;
//    private JAXBContext contexto;
//    
//    public int abrir_XML_JAXB(File fichero) 
//    { 
//        
//        try 
//        { //Crea una instancia JAXB 
//            contexto = JAXBContext.newInstance(Personajes.class);
//            //Crea un objeto Unmarsheller. 
//            Unmarshaller u = contexto.createUnmarshaller();
//            //Deserializa (unmarshal) el fichero
//            misPersonajes = (Personajes) u.unmarshal(fichero);
//            return 0; 
//        } catch (Exception ex)
//            { 
//                ex.printStackTrace();
//                return -1; 
//            } 
//    }
//    
//    
//    public String recorrerJAXByMostrar()
//    {
//        String datos_nodo[]=null; 
//        String cadena_resultado="";
//        //Crea una lista con objetos de tipo libro. 
//        List<Personajes.Personaje> LPersonajes = misPersonajes.getPersonaje();
//        //Recorre la lista para sacar los valores. 
//        for (int i=0; i<LPersonajes.size(); i++)
//        {
//            cadena_resultado = LPersonajes.get(i).getadaptaciones_pelicula()
//                +cadena_resultado+"\n" + "Adaptación en película:";
//            cadena_resultado = cadena_resultado + "\n" +"El Título es: "+ LPersonajes.get(i).getPersonaje();
//            cadena_resultado= cadena_resultado + "\n" +"El Autor es: " + LPersonajes.get(i).getCreador(); 
//            cadena_resultado = cadena_resultado +"\n ------------------------";
//        }   
//        return cadena_resultado;
//    }
//}