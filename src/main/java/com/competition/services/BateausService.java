package com.competition.services;
import com.competition.entities.Bateaus;
import com.competition.repositories.BateausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BateausService {
    @Autowired
    private BateausRepository repository;

    public Bateaus saveBateau(Bateaus Bateau) {
        return repository.save(Bateau);
    }

    public List<Bateaus> saveBateaus(List<Bateaus> Bateaus) {
        return repository.saveAll(Bateaus);
    }

    public List<Bateaus> getBateaus() {
        return repository.findAll();
    }

    public Bateaus getBateauById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Bateaus getBateauByName(String name) {
        return repository.findByNom(name);
    }

    public boolean deleteBateau(int id) {
        repository.deleteById(id);
        return true ;
    }

    public Bateaus updateBateau(Bateaus Bateau) {
        Bateaus existingBateaus = repository.findById(Bateau.getId()).orElse(null);

        if ( Bateau.getNom() != null  && Bateau.getNom() != "")
            existingBateaus.setNom(Bateau.getNom());
        if (  Bateau.getstatus() == true)
            existingBateaus.setStatus(Bateau.getstatus());
        else  existingBateaus.setStatus (false);
        if ( Bateau.getType() != null  && Bateau.getType() != "")
            existingBateaus.setType(Bateau.getType());
        return repository.save(existingBateaus);
    }
}
