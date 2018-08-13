package servlets.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import servlets.abstr.CustomHttpServlet;
import servlets.api.IInscriptionController;

@RestController
@RequestMapping("/inscription")
public class InscriptionController extends CustomHttpServlet implements IInscriptionController{

	private static final long serialVersionUID = 1L;
	

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public Integer validateConnection(@RequestParam(value = "eMail") String eMail,@RequestParam(value = "pwd") String pwd) {
		if("troll".equals(eMail) && "troll".equals(pwd)) {
			return 1;
		}
		return 0;
	}
	
}
