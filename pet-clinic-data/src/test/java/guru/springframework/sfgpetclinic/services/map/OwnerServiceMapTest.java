package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    final String lastName= "Smith";

    @BeforeEach
    void setUp() {


        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetMapService());

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
    Set<Owner> ownerSet=ownerServiceMap.findAll();
    assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner=ownerServiceMap.findById(ownerId);
    }

    @Test
    void save() {
        Owner owner2= Owner.builder().id(2L).build();
        Owner savedOwner= ownerServiceMap.save(owner2);
    }

    @Test
    void saveNoId() {
    Owner saveOwner=ownerServiceMap.save(Owner.builder().build());
    assertNotNull(saveOwner);
    assertNotNull(saveOwner.getId());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
        //assertEquals(0,ownerServiceMap.findById(ownerId));
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(lastName);

        assertNotNull(smith);

        assertEquals(ownerId, smith.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("foo");

        assertNull(smith);
    }
}