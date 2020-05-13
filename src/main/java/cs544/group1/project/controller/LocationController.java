package cs544.group1.project.controller;

import cs544.group1.project.domain.Location;
import cs544.group1.project.dto.LocationResponse;
import cs544.group1.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping()
    public void createLocation(@RequestBody Location location) {
    	locationService.save(location);
    }
    
    @GetMapping()
    public List<LocationResponse> getLocations(){
    	return locationService.findAll();
    }
    
    @GetMapping("/{locationid}")
    public LocationResponse getLocationById(@PathVariable int locationid) {
    	return locationService.findLocationResponseById(locationid);
    }
    
    @PostMapping("/{locationid}")
    public Location updateById(@PathVariable int Locationid, @RequestBody Location location) {
    	return locationService.update(Locationid, location);
    }
    
    @DeleteMapping("/{locationid}")
    public void deleteLocation(@PathVariable int locationid) {
    	locationService.delete(locationid);
    }
    
    
}