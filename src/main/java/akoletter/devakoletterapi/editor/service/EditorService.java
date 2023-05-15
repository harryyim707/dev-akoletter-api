package akoletter.devakoletterapi.editor.service;

import akoletter.devakoletterapi.editor.domain.request.SavePostRequest;
import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.editor.domain.response.SavePostResponse;
import java.util.List;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EditorService {

  ResponseEntity<?> summary(SummaryRequest request) throws JSONException;

  SavePostResponse savePost(SavePostRequest request, List<MultipartFile> files) throws Exception;
}
