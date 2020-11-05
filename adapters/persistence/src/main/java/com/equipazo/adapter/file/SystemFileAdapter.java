package com.equipazo.adapter.file;

import com.equipazo.app.port.out.SaveFilePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class SystemFileAdapter implements SaveFilePort {

    @Override
    public String saveFile(InputStreamSource sourceFile, String filePath) throws IOException {
        MultipartFile file = (MultipartFile) sourceFile;
        log.debug("Saving file to: {}", filePath);
        String result = "";
        if(file != null && !file.isEmpty() && filePath != null) {
            File folder = new File(filePath);
            if(!folder.exists()) {
                folder.mkdir();
            }

            String finalPath = filePath + file.getOriginalFilename();
            File dest = new File(finalPath);

            if(dest.exists())
                dest.delete();

            file.transferTo(dest);
            result = finalPath;

            log.debug("Saved file to: {}", result);
        }

        return result;
    }
}
