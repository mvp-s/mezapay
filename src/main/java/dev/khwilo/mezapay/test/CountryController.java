package dev.khwilo.mezapay.test;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class CountryController {

  private final CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public ResponseEntity<List<Country>> getAll() {
    List<Country> countries = countryService.getAll();
    return ResponseEntity.ok(countries);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Country> getOne(@PathVariable("id") Long id) {
    Optional<Country> country = countryService.getOne(id);

    return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Country> post(@RequestBody CountryDto countryDto) {
    Country country = countryService.add(countryDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(country);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Country> put(
      @PathVariable("id") Long id, @RequestBody CountryDto countryDto) {
    Optional<Country> country = countryService.put(id, countryDto);

    return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    countryService.delete(id);

    return ResponseEntity.ok("Successfully deleted country");
  }
}
