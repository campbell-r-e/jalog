/**
 * Modified by: Campbell Reed (2025-02-21)
 * - Updated Callook API URL to "https://callook.info/[callsign]/xml".
 * - Fixed broken callsign lookups due to outdated URL format.
 */


package openlogbook.jlogimpl.remotecallsign.callookreader;

import openlogbook.factory.CallsignInformationFactory;
import openlogbook.jlog.remotecallsign.RemoteCallsignInformation;
import openlogbook.jlog.remotecallsign.ConnectionReader;
import openlogbook.jlogimpl.debug.DebugManager;
import openlogbook.jlogimpl.debug.InternalEventId;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * An implementation of the ConnectionReader for reading data from the "calllook.info" web site.
 * 
 * @author KC0ZPS
 */
public class CallookReaderImpl implements ConnectionReader {
   private RemoteCallsignInformation _callsignInformation ;
   
   private static final String URL = "https://callook.info/" ;

  

   /**
    * Creates a new CallLookReaderImpl
    */
   public CallookReaderImpl() {
   }

   //**********
   // Methods that are inherited from ConnectionReader
   //**********

   public RemoteCallsignInformation get(String callsign)
   throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
      _callsignInformation = CallsignInformationFactory.createCallsignInformation() ;
      Document doc = readXmlPage(callsign);
      Node root = doc.getDocumentElement();
      convertDocumentToObject(root) ;
      return _callsignInformation ;
   }

   public RemoteCallsignInformation getCachedCallsignInformation() {
      return _callsignInformation ;
   }
   
   //**********
   // Private methods
   //**********
   
   /**
    * Start reading data from the xml page.
    * 
    * @param callsign the callsign to query.
    * 
    * @return A document object containing the callsign information.
    * 
    * @throws ParserConfigurationException Indicates a serious configuration error.
    * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred.
    * @throws IOException Signals that an I/O exception of some sort has occurred.
    * @throws SAXException a general SAX error or warning.
    */
   private Document readXmlPage(String callsign) 
   throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
      DocumentBuilder docBuilder;
      Document doc = null;
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      docBuilderFactory.setIgnoringElementContentWhitespace(true);
      docBuilder = docBuilderFactory.newDocumentBuilder();
      
      URL url = new URL(URL + callsign +"/xml");
      URLConnection yc = url.openConnection();
      
      doc = docBuilder.parse(yc.getInputStream()) ;      
      return doc;
   }
   
   /**
    * Convert the node information to a RemoteCallsignInformation object.
    * 
    * @param node The node to get the data from.
    */
   private void convertDocumentToObject(Node node) {
      String nodeName = node.getNodeName();
      
      createEntry(node) ;
      // System.out.println("NodeName: " + nodeName + ", NodeValue: " + nodeValue);
      
      if (nodeName.equals(CallookXmlConstants.Root)) {
         System.out.println("Start CallLook") ;
      }

      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
          Node child = children.item(i);
          if (child.getNodeType() == Node.ELEMENT_NODE) {
             convertDocumentToObject(child);
          }
      }      
   }
   
   /** 
    * Returns element value
    * 
    * @param elem element (it is XML tag)
    * 
    * @return Element value otherwise empty String
    */
   private final static String getElementValue( Node elem ) {
       Node kid;
       if( elem != null){
           if (elem.hasChildNodes()){
               for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
                   if( kid.getNodeType() == Node.TEXT_NODE  ){
                       return kid.getNodeValue();
                   }
               }
           }
       }
       return "";
   }

   /**
    * Assign the correct data from a single entry.
    * 
    * @param node The node to get the data from.
    */
   private void createEntry(Node node) {
      String nodeName = node.getNodeName();
      String nodeValue = getElementValue(node);

      if (nodeName.equals(CallookXmlConstants.Status.toString())) {
         _callsignInformation.setStatus(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.Callsign.toString())) {
         if (checkCallsignStatus(node, CallookXmlConstants.CurrentCallsignValue)) {
            _callsignInformation.setCurrentCallsign(nodeValue) ;            
         }
         if (checkCallsignStatus(node, CallookXmlConstants.PreviousCallsignValue)) {
            _callsignInformation.setPreviousCallsign(nodeValue) ;            
         }         
      } else if (nodeName.equals(CallookXmlConstants.Name.toString())) {
         _callsignInformation.setName(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.AddressLine1.toString())) {
         _callsignInformation.setAddressLine1(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.AddressLine2.toString())) {
         _callsignInformation.setAddressLine2(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.Latitude.toString())) {
         _callsignInformation.setLatitude(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.Longitude.toString())) {
         _callsignInformation.setLongitude(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.GrantDate.toString())) {
         _callsignInformation.setGrantDate(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.ExpiryDate.toString())) {
         _callsignInformation.setExpiryDate(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.Frn.toString())) {
         _callsignInformation.setFrn(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.GridSquare.toString())) {
         _callsignInformation.setGridSquare(nodeValue) ;
      } else if (nodeName.equals(CallookXmlConstants.LastActionDate.toString())) {
         // Provided, but not exposed by openlogbook.
      } else if (nodeName.equals(CallookXmlConstants.Attn.toString())) {
         // Do nothing
      } else if (nodeName.equals(CallookXmlConstants.Address.toString())) {
         // Parent node for address information.  Do nothing.
      } else if (nodeName.equals(CallookXmlConstants.Trustee.toString())) {
         // Do nothing
      } else if (nodeName.equals(CallookXmlConstants.Callook.toString())) {
         // Do nothing
      } else {
         DebugManager.postEvent(InternalEventId.GeneralError, CallookReaderImpl.class.getName(), 
               "Unknown node during callook lookup: " + nodeName) ;
      }
   }
 
   /**
    * Checks the type of callsign we are looking at.
    * 
    * @param node The node to get the data from.
    * @param callLookXmlConstants The callLookXmlConstants object.
    * 
    * @return true if the callsign node contains the status that was passed in.
    */
   private boolean checkCallsignStatus(Node node, CallookXmlConstants callLookXmlConstants) {
      NamedNodeMap attributes = node.getAttributes();
      for (int i = 0; i < attributes.getLength(); i++) {
         Node attribute = attributes.item(i);
         if (attribute.getNodeName().equals(CallookXmlConstants.CallsignStatus.toString())) {
            if (attribute.getNodeValue().equals(callLookXmlConstants.toString())) {
               return true ;
            }
         }
      }
      return false ;
   }
   
}
