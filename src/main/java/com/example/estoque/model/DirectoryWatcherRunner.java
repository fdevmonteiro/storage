package com.example.estoque.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class DirectoryWatcherRunner {

    @Autowired
    private DirectoryWatcher directoryWatcher;

    @PostConstruct
    public void startWatching() {
            new Thread(() -> directoryWatcher.watchDirectoryPath(Paths.get("C:/Users/kauad/Documents/arquivosImport"))).start();
    }
}
