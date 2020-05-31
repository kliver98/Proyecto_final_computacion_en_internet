package co.edu.icesi.fi.tics.tssc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.dao.TsscTimeControlDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Service
@Transactional
public class TsscTimeControlServiceImpl  implements TsscTimeControlService{

	@Autowired
	private TsscTimeControlDAO tsscTimeControlDAO;
	
	@Override
	public TsscTimecontrol save(TsscTimecontrol tsscTimecontrol) {
		tsscTimeControlDAO.save(tsscTimecontrol);
		return tsscTimeControlDAO.findById(tsscTimecontrol.getId());
	}

	@Override
	public TsscTimecontrol update(TsscTimecontrol tsscTimecontrol) {
		if(tsscTimecontrol == null) {
			throw new NullPointerException("Can not edit the timeControl because it's null the timeControl to edit");
		}
		
		tsscTimeControlDAO.update(tsscTimecontrol);
		return tsscTimeControlDAO.findById(tsscTimecontrol.getId());
	}

	@Override
	public Iterable<TsscTimecontrol> findAll() {
		
		return tsscTimeControlDAO.findAll();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		
		return tsscTimeControlDAO.findById(id);
	}

	@Override
	public TsscTimecontrol delete(TsscTimecontrol tsscTimecontrol) {
		
		return tsscTimeControlDAO.delete(tsscTimecontrol);
	}

}
