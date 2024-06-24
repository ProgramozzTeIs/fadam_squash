
package pti.sb_eurochange_rest.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pti.sb_eurochange_rest.dto.ExchangeDTO;
import pti.sb_eurochange_rest.service.AppService;

@RestController
public class AppController {

	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/exchange")
	public ExchangeDTO doExchange(@RequestParam("huf") int huf) throws JDOMException, IOException {
		
		ExchangeDTO dto = service.getEuroByHuf(huf);
		
		
		
		
		return dto;
		
	}
	
}
