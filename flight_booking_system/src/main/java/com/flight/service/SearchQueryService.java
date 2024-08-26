package com.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.model.SearchQuery;
import com.flight.model.FlightDetails;
import com.flight.repository.SearchQueryRepository;
import com.flight.repository.FlightDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service component, making it eligible for component scanning and dependency injection
public class SearchQueryService {

    @Autowired // Injects the SearchQueryRepository bean into this class
    private SearchQueryRepository searchQueryRepository;

    @Autowired // Injects the FlightDetailsRepository bean into this class
    private FlightDetailsRepository flightDetailsRepository;

    // Save a new search query or update an existing one
    // This method uses the repository's save method to persist the search query
    public SearchQuery saveSearchQuery(SearchQuery searchQuery) {
        return searchQueryRepository.save(searchQuery);
    }

    // Retrieve all search queries from the database
    public List<SearchQuery> getAllSearchQueries() {
        return searchQueryRepository.findAll();
    }

    // Retrieve a search query by its ID
    public Optional<SearchQuery> getSearchQueryById(Long id) {
        return searchQueryRepository.findById(id);
    }

    // Update an existing search query if matching flights are found
    // Checks if the flight details match the search query criteria and if the search query exists
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

    // Delete a search query by its ID
    public void deleteSearchQuery(Long id) {
        searchQueryRepository.deleteById(id);
    }
}
