package akoletter.devakoletterapi.editor.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ClovaRequest {
  private DocumentObject documentObject;
  private OptionObject optionObject;

}
