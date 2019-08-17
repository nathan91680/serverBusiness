package servlets.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bean.IAEntity;
import dao.exception.DAOException;
import metier.service.api.IIAEntityService;
import presentation.converter.IAEntityConverter;
import presentation.dto.IAEntityDto;
import servlets.abstr.CustomHttpServlet;
import servlets.api.IIAEntityController;

@RestController
public class IAController extends CustomHttpServlet implements IIAEntityController{
	
    private static final long serialVersionUID = 1L;
	
	@Autowired
	private IIAEntityService iAEntityService;

	@Override
	@RequestMapping(value = "/ias", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<IAEntityDto>> getIAEntities() {
		List<IAEntityDto> iAEntityDtos = new ArrayList<IAEntityDto>();
		ResponseEntity<List<IAEntityDto>> response = new ResponseEntity<List<IAEntityDto>>(iAEntityDtos,HttpStatus.FOUND);
		return response;
	}

	@Override
	@RequestMapping(value = "/ias/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<IAEntityDto> getIAEntity(@PathVariable Integer id) {
		IAEntity iAEntity = null;
		IAEntityDto iAEntityDto = new IAEntityDto();
		ResponseEntity<IAEntityDto> response = null;
		try {
			iAEntity = iAEntityService.findIAEntity(id);
			iAEntityDto = IAEntityConverter.toDto(iAEntity);
			response = new ResponseEntity<IAEntityDto>(iAEntityDto,HttpStatus.OK);
		} 
		catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<IAEntityDto>(iAEntityDto,HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<IAEntityDto>(iAEntityDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@Override
	@RequestMapping(value = "/ias", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<IAEntityDto> createIAEntity(@RequestBody IAEntityDto iAEntityDto) {
		IAEntity iAEntity = new IAEntity();
		
		try {
			iAEntity = IAEntityConverter.toEntity(iAEntityDto);
			iAEntity = iAEntityService.createIAEntity(iAEntity);
			iAEntityDto = IAEntityConverter.toDto(iAEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<IAEntityDto> response = new ResponseEntity<IAEntityDto>(iAEntityDto,HttpStatus.OK);
		return response;
	}

	@Override
	@RequestMapping(value = "/ias", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<IAEntityDto> modifyIAEntity(@RequestBody IAEntityDto iAEntityDto) {
		
		IAEntity iAEntity = new IAEntity();
		try {
			iAEntity = IAEntityConverter.toEntity(iAEntityDto);
			iAEntity = iAEntityService.updateIAEntity(iAEntity);
			iAEntityDto = IAEntityConverter.toDto(iAEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<IAEntityDto> response = new ResponseEntity<IAEntityDto>(iAEntityDto,HttpStatus.OK);
		return response;
	}

	@Override
	@RequestMapping(value = "/ias/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteIAEntity(@PathVariable Integer id) {
		Boolean bool = Boolean.FALSE;
		ResponseEntity<Boolean> response = null;
		try {
			bool = iAEntityService.deleteIAEntity(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bool) {
			response = new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<Boolean>(Boolean.FALSE,HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
