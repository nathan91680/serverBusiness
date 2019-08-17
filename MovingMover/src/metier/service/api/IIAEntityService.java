package metier.service.api;

import bean.IAEntity;

public interface IIAEntityService {
	
public IAEntity createIAEntity(IAEntity user) throws Exception;
	
	public IAEntity findIAEntity(Integer id) throws Exception;
	
	public IAEntity updateIAEntity(IAEntity user) throws Exception;
	
	public Boolean deleteIAEntity(Integer id) throws Exception;

}
