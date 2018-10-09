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
import com.mvc.mysql.model.DistributorMV;
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
	public ResponseEntity<InventoryMV> postInventory(InventoryVM inventory)
			throws BadRequestException, InternalServerException {
		try {
			logger.info("Create inventory");
			if (inventory == null) {
				throw new BadRequestException("You can't send null in fields..");

			}
			InventoryEntity c = modelMapper.map(inventory, InventoryEntity.class);
			c.setCreatedOn(created);
			c.setUpdatedOn(updated);
			Optional<DistributorEntity> distributorEntity = distributorRepository
					.findById(inventory.getDistributorId());
			if (!distributorEntity.isPresent()) {

			}
			c.setemployeeCategory(distributorEntity.get());
			InventoryEntity _inventory = inventoryRepository.save(c);
			return new ResponseEntity<InventoryMV>(modelMapper.map(_inventory, InventoryMV.class), HttpStatus.OK);

		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");

		}
	}

	/**
	 * @description Delete inventoryItems by id
	 */
	@Override
	public ResponseEntity<String> deleteInventory(long id) throws ResourceNotFound {

		if (inventoryRepository.existsById(id)) {
			logger.info("delete inventory by id=" + id);

			inventoryRepository.deleteById(id);

			return new ResponseEntity<>("Inventory item has been deleted!", HttpStatus.OK);
		} else {
			throw new ResourceNotFound("inventory id not found..");
		}
	}

	/**
	 * @description get all inventoryItems
	 */
	@Override
	public List<InventoryMV> getAllInventory() throws InternalServerException, ResourceNotFound, BadRequestException {
		try {
			logger.info("Get inventory details..");

			List<InventoryEntity> inventory = new ArrayList<>();

			inventoryRepository.findAll().forEach(inventory::add);
			Type listType = new TypeToken<List<InventoryEntity>>() {
			}.getType();
			if (inventory.isEmpty()) {

				throw new ResourceNotFound("inventoryr not found");
			} else {
				return modelMapper.map(inventory, listType);
			}
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");

		}
	}

	/**
	 * @description Update inventoryItems
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseEntity<InventoryMV> updateInventory(long id, InventoryVM inventory)
			throws BadRequestException, InternalServerException {
		try {
			if (inventory == null) {
				throw new BadRequestException("You can't send null in fields..");
			}
			logger.info("Update inventory");

			Optional<InventoryEntity> inventoryData = inventoryRepository.findById(id);

			if (inventoryData.isPresent()) {
				InventoryEntity _inventory = inventoryData.get();
				_inventory.setProductName(inventory.getProductName());
				_inventory.setUpdatedOn(updated);

				InventoryEntity save = inventoryRepository.save(_inventory);

				return new ResponseEntity(save, HttpStatus.OK);
			} else {
				throw new ResourceNotFound("Inventory not found");
			}
		} catch (Exception e) {

			throw new InternalServerException("internal server error");
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
