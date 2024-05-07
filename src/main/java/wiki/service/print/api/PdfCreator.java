package wiki.service.print.api;

import org.springframework.core.io.Resource;

import java.util.UUID;

public interface PdfCreator {

    Resource create(String htmlTitle, String htmlContent);

    String getTitle(UUID uuid);
}
