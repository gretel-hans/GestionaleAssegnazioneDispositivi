package com.hans.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.Device;
import com.hans.model.DeviceAssignment;
import com.hans.model.DeviceState;
import com.hans.repository.DeviceAssignmentPageableRepository;
import com.hans.repository.DeviceAssignmentRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class DeviceAssignmentService {

	@Autowired DeviceAssignmentRepository db;
	@Autowired DeviceAssignmentPageableRepository dbPage;
	@Autowired DeviceService DeviceDb;
	@Autowired UserService userDb;
	
	public boolean createOrUpdateAssignment(DeviceAssignment d) {
		List<Device> listaDevice =new ArrayList<Device>();
		//d.setDevice(null);
		d.getDevice().forEach(device->{
			if(device.getState().equals(DeviceState.Disponibile) && DeviceDb.DeviceExistsObj(device)) {
				listaDevice.add(device);
				device.setState(DeviceState.Assegnato);
				DeviceDb.createOrUpdateDevice(device);
			}
		});
		d.setDevice(listaDevice);
		System.out.println(d.getUser());
		if(listaDevice.size()!=0 && userDb.UserExistsDb(d.getUser())) {
			db.save(d);
			return true;
		}else
		return false;
	}
	
	public DeviceAssignment updateAssignment(DeviceAssignment d) {
		//List<Device> lista
		if(AssignmentExists(d.getId())&&userDb.UserExists(d.getUser().getId())&& d.getDevice().size()>0) {
		//	if(d.getDevice().forEach(e->{})
			return db.save(d);
		}else 
			return null;
	}
	
	public String deleteAssignment(Long id) {
		if (AssignmentExists(id)) {
			db.deleteById(id);
			return "Assignment with id: "+id+" removed!";
		}else 
			throw new EntityExistsException("ERROR!! Assignment passed doesn't exist!");
		
	}
	
	public DeviceAssignment findAssignment(Long id) {
		if (AssignmentExists(id)) {
			return db.findById(id).get();
		}else 
			throw new EntityExistsException("ERROR!! Assignment passed doesn't exist!");
		
	}
	
	public List<DeviceAssignment> findAllAssignments() {
		return db.findAll();
	}
	
	public Page<DeviceAssignment> findAllAssignmentsPage(Pageable p) {
		return dbPage.findAll(p);
	}
	
	public boolean AssignmentExists(Long id) {
		if(db.existsById(id)) {
			return true;
		}else 
			return false;
	}
	


}
