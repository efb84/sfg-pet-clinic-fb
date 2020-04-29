package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {




    private final SpecialityService specialityService;



    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
     private final VisitService visitService;



 /*   public DataLoader() { ownerService= new OwnerServiceMap();
        vetService=new VetMapService();
  }*/


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();

        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("figoo");
        PetType saveDogType = petTypeService.save(dog);
        // System.out.println("asdasds:"+saveDogType.getName());

        PetType cat = new PetType();
        cat.setName("kedicik");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiolgy = new Speciality();
        radiolgy.setDescription("Radiolgy");
        Speciality savedRadiolgy = specialityService.save(radiolgy);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);


        Speciality dentisitry = new Speciality();
        dentisitry.setDescription("Dentisitry");
        Speciality savedDentisitry = specialityService.save(dentisitry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Fikret");
        owner1.setLastName("byk");
        owner1.setAdress("Nalbantoğlu");
        owner1.setCity("bursa");
        owner1.setTelephone("123456788");


        Pet fikos = new Pet();
        fikos.setPetType(saveDogType);
        fikos.setOwner(owner1);
        fikos.setBirthDate(LocalDate.now());
        fikos.setName("Figooooo111");

        owner1.getPets().add(fikos);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Ayse");
        owner2.setLastName("byk");
        owner2.setAdress("kalsacekntoğlu");
        owner2.setCity("ankara");
        owner2.setTelephone("2222000339");

        Pet aysesPet = new Pet();
        aysesPet.setPetType(saveCatType);
        aysesPet.setOwner(owner2);
        aysesPet.setBirthDate(LocalDate.now());
        aysesPet.setName("ayseciikkkkk!!");

        owner2.getPets().add(aysesPet);

        ownerService.save(owner2);

        Visit catVisit=new Visit();
        catVisit.setPet(aysesPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

      /*  Owner owner3 = new Owner();
        owner3.setFirstName("asd");
        owner3.setLastName("bos");
        owner3.setAdress("ahmetveysel");
        owner3.setCity("elazığ");
        owner3.setTelephone("23333111a");

        ownerService.save(owner3);
*/
        System.out.println("Owners are loaded.....");


        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("ahmet");
        vet1.setLastName("alan");
        vet1.getSpecialities().add(savedRadiolgy);
        vet1.getSpecialities().add(savedDentisitry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("hasan");
        vet2.setLastName("alan");
        vet2.getSpecialities().add(savedSurgery);


        vetService.save(vet2);

        System.out.println("Vets are loaded.1...");
        System.out.println("version: " + SpringVersion.getVersion());
    }
}
