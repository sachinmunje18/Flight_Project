package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flight.model.SearchQuery;
import com.flight.service.SearchQueryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    @Autowired
    private SearchQueryService searchQueryService;

    @PostMapping("/save")
    public ResponseEntity<SearchQuery> saveSearchQuery(@RequestBody SearchQuery searchQuery) {
        SearchQuery savedSearchQuery = searchQueryService.saveSearchQuery(searchQuery);
        return ResponseEntity.ok(savedSearchQuery);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SearchQuery>> getAllSearchQueries() {
        List<SearchQuery> searchQueries = searchQueryService.getAllSearchQueries();
        return ResponseEntity.ok(searchQueries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchQuery> getSearchQueryById(@PathVariable Long id) {
        Optional<SearchQuery> searchQuery = searchQueryService.getSearchQueryById(id);
        return searchQuery.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSearchQuery(@PathVariable Long id, @RequestBody SearchQuery searchQuery) {
        SearchQuery updatedSearchQuery = searchQueryService.updateSearchQuery(id, searchQuery);

        if (updatedSearchQuery != null) {
            return ResponseEntity.ok("Search query updated successfully!");
        } else {
            return ResponseEntity.status(404).body("No matching flight details found or search query not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSearchQuery(@PathVariable Long id) {
        searchQueryService.deleteSearchQuery(id);
        return ResponseEntity.noContent().build();
    }
}