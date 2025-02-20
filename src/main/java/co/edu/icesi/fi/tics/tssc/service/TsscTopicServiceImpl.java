package co.edu.icesi.fi.tics.tssc.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.dao.TsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Service
@Transactional
public class TsscTopicServiceImpl implements TsscTopicService {

	@Autowired
	private TsscTopicDAO tsscTopicDAO;
	
	@Override
	public TsscTopic save(TsscTopic topic) {
		long springs = topic.getDefaultSprints(), groups = topic.getDefaultGroups();
		if (springs<1 || groups<1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscTopicDAO.save(topic);
		return tsscTopicDAO.findById(topic.getId());
	}

	@Override
	public TsscTopic update(TsscTopic topic) {
		if (topic==null)
			throw new NullPointerException("Can not edit the topic because it's null the topic to edit");
		long springs = topic.getDefaultSprints(), groups = topic.getDefaultSprints();
		if (springs<1 || groups<1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscTopicDAO.update(topic);
		return tsscTopicDAO.findById(topic.getId());
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return tsscTopicDAO.findAll();
	}
	
	@Override
	public TsscTopic findById(long id) {
		return tsscTopicDAO.findById(id);
	}
	
	@Override
	public TsscTopic delete(TsscTopic topic) {
		return tsscTopicDAO.delete(topic);
	}




	
}
