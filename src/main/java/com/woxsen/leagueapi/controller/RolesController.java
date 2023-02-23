package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.service.RoleService;
import com.woxsen.leagueapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(AppConstants.BASE_URL + "/roles")
public class RolesController {


    @Autowired
    private RoleService rolesService;

    @PostMapping()
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> addRoles() {
        List<Role> roles = rolesService.addRoles();
        return new ResponseEntity<>(roles, HttpStatus.CREATED);
    }

//    @GetMapping(value = "/msworddocument", produces = { "application/octet-stream" })
//    public ResponseEntity<ByteArrayResource> download() {
//
//        try {
//
//            File file = ResourceUtils.getFile("D:\\POC\\exampleDocument.docx");
//
//            byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
//            ByteArrayResource resource = new ByteArrayResource(contents);
//
//           return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .contentLength(resource.contentLength())
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            ContentDisposition.attachment()
//                                    .filename("whatever.docx")
//                                    .build().toString())
//                    .body(resource);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    @GetMapping("/document")
//    public ResponseEntity<byte[]> getDocument() throws IOException {
//        InputStream in = getClass()
//                .getResourceAsStream("D:/POC/eclipse-ide-keybindings.pdf");
//        byte[] pdfBytes = IOUtils.toByteArray(in);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.add("Content-Disposition", "inline; filename=example.pdf");
//        headers.setContentLength(pdfBytes.length);
//
//        return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
//    }
//    @GetMapping(value = "/document", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> getPdf() throws IOException {
//        InputStream is = new FileInputStream("src/main/resources/eclipse-ide-keybindings.pdf");
//        byte[] pdfContents = IOUtils.toByteArray(is);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDisposition(ContentDisposition.builder("inline").filename("file.pdf").build());
//        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
//    }
//    @GetMapping(
//            value = "/get-file",
//            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
//    )
//    public byte[] getFile() throws IOException {
//        File file = new File("D:/POC/eclipse-ide-keybindings.pdf");
//        byte[] data = Files.readAllBytes(file.toPath());
//        return data;
//    }

}
