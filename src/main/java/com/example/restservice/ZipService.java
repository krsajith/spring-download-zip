package com.example.restservice;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipService {


    public void generateZipFile(OutputStream outputStream, List<FileInfo> srcFiles) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(outputStream);
        for (FileInfo fileInfo : srcFiles) {
            try(InputStream fis =  new BufferedInputStream(new URL(fileInfo.getUrl()).openStream())) {
                ZipEntry zipEntry = new ZipEntry(fileInfo.getFileName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
        }
        zipOut.close();
        outputStream.close();
    }
}
