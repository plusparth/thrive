package com.ThreeCheesePasta.worldGenerator;

import java.io.*;
import java.util.zip.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Date;
import org.apache.commons.codec.binary.Base64; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class MapXMLFile {
   String location = "";
   int[][] data;
   
   
   public MapXMLFile(int[][] data, int width, int height) {
      this.data = data;
      this.location = writeTXM(width, height);
   }
   
   public String writeTXM(int w, int h) {
      try {
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
         String width = Integer.toString(w);
         String height = Integer.toString(h);
         
         // root elements
         Document doc = docBuilder.newDocument();
         Element mapElement = doc.createElement("map");
         doc.appendChild(mapElement);
         
         mapElement.setAttribute("version","1.0");
         mapElement.setAttribute("orientation", "orthogonal");
         mapElement.setAttribute("width", width);
         mapElement.setAttribute("height", height);
         mapElement.setAttribute("tilewidth", "48");
         mapElement.setAttribute("tileheight", "48");
         
            Element tileset = doc.createElement("tileset");
            tileset.setAttribute("firstgid", "1");
            tileset.setAttribute("name", "tileset");
            tileset.setAttribute("tilewidth", "48");
            tileset.setAttribute("tileheight", "48");
            
               Element image = doc.createElement("image");
               image.setAttribute("source", "/images/tileset.png");
               image.setAttribute("width", "384");
               image.setAttribute("height", "192");
               
            tileset.appendChild(image);
         
         mapElement.appendChild(tileset);
         
         Element layer = doc.createElement("layer");
         layer.setAttribute("name", "layer");
         layer.setAttribute("width", width);
         layer.setAttribute("height", height);
         
            Element data = doc.createElement("data");
            data.setAttribute("encoding", "base64");
            data.setAttribute("compression", "gzip");
               
               String bytestring = new String();
               for (int x = 0; x < w; x++) {                  
                  for (int y = 0; y < h; y++) {
                     switch(this.data[x][y]){
                        case 1: bytestring += "1000";
                           break;
                        case 2: bytestring += "2000";
                           break;
                        case 3: bytestring += "3000";
                           break;
                        case 4: bytestring += "4000";
                           break;
                        case 5: bytestring += "5000";
                           break;
                        case 6: bytestring += "6000";
                           break;
                        case 7: bytestring += "7000";
                           break;
                     }
                  }
               }
               
               Text value = doc.createTextNode(compress(bytestring)); 
               
               data.appendChild(value);
            
            layer.appendChild(data);
            
         mapElement.appendChild(layer);
         
    
         // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         Date d = new Date();
         String location = "data/map" + d.hashCode() + ".tmx";
         StreamResult result = new StreamResult(new File(location));
    
         // Output to console for testing
         // StreamResult result = new StreamResult(System.out);
    
         transformer.transform(source, result);
    
         System.out.println("Map saved!");
         System.out.println(location);
    
        } catch (ParserConfigurationException pce) {
         pce.printStackTrace();
        } catch (TransformerException tfe) {
         tfe.printStackTrace();
        }
      
      return location;
   }
   private static String compress(String str){
      
      byte byteAry[] = null;
      
      try{
         byteAry = str.getBytes("UTF-8");   
      }catch( UnsupportedEncodingException e){
         System.out.println("Unsupported character set");
      }
      
      for(int i = 0; i < byteAry.length; i++) {
         System.out.println(byteAry[i]);
         if(byteAry[i] == 48)
            byteAry[i] = 0;
         if(byteAry[i] == 49)
            byteAry[i] = 1;
         if(byteAry[i] == 50)
            byteAry[i] = 2;
         if(byteAry[i] == 51)
            byteAry[i] = 3;
         if(byteAry[i] == 52)
            byteAry[i] = 4;
         if(byteAry[i] == 53)
            byteAry[i] = 5;
         if(byteAry[i] == 54)
            byteAry[i] = 6;
         if(byteAry[i] == 55)
            byteAry[i] = 7;
         if(byteAry[i] == 56)
            byteAry[i] = 8;
         if(byteAry[i] == 57)
            byteAry[i] = 9;
         
      }
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      
      try {
         OutputStream deflater = new GZIPOutputStream(buffer);
         deflater.write(byteAry);
         deflater.close();
      }catch (IOException e) {
         throw new IllegalStateException(e);
      }
      String results = Base64.encodeBase64String(buffer.toByteArray());
      return results;
   }   
}