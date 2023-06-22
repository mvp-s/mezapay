package dev.khwilo.mezapay.test;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService {

  private final CountryRepository countryRepository;

  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  public Optional<Country> getOne(Long id) {

    return countryRepository.findById(id);
  }

  @Transactional
  public Country add(CountryDto countryDto) {
    Country country = Country.builder().name(countryDto.name()).build();
    return countryRepository.save(country);
  }

  @Transactional
  public Optional<Country> put(Long id, CountryDto countryDto) {
    Optional<Country> country = countryRepository.findById(id);

    if (country.isPresent()) {
      country.get().setName(countryDto.name());
      return country;
    }

    return Optional.empty();
  }

  @Transactional
  public void delete(Long id) {
    boolean countryPresent = countryRepository.existsById(id);

    if (countryPresent) {
      countryRepository.deleteById(id);
    }
  }
}
