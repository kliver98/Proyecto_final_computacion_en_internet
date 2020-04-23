package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;

@Service
@Transactional
public class TsscAdminServiceImpl implements TsscAdminService {

	@Autowired
	private TsscAdminRepository tsscAdminRepository;
	
	@Override
	public void save(TsscAdmin user) {
		tsscAdminRepository.save(user);
	}

	@Override
	public Optional<TsscAdmin> findById(long id) {
		return tsscAdminRepository.findById(id);
	}

	@Override
	public Iterable<TsscAdmin> findAll() {
		return tsscAdminRepository.findAll();
	}

	@Override
	public void delete(TsscAdmin user) {
		tsscAdminRepository.delete(user);
	}

	@Override
	public AdminType[] getTypes() {
		return AdminType.values();
	}

}
