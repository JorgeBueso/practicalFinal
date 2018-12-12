package proyectofinaljorgebueso;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorge
 */
public class Sax
{

    File ficheroXML;
    ManejadorSAX sh;
    SAXParser parser;
    
    
    public int abrir_XML_SAX(File fichero)
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //se crea un objeto SAXParser para interpretar el documento XML
            parser = factory.newSAXParser();

            //se crea una instancia del manejador que será el que recorra 
            //el documento XML secuencialmente
            sh = new ManejadorSAX();

            ficheroXML = fichero;

            return 0;
        }catch(Exception e )
            {
                e.printStackTrace();
                return 1;
            }

    
    }

    

  public class ManejadorSAX extends DefaultHandler
    {
        int ultimoelement ;
        String cadena_resultado = "";

        public ManejadorSAX()
        {
            ultimoelement = 0;
        }
        
        public void startElement(String uri,String localName,String qName,Attributes atts) throws SAXException
        {
           if(qName.equals("personaje"))
            {
                cadena_resultado = cadena_resultado + "\n Nombre: "
                                +atts.getValue(atts.getQName(0));
                ultimoelement = 1;
            }  
           
           else if(qName.equals("personaje"))
            {
                cadena_resultado = cadena_resultado + "\n Libro_origen: "
                                +atts.getValue(atts.getQName(1));
                ultimoelement = 2;
                
            }
            else if(qName.equals("personaje"))
            {
                cadena_resultado = cadena_resultado + "\n fecha_primera_aparicion: "
                                +atts.getValue(atts.getQName(2));
                ultimoelement = 3;
                
            }
            
            else if(qName.equals("creador"))
            {
                ultimoelement=4;
                cadena_resultado = cadena_resultado.trim() + "\n"+" Su creador es :";
            }
            
            else if(qName.equals("adaptaciones_pelicula"))
            {
                ultimoelement=5;
                cadena_resultado = cadena_resultado.trim() + "\n"+ " Una adaptacion de pelicula es:";
            }
           else if(qName.equals("adaptaciones_serie"))
            {
                ultimoelement=6;
                cadena_resultado = cadena_resultado.trim() + "\n" +" Una adaptacion de serie es:";
            }
           else if(qName.equals("estetica"))
            {
                ultimoelement=7;
                cadena_resultado = cadena_resultado.trim() + "\n"+ " La estética principal del personaje es:";
            }
           else if(qName.equals("epoca_en_la_que_transcurre"))
            {
                ultimoelement=8;
                cadena_resultado = cadena_resultado.trim() + "\n"+ " La época_en_la_que_transcurre es:";
            }
           else if(qName.equals("ultima_aparicion"))
            {
                ultimoelement=9;
                cadena_resultado = cadena_resultado.trim() + "\n"+ " La última_aparición es:";
            }
           else if(qName.equals("actor_ultima_aparicion"))
            {
                ultimoelement=10;
                cadena_resultado = cadena_resultado.trim() + "\n"+ " El actor que lo encarna en su última aparición es: ";
            }
          
            
          
            
            
        }
        //  -------------------------------------------------
        //cuando se detecta el final de un elemento <libro>,
        //se pone una línea discontínua en la salida
        public void endElement(String uri, String localName,String qName)throws SAXException
        {
            if(qName.equals("Personaje"))
            {
                System.out.println("He encontrado el final de un elemento.");
                cadena_resultado = cadena_resultado + "\n ------------";
            }
        }
        //------------------------------------------------
        //cuando se detecta una cadena de texto posterior a uno de los elementos <titulo>
        //o <autor> entonces guarda ese texto en la variable correspondiente.
        public void characters(char[] ch,int start, int length) throws SAXException
        {
            
            if(ultimoelement == 4)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 5)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 6)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 7)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 8)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 9)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }else if(ultimoelement == 10)
            {
                for(int i = start; i<length + start; i++)
                {
                    cadena_resultado=cadena_resultado+ch[i];
                }
            }
            
        }
  }  
        //para lanzar el parser SAX, para obtener el resultado anterior
        public String recorrerSAX() 
        {
            try
            {
                parser.parse(ficheroXML,sh);
                return sh.cadena_resultado;
                
            }catch(SAXException e)
            {
                e.printStackTrace();
                return"Error al parsear con SAX";
            }catch(Exception e)
            {
                e.printStackTrace();
                return"Error al parsear con SAX";
            }
        }
    
}
