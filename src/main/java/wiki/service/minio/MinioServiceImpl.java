package wiki.service.minio;

import io.minio.*;
import io.minio.errors.MinioException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import wiki.config.MinioProperties;
import wiki.service.minio.api.MinioService;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class MinioServiceImpl implements MinioService {

    private static final Logger LOGGER = getLogger(MinioServiceImpl.class);
    private final wiki.config.MinioProperties minioProperties;
    @Qualifier("minClient")
    private final MinioClient minioClient;

    public MinioServiceImpl(MinioClient minioClient,
                            MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
        createBucket(minioClient, minioProperties.getBucket());
    }

    @Override
    public void createFile(UUID uuid) {
        String content = minioProperties.getInitialContent();
        this.put(uuid, content);
    }

    @Override
    public void updateFile(UUID uuid, String content) {
        this.put(uuid, content);
    }

    @Override
    public String getContent(UUID uuid) {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(this.getPath(uuid))
                            .build());
            return this.readToString(stream);
        } catch (Exception e) {
            throw new IllegalStateException("Error when get file by: " + this.getPath(uuid), e);
        }
    }

    private void put(UUID uuid, String content) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(this.getPath(uuid))
                            .stream(inputStream, -1, minioProperties.getPartSize())
                            .contentType("text/html")
                            .build());

            LOGGER.info("File is successfully uploaded to bucket");
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("Error when saving file to bucket");
        }
    }

    private void createBucket(MinioClient minioClient, String bucketName) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                LOGGER.info("Bucket already exists.");
            }
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("Unable to create bucket on the internal storage. Please retry later", e);
        }
    }

    private String getPath(UUID uuid) {
        return "/" + uuid;
    }

    private String readToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}