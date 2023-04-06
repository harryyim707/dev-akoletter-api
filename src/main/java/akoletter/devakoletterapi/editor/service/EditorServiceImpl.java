package akoletter.devakoletterapi.editor.service;

import akoletter.devakoletterapi.editor.domain.request.DocumentObject;
import akoletter.devakoletterapi.editor.domain.request.OptionObject;
import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.editor.domain.response.SummaryResponse;
import akoletter.devakoletterapi.util.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URI;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EditorServiceImpl implements EditorService{
  @Value("${clientId}")
  private String clientID;

  @Value("${clientSecret}")
  private String clientSecret;
  @Value("${apiUrl}")
  private URI url;

  private final Response response;

  @Override
  public ResponseEntity<?> summary(SummaryRequest request) throws JSONException {
    DocumentObject documentObject = new DocumentObject();
    documentObject.setTitle(request.getTitle());
    documentObject.setContent(request.getContent());
    OptionObject optionObject = new OptionObject();
    optionObject.setLanguage("ko");
    optionObject.setModel("news");
    optionObject.setTone(2);
    optionObject.setSummaryCount(3);
    JSONObject jsonObjectDoc = new JSONObject();
    jsonObjectDoc.put("title", documentObject.getTitle());
    jsonObjectDoc.put("content", documentObject.getContent());
    JSONObject jsonObjectOpt = new JSONObject();
    jsonObjectOpt.put("language", optionObject.getLanguage());
    jsonObjectOpt.put("model", optionObject.getModel());
    jsonObjectOpt.put("tone", optionObject.getTone());
    jsonObjectOpt.put("summaryCount", optionObject.getSummaryCount());
    JSONObject jsonObject = new JSONObject();
    jsonObject.putOpt("document", jsonObjectDoc);
    jsonObject.putOpt("option", jsonObjectOpt);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-NCP-APIGW-API-KEY-ID", clientID);
    httpHeaders.add("X-NCP-APIGW-API-KEY", clientSecret);
    httpHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
    HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
    SummaryResponse summaryResponse = new SummaryResponse();
    String summary = restTemplate.postForObject(url, requestEntity, String.class);
    summaryResponse.setSummary(summary);
    summaryResponse.setReferences(request.getReferences());
    summaryResponse.setOriginal(documentObject);

    return response.success(summaryResponse);
  }
}
