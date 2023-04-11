package akoletter.devakoletterapi.editor.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OptionObject {
  private String language;
  private String model;
  private int tone;
  private int summaryCount;
}
