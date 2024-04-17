package wiki.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.dto.directory.TreeDto;
import wiki.service.tree.api.TreeReader;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private static final Logger LOGGER = getLogger(TreeController.class);

    private final TreeReader treeReader;

    public TreeController(TreeReader treeReader) {
        this.treeReader = treeReader;
    }

    @GetMapping("/read")
    public ResponseEntity<TreeDto> read() {
        LOGGER.info("Read tree requested");
        TreeDto response = treeReader.read();
        return ok(response);
    }
}
