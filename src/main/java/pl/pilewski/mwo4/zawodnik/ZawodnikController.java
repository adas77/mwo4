package pl.pilewski.mwo4.zawodnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/zawodnik")
public class ZawodnikController {
    private final ZawodnikService zawodnikService;

    @Autowired
    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }

    @GetMapping
    public List<Zawodnik> getZawodniks() {
        return zawodnikService.getZawodnicy();
    }

    @GetMapping("/{id}")
    public Zawodnik getZawodnikById(@PathVariable Long id) {
        return zawodnikService.getZawodnikById(id);
    }

    @GetMapping("/country/{country}")
    @ResponseBody
    public List<Zawodnik> getZawodniksByCountry(@PathVariable String country) {
        System.out.println(country);

        return zawodnikService.getZawodniksByCountry(country);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteZawodnikById(@PathVariable Long id) {
        System.out.println(id);
        zawodnikService.deleteZawodnikById(id);
    }

    @PostMapping("/create")
    public void registerNewZawodnik(@RequestBody Zawodnik zawodnik) {
        zawodnikService.addNewZawodnik(zawodnik);

        // {
        // "idZawodnik":"22",
        // "idTrener":"12",
        // "name":"aaaa",
        // "surname":"bbb",
        // "country":"ccc",
        // "dob":"1995-11-11",
        // "weight":"44.0",
        // "height":"12.5"
        // }
    }

    @PutMapping("/update/{id}")
    public void updateZawodnikById(@PathVariable Long id, @RequestBody Zawodnik zawodnik) {
        zawodnikService.updateZawodnikById(id, zawodnik);
    }

}
