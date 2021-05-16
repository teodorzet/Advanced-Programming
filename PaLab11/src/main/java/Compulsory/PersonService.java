package Compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<Person> listPersons() {
        return (List<Person>) repository.findAll();
    }

    public void save(Person person) {
        repository.save(person);
    }

    public Person get(long id){
        return repository.findById(id).get();
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
