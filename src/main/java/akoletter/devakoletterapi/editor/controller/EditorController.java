package akoletter.devakoletterapi.editor.controller;

import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.editor.service.EditorService;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editor")
@RequiredArgsConstructor
public class EditorController {
  private final EditorService editorService;
  private final Response response;
  @PostMapping("/summary")
  public ResponseEntity<?> summary(@RequestBody SummaryRequest request, Errors errors)
      throws JSONException {
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return editorService.summary(request);
  }

}
