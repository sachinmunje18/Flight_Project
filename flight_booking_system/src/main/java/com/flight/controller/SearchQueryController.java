package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flight.model.SearchQuery;
import com.flight.service.SearchQueryService;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/search") // Base URL for all endpoints in this controller
public class SearchQueryController {

	@Autowired
	private SearchQueryService searchQueryService; // Injects the SearchQueryService

	@PostMapping("/save") // Handles HTTP POST requests to save a search query
	public ResponseEntity<SearchQuery> saveSearchQuery(@RequestBody SearchQuery searchQuery) {
		SearchQuery savedSearchQuery = searchQueryService.saveSearchQuery(searchQuery); // Saves the search query
		return ResponseEntity.ok(savedSearchQuery); // Returns the saved search query
	}

	@GetMapping("/all") // Handles HTTP GET requests to retrieve all search queries
	public ResponseEntity<List<SearchQuery>> getAllSearchQueries() {
		List<SearchQuery> searchQueries = searchQueryService.getAllSearchQueries(); // Retrieves all search queries
		return ResponseEntity.ok(searchQueries); // Returns the list of search queries
	}

	@GetMapping("/{id}") // Handles HTTP GET requests to retrieve a search query by its ID
	public ResponseEntity<SearchQuery> getSearchQueryById(@PathVariable Long id) {
		Optional<SearchQuery> searchQuery = searchQueryService.getSearchQueryById(id); // Retrieves the search query by
																						// ID
		return searchQuery.map(ResponseEntity::ok) // Returns the search query if found
				.orElseGet(() -> ResponseEntity.notFound().build()); // Returns 404 if not found
	}

	@PutMapping("/{id}") // Handles HTTP PUT requests to update a search query by its ID
	public ResponseEntity<String> updateSearchQuery(@PathVariable Long id, @RequestBody SearchQuery searchQuery) {
		SearchQuery updatedSearchQuery = searchQueryService.updateSearchQuery(id, searchQuery); // Updates the search
																								// query

		if (updatedSearchQuery != null) { // Checks if the update was successful
			return ResponseEntity.ok("Search query updated successfully!"); // Returns success message
		} else {
			return ResponseEntity.status(404).body("No matching flight details found or search query not found."); // Returns
																													// error
																													// message
		}
	}

	@DeleteMapping("/{id}") // Handles HTTP DELETE requests to delete a search query by its ID
	public ResponseEntity<Void> deleteSearchQuery(@PathVariable Long id) {
		searchQueryService.deleteSearchQuery(id); // Deletes the search query by ID
		return ResponseEntity.noContent().build(); // Returns 204 No Content response
	}
}
