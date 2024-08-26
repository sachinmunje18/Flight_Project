package com.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.model.SearchQuery;
import com.flight.model.FlightDetails;
import com.flight.repository.SearchQueryRepository;
import com.flight.repository.FlightDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SearchQueryService {

    @Autowired
    private SearchQueryRepository searchQueryRepository;

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    public SearchQuery saveSearchQuery(SearchQuery searchQuery) {
        return searchQueryRepository.save(searchQuery);
    }

    public List<SearchQuery> getAllSearchQueries() {
        return searchQueryRepository.findAll();
    }

    public Optional<SearchQuery> getSearchQueryById(Long id) {
        return searchQueryRepository.findById(id);
    }

    public SearchQuery updateSearchQuery(Long id, SearchQuery searchQuery) {
        // Check if a matching flight exists in the flight_details table
        List<FlightDetails> matchingFlights = flightDetailsRepository.findBySourceAndDestinationAndDate(
                searchQuery.getSource(),
                searchQuery.getDestination(),
                searchQuery.getDate()
        );

        if (!matchingFlights.isEmpty() && searchQueryRepository.existsById(id)) {
            searchQuery.setId(id);
            return searchQueryRepository.save(searchQuery);
        } else {
            // No matching flight found, return null or handle it accordingly
            return null;
        }
    }

    public void deleteSearchQuery(Long id) {
        searchQueryRepository.deleteById(id);
    }
}