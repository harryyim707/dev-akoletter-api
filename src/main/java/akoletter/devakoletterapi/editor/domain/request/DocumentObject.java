package akoletter.devakoletterapi.editor.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DocumentObject {
  private String title;
  private String content;
}
