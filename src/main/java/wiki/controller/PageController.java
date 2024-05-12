package wiki.controller;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiki.dto.page.NewPageDto;
import wiki.dto.page.PageAdvancedDto;
import wiki.dto.page.PageDto;
import wiki.dto.page.UpdatePageDto;
import wiki.service.minio.api.MinioService;
import wiki.service.page.api.PageCreator;
import wiki.service.page.api.PageMapper;
import wiki.service.page.api.PageStore;
import wiki.service.page.api.PageUpdater;
import wiki.service.print.api.PdfCreator;

import javax.validation.Valid;
import java.util.UUID;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/page")
public class PageController {

    private static final Logger LOGGER = getLogger(PageController.class);

    private final MinioService minioService;
    private final PageCreator pageCreator;
    private final PageUpdater pageUpdater;
    private final PdfCreator pdfCreator;
    private final PageMapper pageMapper;
    private final PageStore pageStore;

    public PageController(MinioService minioService,
                          PageCreator pageCreator,
                          PageUpdater pageUpdater,
                          PdfCreator pdfCreator,
                          PageMapper pageMapper,
                          PageStore pageStore) {
        this.minioService = minioService;
        this.pageCreator = pageCreator;
        this.pageUpdater = pageUpdater;
        this.pdfCreator = pdfCreator;
        this.pageMapper = pageMapper;
        this.pageStore = pageStore;
    }


    @PostMapping("/create")
    public ResponseEntity<PageDto> createNewPage(
            @RequestBody
            @Valid
            NewPageDto request
    ) {
        LOGGER.info("Create PAGE requested: {}", request);
        Long id = pageCreator.create(request);
        PageDto response = pageStore.readPage(
                id,
                pageMapper::toDto
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<PageAdvancedDto> updatePage(
            @RequestBody
            @Valid
            UpdatePageDto request
    ) {
        LOGGER.info("Update PAGE requested: {}", request);
        pageUpdater.update(request);
        PageAdvancedDto response = pageStore.readPage(
                Long.parseLong(request.getId()),
                pageMapper::toAdvancedDto
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read-advanced/{uuid}")
    public ResponseEntity<PageAdvancedDto> readPageAdvanced(@PathVariable("uuid") UUID uuid) {
        LOGGER.info("Read Advanced PAGE requested: {}", uuid);
        String content = minioService.getContent(uuid);
        PageAdvancedDto response = pageStore.readPage(
                uuid,
                pageMapper::toAdvancedDto
        );
        response.setContent(content);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PageDto> readPage(@PathVariable("id") String id) {
        LOGGER.info("Read PAGE requested: {}", id);
        long pageId = Long.parseLong(id);
        PageDto response = pageStore.readPage(
                pageId,
                pageMapper::toDto
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/create-pdf/{uuid}")
    public ResponseEntity<Resource> createPdf(@PathVariable("uuid") UUID uuid){
        LOGGER.info("Create PDF requested: {}", uuid);
        String title = pdfCreator.getTitle(uuid);
        String content = minioService.getContent(uuid);
        Resource resource = pdfCreator.create(title, content);
        String fileName = uuid.toString()+".pdf";
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; " +
                        "filename=" + encode(fileName, UTF_8))
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
