package com.ra.ss2lan2.controller;

import com.ra.ss2lan2.model.entity.Showtime;
import com.ra.ss2lan2.service.MovieService;
import com.ra.ss2lan2.service.ScreenRoomService;
import com.ra.ss2lan2.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping
    public String listShowtimes(Model model) {
        model.addAttribute("showtimes", showtimeService.findAll());
        return "showtime-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("rooms", screenRoomService.findAll());
        return "showtime-add";
    }

    @PostMapping("/add")
    public String addShowtime(@ModelAttribute("showtime") Showtime showtime) {
        showtimeService.save(showtime);
        return "redirect:/showtimes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Showtime> optionalShowtime = showtimeService.findById(id);
        if (optionalShowtime.isPresent()) {
            model.addAttribute("showtime", optionalShowtime.get());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-edit";
        } else {
            return "redirect:/showtimes";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateShowtime(@PathVariable Long id, @ModelAttribute("showtime") Showtime showtime) {
        showtime.setId(id);
        showtimeService.update(showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes";
    }
}