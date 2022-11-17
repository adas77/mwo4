package pl.pilewski.mwo4.zawodnik;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZawodnikService {

    private final IZawodnikRepository iZawodnikRepository;

    @Autowired
    public ZawodnikService(IZawodnikRepository iZawodnikRepository) {
        this.iZawodnikRepository = iZawodnikRepository;
    }

    public List<Zawodnik> getZawodnicy() {
        return iZawodnikRepository.findAll();
    }

    public Zawodnik getZawodnikById(Long id) {
        var zOptional = iZawodnikRepository.findById(id);
        return zOptional.isPresent() ? zOptional.get() : null;
    }

    public void addNewZawodnik(Zawodnik zawodnik) {
        var id = zawodnik.getIdZawodnik();
        Optional<Zawodnik> zOptional = iZawodnikRepository.findById(id);
        if (zOptional.isPresent()) {
            throw new IllegalStateException(String.format("Zawodnik with id=%d already exists", id));
        }
        iZawodnikRepository.save(zawodnik);
    }

    public void deleteZawodnikById(Long id) {
        if (!iZawodnikRepository.findById(id).isPresent()) {
            throw new IllegalStateException(String.format("Zawodnik with id=%d does not exist", id));
        }
        iZawodnikRepository.deleteById(id);
    }

    public List<Zawodnik> getZawodniksByCountry(String country) {
        var optionalZawodnicyByCountry = iZawodnikRepository.findZawodnicyByCountry(country);
        var zawodnicy = optionalZawodnicyByCountry.get();
        return zawodnicy.isEmpty() ? null : zawodnicy;
    }

    public void updateZawodnikById(Long id, Zawodnik zawodnik) {
        var zOptional = iZawodnikRepository.findById(id);
        if (!zOptional.isPresent()) {
            throw new IllegalStateException(String.format("Zawodnik with id=%d does not exist", id));
        }
        var zawodnikToUpdate = zOptional.get();
        zawodnikToUpdate.setIdTrener(zawodnik.getIdTrener());
        zawodnikToUpdate.setName(zawodnik.getName());
        zawodnikToUpdate.setSurname(zawodnik.getSurname());
        zawodnikToUpdate.setCountry(zawodnik.getCountry());
        zawodnikToUpdate.setDob(zawodnik.getDob());
        zawodnikToUpdate.setHeight(zawodnik.getHeight());
        zawodnikToUpdate.setWeight(zawodnik.getWeight());
        iZawodnikRepository.save(zawodnik);
    }

}
