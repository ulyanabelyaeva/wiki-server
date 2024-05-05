package wiki.service.minio.api;

import java.util.UUID;

public interface MinioService {

    void createFile(UUID uuid);

    String getContent(UUID uuid);
}
