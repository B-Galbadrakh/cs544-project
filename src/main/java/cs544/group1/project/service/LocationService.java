package cs544.group1.project.service;

import cs544.group1.project.domain.Location;
import cs544.group1.project.dto.LocationResponse;

import java.util.List;

public interface LocationService {

	public void save(Location location);
	
	public List<LocationResponse> findAll();
	
	public LocationResponse findLocationResponseById(int locationid);
	
	public Location findById(int locationid);
	
	public Location update(int locationId, Location location);
	
	public void delete(int locationId);

}
