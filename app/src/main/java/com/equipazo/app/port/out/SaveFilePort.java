package com.equipazo.app.port.out;

import org.springframework.core.io.InputStreamSource;

import java.io.IOException;

public interface SaveFilePort {
    String saveFile(InputStreamSource file, String filePath) throws IOException;
}
