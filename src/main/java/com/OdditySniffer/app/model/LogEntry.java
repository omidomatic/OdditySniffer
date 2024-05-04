package com.OdditySniffer.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    @Id
    private String id;
    private String timestamp;
    private String message;


    // Constructors, getters, and setters
}
