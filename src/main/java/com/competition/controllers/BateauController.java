package com.competition.controllers;
import com.competition.entities.Bateaus;
import com.competition.services.BateausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class BateauController {


    @Autowired
    private BateausService service;

    @PostMapping("/addBateau")
    @CrossOrigin(origins = "http://localhost:4200")
    public Bateaus addBateau(@RequestBody Bateaus Bateaus) {
        return service.saveBateau(Bateaus);
    }

    @PostMapping("/addBateaus")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Bateaus> addBateaus(@RequestBody List<Bateaus> Bateaus) {
        return service.saveBateaus(Bateaus);
    }

    @GetMapping("/Bateaus")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Bateaus> findAllBateaus() {
        return service.getBateaus();
    }

    @GetMapping("/BateauById/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Bateaus findBateausById(@PathVariable int id) {
        return service.getBateauById(id);
    }

    @GetMapping("/Bateaus/{name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Bateaus findBateausByName(@PathVariable String name) {
        return service.getBateauByName(name);
    }

    @PutMapping("/updateBateaus/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Bateaus updateBateaus(@RequestBody Bateaus Bateaus) {


        return service.updateBateau(Bateaus);
    }

    @DeleteMapping("/deleteBateaus/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Map<String,Object> deleteBateaus(@PathVariable int id) {
        boolean  bl =   service.deleteBateau(id);
        Map<String,Object> map = new HashMap<>(3);
        if (bl =  true){
            map.put("msg", true);
        }else
            map.put("msg", false);



        return  map ;
    }
}
