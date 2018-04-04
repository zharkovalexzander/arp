package springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springapp.entities.CityEntity;
import springapp.service.CityService;

@Controller
@Configurable
public class MainController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/visit", method = RequestMethod.GET)
    public @ResponseBody
    CityEntity visit(@RequestParam("name") String name) {
        return this.cityService.getCityByName(name);
    }

}
