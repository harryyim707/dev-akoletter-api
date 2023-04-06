package akoletter.devakoletterapi.editor.service;

import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface EditorService {

  ResponseEntity<?> summary(SummaryRequest request) throws JSONException;
}
