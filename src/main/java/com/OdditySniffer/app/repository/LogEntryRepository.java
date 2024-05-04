package com.OdditySniffer.app.repository;

import com.OdditySniffer.app.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
}
