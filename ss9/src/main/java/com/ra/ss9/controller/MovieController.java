package com.ra.ss9.controller;

import com.ra.ss9.model.entity.Movie;
import com.ra.ss9.repository.MovieRepository;
import com.ra.ss9.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie saved = movieService.save(movie);
            logger.info("Thêm phim '{}' vào lúc {}", saved.getTitle(), LocalDateTime.now());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error("Lỗi khi thêm phim: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi thêm phim.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
        try {
            return movieRepository.findById(id).map(movie -> {
                Movie oldMovie = new Movie();
                BeanUtils.copyProperties(movie, oldMovie);

                movie.setTitle(newMovie.getTitle());
                movie.setDescription(newMovie.getDescription());
                movie.setReleaseDate(newMovie.getReleaseDate());
                movie.setPoster(newMovie.getPoster());

                movieRepository.save(movie);

                logger.info("[YELLOW] Old: {} [GREEN] Updated: {}", oldMovie, movie);
                return ResponseEntity.ok(movie);
            }).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("[RED] Error while updating movie with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật phim.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isPresent()) {
                Movie movie = optionalMovie.get();
                movieRepository.delete(movie);
                logger.info("[RED] Xóa thành công [GREEN] {}", movie);
                return ResponseEntity.ok("Xóa thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phim.");
            }
        } catch (Exception e) {
            logger.error("[RED] Error while deleting movie with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xóa phim.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        long start = System.currentTimeMillis();
        List<Movie> movies;

        if (searchMovie != null && !searchMovie.isEmpty()) {
            movies = movieRepository.findByTitleContainingIgnoreCase(searchMovie);
            logger.info("SEARCH_KEYWORD: {}", searchMovie);
        } else {
            movies = movieRepository.findAll();
        }

        long end = System.currentTimeMillis();
        logger.info("FOUND_COUNT: {} | TIME: {}ms", movies.size(), end - start);

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search-logs")
    public ResponseEntity<Map<String, Integer>> getSearchLogs() {
        Map<String, Integer> keywordCount = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get("logs/app.log"))) {
            lines.filter(line -> line.contains("SEARCH_KEYWORD: "))
                    .forEach(line -> {
                        String keyword = line.substring(line.indexOf("SEARCH_KEYWORD: ") + 16).trim();
                        keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
                    });
        } catch (IOException e) {
            logger.error("Lỗi khi đọc file log: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(keywordCount);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Movie>> getSuggestions() {
        Set<String> keywords = new HashSet<>();
        try (Stream<String> lines = Files.lines(Paths.get("logs/app.log"))) {
            lines.filter(line -> line.contains("SEARCH_KEYWORD: "))
                    .forEach(line -> {
                        String keyword = line.substring(line.indexOf("SEARCH_KEYWORD: ") + 16).trim();
                        keywords.add(keyword.toLowerCase());
                    });
        } catch (IOException e) {
            logger.error("Lỗi khi đọc file log: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
        List<Movie> suggestions = new ArrayList<>();
        for (String keyword : keywords) {
            suggestions.addAll(movieRepository.findByTitleContainingIgnoreCase(keyword));
        }
        return ResponseEntity.ok(suggestions);
    }
}