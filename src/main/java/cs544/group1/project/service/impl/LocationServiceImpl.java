package cs544.group1.project.service.impl;

import cs544.group1.project.domain.Location;
import cs544.group1.project.dto.LocationResponse;
import cs544.group1.project.repo.LocationRepo;
import cs544.group1.project.service.LocationService;
import cs544.group1.project.service.mappers.LocationResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LocationServiceImpl implements LocationService {
	
	@Autowired
    LocationRepo locationRepository;

	@Autowired
	protected LocationResponseMapper responseMapper;

	public void save(Location location) {
		
		locationRepository.save(location);
	}
	
	public List<LocationResponse> findAll(){
		List<Location> locations = locationRepository.findAll();
//		List<LocationResponse> locationResponses = new ArrayList<>();
		return convertEntityListToResponse(locations);

//		for(Location l: locations) {
//			LocationResponse locationResponse = new LocationResponse();
//			locationResponse.setBuildNo(l.getBuildNo());
//			locationResponse.setCity(l.getCity());
//			locationResponse.setCreatedDate(l.getCreatedDate());
//			locationResponse.setId(l.getId());
//			locationResponse.setRoomNo(l.getRoomNo());
//			locationResponse.setState(l.getState());
//			locationResponse.setStreet(l.getStreet());
//			locationResponse.setUpdatedDate(l.getUpdatedDate());
//			locationResponse.setZipcode(l.getZipcode());
//			locationResponses.add(locationResponse);
//		}
//		return locationResponses;
	}
	
	public LocationResponse findLocationResponseById(int locationid) {
		Optional<Location> location = locationRepository.findById(locationid);
		LocationResponse locationResponse = new LocationResponse();
		if(location.isPresent()) {
//			locationResponse.setBuildNo(location.get().getBuildNo());
//			locationResponse.setCity(location.get().getCity());
//			locationResponse.setCreatedDate(location.get().getCreatedDate());
//			locationResponse.setId(location.get().getId());
//			locationResponse.setRoomNo(location.get().getRoomNo());
//			locationResponse.setState(location.get().getState());
//			locationResponse.setStreet(location.get().getStreet());
//			locationResponse.setUpdatedDate(location.get().getUpdatedDate());
//			locationResponse.setZipcode(location.get().getZipcode());
			Location location1 = location.get();
			return convertEntityToResponse(location1);
		 //return locationResponse;
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

	@Override
	public List<LocationResponse> convertEntityListToResponse(List<Location> locationList) {
		if(null == locationList){
			return null;
		}
		else {
			return locationList.stream()
					.map(responseMapper::map)
					.collect(Collectors.toList());
		}
	}

	@Override
	public LocationResponse convertEntityToResponse(Location location) {
		return responseMapper.map(location);
	}

}
