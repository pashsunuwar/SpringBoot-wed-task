package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entities.Student;
import com.example.demo.Repository.StudentRepository;

@RestController
@CrossOrigin
public class FirstController {
	
	@Autowired
	StudentRepository repo;
	
	@GetMapping("/allrecords")
	public List <Student> showallrecord() {
		
	List<Student> records = repo.findAll();
		
	return records;
	}
	
	@GetMapping("/showrecord/{r}")
	public Student record(@PathVariable("r")int regno) {
		
		Optional<Student> opt = repo.findById(regno);
		
		if (opt.isPresent()) {
			Student rec = opt.get();
			return rec;
		}
		
		return null;
		
	
		
	}
	
	//delete by id
//	@DeleteMapping("/deleterecord/{regno}")
	@GetMapping("/deleterecord/{regno}")
	public String deleterecord(@PathVariable("regno")int regno) {
		repo.deleteById(regno);
		return " record deleted ";
	}
	
	
	//insert record
	@PostMapping("/insertrecord")
	public String insertrecord(@RequestBody Student s) {
		
		repo.save(s);
//		return s.getRegno() + " " + s.getName() + " lives in " + s.getAddress();
		return " POST record inserted ";
	}
	
	@GetMapping("/save")
	public String RecordSave() {
		
		Student ref = new Student();
		ref.setRegno(13);
		ref.setName("Pash");
		ref.setAddress("Manchester");
		
		repo.save(ref);
		
		return " record save ";
	}
	
	@GetMapping("/savedrecords/{regno}/{name}/{address}")
	public String savedRecord(@PathVariable("regno")int regno, 
			@PathVariable("name") String name, 
			@PathVariable("address")String address) {
		
		Student s = new Student();
		s.setRegno(regno);
		s.setName(name);
		s.setAddress(address);
		
		repo.save(s);

		return " Record successfully saved ";
				
	}

}
