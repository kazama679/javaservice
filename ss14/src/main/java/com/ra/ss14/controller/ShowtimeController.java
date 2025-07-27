package com.ra.ss14.controller;

import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.Showtime;
import com.ra.ss14.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    @GetMapping("/showtimes")
    public ResponseEntity<APIResponse<List<Showtime>>> getAllShowtimes() {
        return ResponseEntity.ok(new APIResponse<>("Danh sách lịch chiếu", true, showtimeService.findAll(), 200));
    }

    @PostMapping("/admin/showtimes")
    public ResponseEntity<APIResponse<Showtime>> createShowtime(@RequestBody Showtime showtime) {
        return ResponseEntity.ok(new APIResponse<>("Thêm lịch chiếu thành công", true, showtimeService.save(showtime), 200));
    }
}
