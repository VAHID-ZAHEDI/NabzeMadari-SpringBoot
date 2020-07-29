package com.example.api.pregnancy.controllers;

import com.example.api.pregnancy.models.Video;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoController {

    //    @PostMapping(value = "/Videos")
//    public @ResponseBody Vidoes vidoes(@ModelAttribute Vidoes vidoes) {
//        return vidoes;
//    }

    @RequestMapping(value = "/Videos", method = RequestMethod.POST)
    public @ResponseBody
    Video persistPerson(@RequestBody Video vidoes) {
        return vidoes;
    }
}
