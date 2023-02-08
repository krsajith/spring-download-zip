package com.example.restservice;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class DownloadController {

	private final ZipService zipService;

	public DownloadController(ZipService zipService) {
		this.zipService = zipService;
	}

	@PostMapping("/download-zip")
	public void greeting(HttpServletResponse response, @RequestBody List<FileInfo> files) throws IOException {

		response.setContentType("application/zip");
		response.addHeader("Content-Disposition", "files.zip");

		zipService.generateZipFile(response.getOutputStream(),files);
		response.getOutputStream().flush();
	}
}
