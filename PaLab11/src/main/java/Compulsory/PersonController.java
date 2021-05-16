package Compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping("/working")
    public String test(){
        return "It's working.";
    }

    @GetMapping("/list")
    public List<Person> getPersons() {
        return service.listPersons();
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        List <Person> personList = service.listPersons();
        person.setId((long) (personList.size()+1));
        service.save(person);
        return person;
    }

    @PutMapping("/modify/{id}")
    public Person modifyPersonName(@PathVariable (value = "id") long id, @RequestBody Person person) {
        service.get(id).setFirstName(person.getFirstName());
        service.get(id).setLastName(person.getLastName());
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deletePerson(@PathVariable (value = "id") long id) {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
