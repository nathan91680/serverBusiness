package metier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.IAEntity;
import dao.api.IIAEntityDao;
import metier.service.api.IIAEntityService;

@Service("iAEntityService")
public class IAEntityService implements IIAEntityService{
	
	@Autowired
	IIAEntityDao iAEntityDao;

	@Override
	public IAEntity createIAEntity(IAEntity iAEntity) throws Exception {
		// TODO Auto-generated method stub
		IAEntity createdIAEntity = iAEntityDao.create(iAEntity);
		return createdIAEntity;
	}

	@Override
	public IAEntity findIAEntity(Integer id) throws Exception {
		IAEntity iAEntity = iAEntityDao.find(id);
		return iAEntity;
	}

	@Override
	public IAEntity updateIAEntity(IAEntity iAEntity) throws Exception {
		IAEntity updatedIAEntity = iAEntityDao.update(iAEntity);
		return updatedIAEntity;
	}

	@Override
	public Boolean deleteIAEntity(Integer id) throws Exception {
		Boolean success = iAEntityDao.delete(id);
		return success;
	}
}
