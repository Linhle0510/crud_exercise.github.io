package com.crud.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crud.demo.exception.RecordNotFoundException;
import com.crud.demo.model.PersonEntity;
import com.crud.demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonEntity> getAllPeople()
	{
		List<PersonEntity> result = (List<PersonEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PersonEntity>();
		}
	}
	
	public PersonEntity getPersonById(Long id) throws RecordNotFoundException 
	{
		Optional<PersonEntity> person = repository.findById(id);
		
		if(person.isPresent()) {
			return person.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
	
	public PersonEntity createOrUpdatePerson(PersonEntity entity) 
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<PersonEntity> person = repository.findById(entity.getId());
			
			if(person.isPresent()) 
			{
				PersonEntity newEntity = person.get();
				newEntity.setFullName(entity.getFullName());
				newEntity.setJob(entity.getJob());
				newEntity.setBirthDay(entity.getBirthDay());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deletePersonById(Long id) throws RecordNotFoundException 
	{
		Optional<PersonEntity> person = repository.findById(id);
		
		if(person.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	} 
	
}