package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Person;
import guru.springframework.sfgpetclinic.services.CrudService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PersonMapService extends AbstractMapService<Person,Long> implements CrudService<Person,Long> {
    @Override
    public Set<Person> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Person Object) {
    super.delete(Object);
    }

    @Override
    public Person save(Person Object) {
        return super.save(Object);
    }

    @Override
    public Person findById(Long id) {
        return super.findById(id);
    }
}
