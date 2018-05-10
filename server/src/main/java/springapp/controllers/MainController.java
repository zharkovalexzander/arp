package springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springapp.entities.CityEntity;
import springapp.entities.Pair;
import springapp.service.CityService;
import springapp.service.PointService;

@Controller
@Configurable
public class MainController {
    @Autowired
    private CityService cityService;

    @Autowired
    private PointService pointService;

    @RequestMapping(value = "/visit", method = RequestMethod.GET)
    public @ResponseBody
    CityEntity visit(@RequestParam("name") String name) {
        return this.cityService.getCityByName(name);
    }

    @RequestMapping(value = "/get/{id}/gps", method = RequestMethod.GET)
    public @ResponseBody
    Pair<Double, Double> getCoord(@PathVariable("id") String id) {
        return pointService.poll(id, "gps");
    }

    @RequestMapping(value = "/pass/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Pair<String, Long> getPassengers(@PathVariable("id") String id) {
        return pointService.poll(id, "ls");
    }

}
