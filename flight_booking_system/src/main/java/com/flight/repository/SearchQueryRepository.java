package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.model.SearchQuery;

import java.util.List;

@Repository // Indicates that this interface is a Spring Data repository for data access operations
public interface SearchQueryRepository extends JpaRepository<SearchQuery, Long> {

    // Finds SearchQuery records by source, destination, and date
    // This method will generate a query to search for records that match the given parameters
    List<SearchQuery> findBySourceAndDestinationAndDate(String source, String destination, String date);
}
