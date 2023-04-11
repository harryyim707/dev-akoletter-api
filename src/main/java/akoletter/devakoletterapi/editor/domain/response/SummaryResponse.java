package akoletter.devakoletterapi.editor.domain.response;

import akoletter.devakoletterapi.editor.domain.request.DocumentObject;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SummaryResponse {
  private String summary;
  private DocumentObject original;
  private List<String> references;
}
