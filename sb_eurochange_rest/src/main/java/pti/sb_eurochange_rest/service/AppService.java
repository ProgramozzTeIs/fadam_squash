package pti.sb_eurochange_rest.service;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pti.sb_eurochange_rest.dto.ExchangeDTO;
import pti.sb_eurochange_rest.xml.XMLParser;

@Service
public class AppService {
	
	
	@Autowired
	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
	}

	private XMLParser parser;

	public ExchangeDTO getEuroByHuf(int huf) throws JDOMException, IOException {
		
		RestTemplate template = new RestTemplate();
		String url = "http://api.napiarfolyam.hu/?valuta=eur";
		String resultXml = template.getForObject(url, String.class);
		float euroCurrancy = parser.getEuroCurrency(resultXml);
		
		float euro = huf/euroCurrancy;
		
		ExchangeDTO dto = new ExchangeDTO();
		
		dto.setEur(euro);
		
		return dto;
	}

}
