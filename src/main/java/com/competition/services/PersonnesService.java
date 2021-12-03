package com.competition.services;
import com.competition.entities.Personnes;
import com.competition.repositories.PersonnesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonnesService {
    @Autowired
    private PersonnesRepository repository;

    public Personnes savePersonne(Personnes Personne) {
        return repository.save(Personne);
    }

    public List<Personnes> savePersonnes(List<Personnes> Personnes) {
        return repository.saveAll(Personnes);
    }

    public List<Personnes> getPersonnes() {
        return repository.findAll();
    }

    public Personnes getPersonneById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Personnes getPersonneByName(String name) {
        return repository.findByNom(name);
    }

    public boolean deletePersonne(int id) {
        repository.deleteById(id);
        return true ;
    }

    public Personnes updatePersonne(Personnes Personne) {
        Personnes existingPersonnes = repository.findById(Personne.getId()).orElse(null);
         if ( Personne.getAnnee() != null  && Personne.getAnnee() != "")
        existingPersonnes.setAnnee(Personne.getAnnee());
        if ( Personne.getNom() != null  && Personne.getNom() != "")
        existingPersonnes.setNom(Personne.getNom());
        if ( Personne.getPrenom() != null  && Personne.getPrenom() != "")
        existingPersonnes.setPrenom(Personne.getPrenom());
        if ( Personne.getType() != null  && Personne.getType() != "")
        existingPersonnes.setType(Personne.getType());
        if ( Personne.getstatus() == true )
            existingPersonnes.setStatus(Personne.getstatus());
        else existingPersonnes.setStatus (false);
        return repository.save(existingPersonnes);
    }
}
