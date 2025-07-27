package com.ra.ss14.controller;

import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.Movie;
import com.ra.ss14.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<APIResponse<List<Movie>>> getAllMovies() {
        return ResponseEntity.ok(new APIResponse<>("Lấy danh sách phim", true, movieService.findAll(), 200));
    }

    @PostMapping("/admin/movies")
    public ResponseEntity<APIResponse<Movie>> create(@RequestBody Movie movie) {
        return ResponseEntity.ok(new APIResponse<>("Thêm phim thành công", true, movieService.save(movie), 200));
    }

    @PutMapping("/admin/movies/{id}")
    public ResponseEntity<APIResponse<Movie>> update(@PathVariable Long id, @RequestBody Movie movie) {
        return ResponseEntity.ok(new APIResponse<>("Sửa phim thành công", true, movieService.update(id, movie), 200));
    }

    @DeleteMapping("/admin/movies/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.ok(new APIResponse<>("Xoá phim thành công", true, null, 200));
    }
}
