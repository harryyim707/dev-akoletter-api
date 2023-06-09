package akoletter.devakoletterapi.util.File.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfig {
  @Value("${storage.connstr}")
  private String constr;
  @Bean
  public BlobContainerClient blobContainerClient(){
    return new BlobContainerClientBuilder()
        .connectionString(constr)
        .containerName("akoletterimages")
        .buildClient();
  }
}
