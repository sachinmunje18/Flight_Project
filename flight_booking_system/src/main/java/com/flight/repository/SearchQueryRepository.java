package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.model.SearchQuery;

import java.util.List;

@Repository
public interface SearchQueryRepository extends JpaRepository<SearchQuery, Long> {

    // Find by source, destination, and date (example)
    List<SearchQuery> findBySourceAndDestinationAndDate(String source, String destination, String date);
}