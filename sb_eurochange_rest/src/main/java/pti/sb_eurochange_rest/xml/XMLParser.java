package pti.sb_eurochange_rest.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;
@Component
public class XMLParser {

	
	
	
	public float getEuroCurrency(String xmlString) throws JDOMException, IOException {
		
		float returnEuro = 0;
		
		SAXBuilder sb = new SAXBuilder();
		
		StringReader reader = new StringReader(xmlString);
		
		Document document = sb.build(reader);
		
		Element rootelement = document.getRootElement();
		
		Element devizaElement = rootelement.getChild("deviza");

		List<Element> itemList = devizaElement.getChildren("item");
		
		for (int index = 0; index < itemList.size(); index++) {
			
			Element currentElement = itemList.get(index);
			
			
			
			Element bankElement = currentElement.getChild("bank");
			
			String bankName = bankElement.getValue();

			if(bankName.equals("mnb")) {
				
				Element euroCurr = currentElement.getChild("kozep");
				
				returnEuro = Float.parseFloat(euroCurr.getValue());
				break;
			}
			
			
		}
		
		return returnEuro;
	}
	
}
