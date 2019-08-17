package servlets.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import presentation.dto.IAEntityDto;

public interface IIAEntityController {
	
	public ResponseEntity<List<IAEntityDto>> getIAEntities();
	public ResponseEntity<IAEntityDto> getIAEntity(Integer id);
	public ResponseEntity<IAEntityDto> createIAEntity(IAEntityDto iAEntityDto);
	public ResponseEntity<IAEntityDto> modifyIAEntity(IAEntityDto iAEntityDto);
	public ResponseEntity<Boolean> deleteIAEntity(Integer id);

}
