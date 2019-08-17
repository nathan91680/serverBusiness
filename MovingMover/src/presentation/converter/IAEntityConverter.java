package presentation.converter;

import bean.IAEntity;
import presentation.dto.IAEntityDto;

public class IAEntityConverter {
	
	public static IAEntityDto toDto(IAEntity iAEntity) throws Exception {
		IAEntityDto iAEntityDto = new IAEntityDto();
		iAEntityDto.setId(iAEntity.getId());
		iAEntityDto.setName(iAEntity.getName());
		iAEntityDto.setAlgo(iAEntity.getAlgo());
		return iAEntityDto;
	}
	
	public static IAEntity toEntity(IAEntityDto iAEntityDto) throws Exception {
		IAEntity iAEntity = new IAEntity();
		iAEntity.setId(iAEntityDto.getId());
		iAEntity.setName(iAEntityDto.getName());
		iAEntity.setAlgo(iAEntityDto.getAlgo());
		return iAEntity;
	}

}
