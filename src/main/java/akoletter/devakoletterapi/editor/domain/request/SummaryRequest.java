package akoletter.devakoletterapi.editor.domain.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SummaryRequest {
  private String title;
  private String content;
  private String category;
  private List<String> references;
}
