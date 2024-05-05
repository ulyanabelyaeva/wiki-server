package wiki.service.minio.api;

import java.util.UUID;

public interface MinioService {

    void createFile(UUID uuid);

    void updateFile (UUID uuid, String content);

    String getContent(UUID uuid);
}
