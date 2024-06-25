package pti.sb_sqashadmin_mvc.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Repository;

import pti.sb_sqashadmin_mvc.dto.MatchDTO;
@Repository
public class XMLParser {

	public void export(List<MatchDTO> matchDtos, String filePath) throws IOException {
		
		filePath = filePath + ".xml";
		
		FileWriter writer = new FileWriter(filePath);
		
		XMLOutputter outputter = new XMLOutputter();
		
		Document document = new Document();
		
		/*	<matchs>
		 * 		<match>
		 * 			<user1name>
		 * 			<user1points>
		 * 			<user2name>
		 * 			<user2points>
		 * 			<placename>
		 * 			<placeprice>
		 * 			<matchdate>
		 * 			<placepriceeur>
		 * 		</match>
		 * 	</matchs>
		 */
		
		Element rootelement = new Element("matchs");
		
		for(int index = 0; index < matchDtos.size(); index++) {
		
			MatchDTO currentMatch = matchDtos.get(index);
			
			Element matchElement = new Element("match");
			
			Element user1name = new Element("user1name");
			user1name.setText(currentMatch.getUser1Name());
			matchElement.addContent(user1name);
			
			Element user1points = new Element("user1points");
			user1points.setText (currentMatch.getUser1Points()+"");
			matchElement.addContent(user1points);
			
			Element user2name = new Element("user2name");
			user2name.setText(currentMatch.getUser2Name());
			matchElement.addContent(user2name);
			
			Element user2points = new Element("user2points");
			user2points.setText(currentMatch.getUser2Points()+"");
			matchElement.addContent(user2points);
			
			Element locationName = new Element("locationname");
			locationName.setText(currentMatch.getPlaceName());
			matchElement.addContent(locationName);
			
			Element locationPrice = new Element("locationprice");
			locationPrice.setText(currentMatch.getPlacePrice()+"");
			matchElement.addContent(locationPrice);
			
			Element locationPriceEur = new Element("locationpriceeur");
			locationPriceEur.setText(currentMatch.getPlacePriceEur()+"");
			matchElement.addContent(locationPriceEur);
			
			Element localDate = new Element("localdate");
			localDate.setText(currentMatch.getMatchDate()+"");
			matchElement.addContent(localDate);
			
			rootelement.addContent(matchElement);
			
			
		
		}
		document.setRootElement(rootelement);
		outputter.setFormat(Format.getPrettyFormat());
		outputter.output(document, writer);
		
	}

	public List<MatchDTO> importXml(String filePath) throws JDOMException, IOException {
		
		SAXBuilder sax = new SAXBuilder();
		
		Document doc = sax.build(new File(filePath));

        Element rootElement = doc.getRootElement();
		
        List<Element> matchElements = rootElement.getChildren("match");
        
        List<MatchDTO> matchDtos = new ArrayList<>();
        
        for(int index = 0; index < matchElements.size(); index++) {
        	
        	
        	
        	Element currentMatch = matchElements.get(index);
        	
        	String name1 = currentMatch.getChildText("user1name");
        	System.out.println(name1);
        	int user1points = Integer.parseInt(currentMatch.getChildText("user1points"));
        	System.out.println(user1points);
        	String name2 = currentMatch.getChildText("user2name");
        	int user2points = Integer.parseInt(currentMatch.getChildText("user2points"));
        	String locationName = currentMatch.getChildText("locationname");
        	int locationPrice = Integer.parseInt(currentMatch.getChildText("locationprice"));
        	float locationPriceEur = Float.parseFloat(currentMatch.getChildText("locationpriceeur"));
        	LocalDate localDate = LocalDate.parse(currentMatch.getChildText("localdate"), DateTimeFormatter.ISO_LOCAL_DATE);
        	
        	MatchDTO matchDto = new MatchDTO(name1, user1points, name2, user2points, locationName, locationPrice, localDate, locationPriceEur);
        	matchDtos.add(matchDto);
        	
        	
        }
        
        return matchDtos;
	}

	
}
