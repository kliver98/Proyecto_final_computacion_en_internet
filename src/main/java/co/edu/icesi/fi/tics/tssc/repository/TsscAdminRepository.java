package co.edu.icesi.fi.tics.tssc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long> {
	
	Optional<TsscAdmin> findByUser(String user);
	
}
