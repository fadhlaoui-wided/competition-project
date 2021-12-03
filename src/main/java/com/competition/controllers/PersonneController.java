package com.competition.controllers;
import com.competition.entities.Personnes;
import com.competition.services.PersonnesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class PersonneController {


    @Autowired
    private PersonnesService service;

    @PostMapping("/addPersonne")
    @CrossOrigin(origins = "http://localhost:4200")
    public Personnes addPersonne(@RequestBody Personnes Personnes) {
        return service.savePersonne(Personnes);
    }

    @PostMapping("/addPersonnes")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Personnes> addPersonnes(@RequestBody List<Personnes> Personnes) {
        return service.savePersonnes(Personnes);
    }

    @GetMapping("/Personnes")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Personnes> findAllPersonnes() {
        return service.getPersonnes();
    }

    @GetMapping("/PersonneById/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Personnes findPersonnesById(@PathVariable int id) {
        return service.getPersonneById(id);
    }

    @GetMapping("/Personnes/{name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Personnes findPersonnesByName(@PathVariable String name) {
        return service.getPersonneByName(name);
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Personnes updatePersonnes(@RequestBody Personnes Personnes) {


        return service.updatePersonne(Personnes);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Map<String,Object> deletePersonnes(@PathVariable int id) {
        boolean  bl =   service.deletePersonne(id);
        Map<String,Object> map = new HashMap<>(3);
        if (bl =  true){
            map.put("msg", true);
        }else
            map.put("msg", false);



        return  map ;
    }
}
