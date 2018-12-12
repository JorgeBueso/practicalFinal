package proyectofinaljorgebueso;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorge
 */
public class ejecutarXpath 
{
//   String datos_nodo[] = null;
   // Node node ;
    Document doc;
    int conteo = 1;
    String salida = "";
    
  // XPath xpath = XPathFactory.newInstance().newXPath();
   

    public int abrir_XML_DOM(File fichero )
            {
           
                 
                try
                 {
                   //CARGA EL DOCUMENTO
                    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
                 //   factory.setIgnoringComments(true);
                    factory.setIgnoringElementContentWhitespace(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    doc = builder.parse(fichero);
                    return 0;
                 }catch(Exception e)
                    {
                        e.printStackTrace();
                        return -1;
                    }
                
            }
     
  
   
   
    //poner como string todos los elementos(tanto atributos como campos)
    public int anyadirDOM(String nombre, String libro, String primeraAparicion,String creador,
            String pelicula, String serie,String estetica, String epoca, String ultimaAparicion, String actor)
    {
        try
        {
            System.out.println("llega aquí 1");
           
 //SE CREAN LOS ELEMENTOS
                    
 
            //Se crea un nodo tipo Element con nombre 'creador'(<creador>)
                Node ncreador = doc.createElement("creador");     
            
            //Se crea un nodo tipo texto con el nombre del creador
                Node ncreador_text = doc.createTextNode(creador);                      
            
            //Se añade el nodo de texto al creador como su hijo. 
                ncreador.appendChild(ncreador_text);     
            
                System.out.println("llega aquí 3");
         //------------------------------ADAPTACIONES PELÍCULAS-----------------------------------------------------// 
            
            //Se hace lo mismo que con titulo a autor(<adaptaciones_pelicula>)
                Node nadaptaciones_pelicula = doc.createElement("adaptaciones_pelicula");           
                
            //Se crea un nodo tipo texto con el título del creador
                Node nadaptaciones_pelicula_text = doc.createTextNode(pelicula);
            
            //Se añade el nodo de texto al creador como su hijo. 
                nadaptaciones_pelicula.appendChild(nadaptaciones_pelicula_text);
            System.out.println("llega aquí 4");
          //-----------------------------------------------------------------------------------// 
          
            //Se crea un nodo tipo Element con nombre 'adaptaciones_serie'(<adaptaciones_serie>)
            Node nadaptaciones_serie = doc.createElement("adaptaciones_serie");     
            
            //Se crea un nodo tipo texto con el título del creador
            Node nadaptaciones_serie_text = doc.createTextNode(serie);                      
            
            //Se añade el nodo de texto al creador como su hijo. 
            nadaptaciones_serie.appendChild(nadaptaciones_serie_text);     
            
     //-----------------------------------------------------------------------------------//      
            
            //Se crea un nodo tipo Element con nombre 'adaptaciones_serie'(<adaptaciones_serie>)
            Node nestetica = doc.createElement("estetica");    
            
            
            Node nestetica_text = doc.createTextNode(estetica);
            
            //Se añade el nodo de texto al creador como su hijo. 
            nestetica.appendChild(nestetica_text);
  //-----------------------------------------------------------------------------------//           
            //Se crea un nodo tipo Element con nombre 'creador'(<epoca_en_la_que_transcurre>)
            Node nepoca_en_la_que_transcurre = doc.createElement("epoca_en_la_que_transcurre");     
            
            //Se crea un nodo tipo texto con el título del creador
            Node nepoca_en_la_que_transcurre_text = doc.createTextNode(epoca);                      
            
            //Se añade el nodo de texto con el título como hijo del elemento Titulo
            nepoca_en_la_que_transcurre.appendChild(nepoca_en_la_que_transcurre_text);     
  //-----------------------------------------------------------------------------------//           
          
            
            //ultima_aparicion(<ultima_aparicion>)
            Node nultima_aparicion = doc.createElement("ultima_aparicion");                           
            Node nultima_aparicion_text = doc.createTextNode(ultimaAparicion);
            
            nultima_aparicion.appendChild(nultima_aparicion_text);
 
  //-----------------------------------------------------------------------------------//      
            
        //(<actor_ultima_aparicion>)
            Node nactor_ultima_aparicion = doc.createElement("actor_ultima_aparicion");     
            
        //Se crea un nodo tipo texto con el título del creador
            Node nactor_ultima_aparicion_text = doc.createTextNode(actor);                      
            
        //Se añade el nodo de texto con el título como hijo del elemento Titulo
            nactor_ultima_aparicion.appendChild(nactor_ultima_aparicion_text);     
            
 
               
            //Se crea un nodo de tipo elemento(<Personaje>) 
            Node nPersonaje = doc.createElement("Personaje");          
            System.out.println("llega aquí 2");
            //Al nuevo nodo personaje se le añaden los atributos
            ((Element)nPersonaje).setAttribute("nombre:", nombre);  
            ((Element)nPersonaje).setAttribute("libro_origen", libro );  
            ((Element)nPersonaje).setAttribute("fecha_primera_aparicion", primeraAparicion);  
            
            
            //Se añade a personaje el nodo creador y todos los demás nodos
            nPersonaje.appendChild(ncreador);                                        
            nPersonaje.appendChild(nadaptaciones_pelicula);
            nPersonaje.appendChild(nadaptaciones_serie);                                        
            nPersonaje.appendChild(nestetica);
            nPersonaje.appendChild(nepoca_en_la_que_transcurre);                                        
            nPersonaje.appendChild(nultima_aparicion);
            nPersonaje.appendChild(nactor_ultima_aparicion);                                        
            
            
            //Se obtiene el primer nodo del documento y a él se le añade como hijo el nodo personaje que ya tiene todos sus hijos
            //y atributos, creados antes
            Node raiz = doc.getChildNodes().item(0);                            
            raiz.appendChild(nPersonaje);
            
            return 0;    
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
     public int guardarDOMcomoFILE(String salida)
    {
    try{
        //Crea un fichero llamado salida.xml
        File archivo_xml = new File(salida);      
        
        //Especifica el formato de salida
        OutputFormat format =new OutputFormat(doc);  
        
        //Especifica que la salida esté indentada
        format.setIndenting(true);                   
        
        //Escribe el contenido en el File
        XMLSerializer serializer = new XMLSerializer(new FileOutputStream            
        (archivo_xml),format);
        
        serializer.serialize(doc);
        
        return 0;  
    }
    catch(Exception e){
        return-1;
    }
    
    }
     
      public String ejecutaXPath(String text)
    {
        String salida = "";
        
     try
     {
               
       //Crea el objetoXPath
       XPath xpath = XPathFactory.newInstance().newXPath();
       
       //Crea un XpathExpression con la consulta deseada
         XPathExpression exp = xpath.compile(text);
       
       //Ejecuta la consulta indicando que se ejecute sobre el DOM y
       //que devolverá el resultadocomo una lista de nodos
         Object result = exp.evaluate(doc, XPathConstants.NODESET);
         NodeList nodeList = (NodeList) result;
         
       //Ahora recorre la lista para sacar los resultados
        for(int i=0; i < nodeList.getLength();i++)
         {
                                          
                salida = salida +"\n" + nodeList.item(i).getAttributes().getNamedItem("nombre:");
                salida = salida +"\n" + nodeList.item(i).getAttributes().getNamedItem("libro_origen:");
                salida = salida +"\n" + nodeList.item(i).getAttributes().getNamedItem("fecha_primera_aparicion:");
               
                
            
                //me imprime los elementos,los nodos hijos 
                salida = salida + "\n" + nodeList.item(i).getTextContent(); 
                
                salida = salida + "\n-------------------------------------------------------------------"; 
                   
         }
        
      return salida;
         
     }catch(Exception ex)
        {
            System.out.println("Error:" + ex.toString());          
        }
         return text;
         
    }
    
    
  
    
}
