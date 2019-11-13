package Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cvs-resources")
public class CvsController {

    @RequestMapping(value = "/enviarCVS", method = RequestMethod.POST)
    public ResponseEntity<?> sendCVS(MultipartFile multipartFile){
       return ResponseEntity.ok(null);
    }
    
}
