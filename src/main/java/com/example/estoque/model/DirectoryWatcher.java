package com.example.estoque.model;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectoryWatcher {

    @Autowired
    private XMLParser xmlParser;

    public void watchDirectoryPath(Path path) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, ENTRY_CREATE);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_CREATE) {
                        // Novo arquivo criado
                        Path newPath = path.resolve((Path) event.context());
                        System.out.println("Novo arquivo detectado: " + newPath);

                        // Processa o arquivo XML
                        xmlParser.parseAndSaveProdutos(newPath.toString());
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
