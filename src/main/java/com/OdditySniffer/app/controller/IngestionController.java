package com.OdditySniffer.app.controller;


import com.OdditySniffer.app.model.LogEntry;
import com.OdditySniffer.app.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/v1/ingest")
@RequiredArgsConstructor
public class IngestionController {

    private final LogEntryRepository logEntryRepository;

    @PostMapping("/uploadLogfile")
    public ResponseEntity<String> ingestLogFile(@RequestParam("logFile") MultipartFile logFile) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(logFile.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry logEntry = parseLogLine(line);
                if (logEntry != null) {
                    logEntryRepository.save(logEntry);
                }
            }
            return ResponseEntity.ok("Log file processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to process log file: " + e.getMessage());
        }
    }

    private LogEntry parseLogLine(String line) {
        try {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) return null;
            LogEntry logEntry = new LogEntry();
            logEntry.setTimestamp(parts[0].trim() + " - " + parts[1].trim());
            logEntry.setMessage(parts[2].trim());
            return logEntry;
        } catch (Exception e) {
            // Handle parsing errors or log them
            return null;
        }
    }
}
