package wiki.service.print;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.Page;
import wiki.service.page.api.PageStore;
import wiki.service.print.api.PdfCreator;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static java.util.function.Function.identity;

@Service
public class PdfCreatorImpl implements PdfCreator {

    public static final String TITLE_TEMPLATE = "<div style='text-align: center;'><h3> %s </h3></div>";
    
    private final PageStore pageStore;

    public PdfCreatorImpl(PageStore pageStore) {
        this.pageStore = pageStore;
    }

    @Override
    public Resource create(String htmlTitle, String htmlContent) {
        String fullContent = htmlTitle+htmlContent;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(fullContent, outputStream);
        return new ByteArrayResource(outputStream.toByteArray());
    }

    @Override
    @Transactional(readOnly = true)
    public String getTitle(UUID uuid) {
        Page page = pageStore.readPage(uuid, identity());
        return String.format(TITLE_TEMPLATE, page.getName());
    }
}
