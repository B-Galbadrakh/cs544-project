package cs544.group1.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.group1.project.domain.Location;
import cs544.group1.project.domain.User;
import cs544.group1.project.repo.LocationRepo;
import cs544.group1.project.repo.UserRepo;
import cs544.group1.project.service.response.LocationResponse;
import cs544.group1.project.service.response.UserResponse;

@Component
public class LocationService {
	
	@Autowired
	LocationRepo locationRepository;
	
	public void save(Location location) {
		
		locationRepository.save(location);
	}
	
	public List<LocationResponse> findAll(){
		List<Location> locations = locationRepository.findAll();
		List<LocationResponse> locationResponses = new ArrayList<>();
		for(Location l: locations) {
			LocationResponse locationResponse = new LocationResponse();
			locationResponse.setBuildNo(l.getBuildNo());
			locationResponse.setCity(l.getCity());
			locationResponse.setCreatedDate(l.getCreatedDate());
			locationResponse.setId(l.getId());
			locationResponse.setRoomNo(l.getRoomNo());
			locationResponse.setState(l.getState());
			locationResponse.setStreet(l.getStreet());
			locationResponse.setUpdatedDate(l.getUpdatedDate());
			locationResponse.setZipcode(l.getZipcode());
			locationResponses.add(locationResponse);
		}
		return locationResponses;
	}
	
	public LocationResponse findLocationResponseById(int locationid) {
		Optional<Location> location = locationRepository.findById(locationid);
		LocationResponse locationResponse = new LocationResponse();
		if(location.isPresent()) {
			locationResponse.setBuildNo(location.get().getBuildNo());
			locationResponse.setCity(location.get().getCity());
			locationResponse.setCreatedDate(location.get().getCreatedDate());
			locationResponse.setId(location.get().getId());
			locationResponse.setRoomNo(location.get().getRoomNo());
			locationResponse.setState(location.get().getState());
			locationResponse.setStreet(location.get().getStreet());
			locationResponse.setUpdatedDate(location.get().getUpdatedDate());
			locationResponse.setZipcode(location.get().getZipcode());
		 return locationResponse;
		}
		else {
			return null;
		}
	}
	
	public Location findById(int locationid) {
		Optional<Location> location = locationRepository.findById(locationid);
		return location.isPresent() ? location.get() : null;
	}
	
	public Location update(int locationId, Location location) {
		Location oldLocation = findById(locationId);
    	if(oldLocation == null){
    		return null;
    	}
    	oldLocation.setRoomNo(location.getRoomNo());
    	return locationRepository.save(oldLocation);
	}
	
	public void delete(int locationId) {
		Location oldLocation = findById(locationId);
    	if(oldLocation == null){
    		return;
    	}
    	locationRepository.deleteById(locationId);
	}

}
