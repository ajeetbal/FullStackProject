package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.entity.DistributorEntity;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.repo.InventoryRepository;
import com.mvc.mysql.repo.DistributorRepository;
import com.mvc.mysql.service.InventoryService;

@Service
public class InventoryServiceImplementation implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	DistributorRepository distributorRepository;

	@Autowired
	ModelMapper modelMapper;

	Date created = new Date();
	Date updated = new Date();
	final static Logger logger = LoggerFactory.getLogger(DistributorServiceImplementation.class);

	/**
	 * @description create inventoryItems
	 */
	@Override
	public InventoryMV postCustomer(InventoryVM customer) throws BadRequestException, InternalServerException {
		try {
			logger.info("Create inventory");
			if (customer == null) {
				throw new BadRequestException("You can't send null in fields..");

			}
			InventoryEntity c = modelMapper.map(customer, InventoryEntity.class);
			c.setCreatedOn(created);
			c.setUpdatedOn(updated);
			Optional<DistributorEntity> employee = distributorRepository.findById(customer.getEmployeeId());
			if (!employee.isPresent()) {

			}
			c.setemployeeCategory(employee.get());
			InventoryEntity _customer = inventoryRepository.save(c);
			return modelMapper.map(_customer, InventoryMV.class);
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");

		}
	}

	/**
	 * @description Delete inventoryItems by id
	 */
	@Override
	public ResponseEntity<String> deleteCustomer(long id) throws ResourceNotFound {

		if (inventoryRepository.existsById(id)) {
			logger.info("delete inventory by id=" + id);

			inventoryRepository.deleteById(id);

			return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
		} else {
			throw new ResourceNotFound("inventory id not found..");
		}
	}

	/**
	 * @description get all inventoryItems
	 */
	@Override
	public List<InventoryMV> getAllCustomers() throws InternalServerException, ResourceNotFound, BadRequestException {
		try {
			logger.info("Get inventory details..");

			List<InventoryEntity> customers = new ArrayList<>();

			inventoryRepository.findAll().forEach(customers::add);
			Type listType = new TypeToken<List<InventoryEntity>>() {
			}.getType();
			if (customers.isEmpty()) {

				throw new ResourceNotFound("inventoryr not found");
			} else {
				return modelMapper.map(customers, listType);
			}
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");

		}
	}

	/**
	 * @description Update inventoryItems
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<InventoryMV> updateCustomer(long id, InventoryVM customer)throws BadRequestException,InternalServerException {
try {
	if(customer==null)
	{
		throw new BadRequestException("You can't send null in fields..");
	}
		logger.info("Update inventory");

		Optional<InventoryEntity> customerData = inventoryRepository.findById(id);

		if (customerData.isPresent()) {
			InventoryEntity _customer = customerData.get();
			_customer.setProductName(customer.getProductName());
			_customer.setUpdatedOn(updated);

			return new ResponseEntity<InventoryMV>(
					(MultiValueMap<String, String>) (MultiValueMap<String, String>) inventoryRepository.save(_customer),
					HttpStatus.OK);
		}  else {
			throw new ResourceNotFound("Inventory not found");
		}
}catch(Exception e)
{
	throw new InternalServerException("Internal Server Error");
}
	}

	/**
	 * @description Find inventoryItems
	 */
	@Override
	public Optional<InventoryEntity> findById(Long id) {
		// TODO Auto-generated method stub
		logger.info("Find inventory element By id..");

		return inventoryRepository.findById(id);
	}
}
