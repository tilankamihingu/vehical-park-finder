package com.tilanka.parkcomp.controller;

import com.tilanka.parkcomp.exception.ResourceNotFoundException;
import com.tilanka.parkcomp.model.Park;
import com.tilanka.parkcomp.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ParkController {

    @Autowired
    private ParkRepository parkRepository;

    //get all parks
    @GetMapping("/parks")
    public List<Park> getAllParks(){
        return parkRepository.findAll();
    }

    //create park rest api
    @PostMapping("/parks")
    public Park createPark(@RequestBody Park park){
        return parkRepository.save(park);
    }

    //get employee by id rest api
    @GetMapping("/parks/{id}")
    public ResponseEntity<Park> getParkById(@PathVariable Long id){
        Park park = parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park not exist with id:" + id));
        return ResponseEntity.ok(park);
    }

    @GetMapping("/park")
    public List<Park> findByName(@RequestParam Optional<String> name){
        return parkRepository.findByName(name.orElse(""));
    }

    //update park rest api
    @PutMapping("/parks/{id}")
    public ResponseEntity<Park> updatePark(@PathVariable Long id, @RequestBody Park parkDetails){
        Park park = parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park not exist with id:" + id));

        park.setName(parkDetails.getName());
        park.setAddress(parkDetails.getAddress());
        park.setPhone(parkDetails.getPhone());
        park.setSlots(parkDetails.getSlots());
        park.setType(parkDetails.getType());
        park.setPrice(parkDetails.getPrice());
        park.setImageUrl(parkDetails.getImageUrl());

        Park updatePark = parkRepository.save(park);
        return ResponseEntity.ok(updatePark);
    }

    //delete park rest api
    @DeleteMapping("/parks/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePark(@PathVariable Long id){
        Park park = parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park not exist with id:" + id));

        parkRepository.delete(park);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
