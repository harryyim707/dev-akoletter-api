package akoletter.devakoletterapi.editor.service;

import akoletter.devakoletterapi.editor.domain.request.DocumentObject;
import akoletter.devakoletterapi.editor.domain.request.OptionObject;
import akoletter.devakoletterapi.editor.domain.request.SavePostRequest;
import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.editor.domain.response.ClovaResponse;
import akoletter.devakoletterapi.editor.domain.response.SavePostResponse;
import akoletter.devakoletterapi.editor.domain.response.SummaryResponse;
import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.util.File.service.FileService;
import akoletter.devakoletterapi.util.response.Response;
import jakarta.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EditorServiceImpl implements EditorService {

  @Value("${clientId}")
  private String clientID;

  @Value("${clientSecret}")
  private String clientSecret;
  @Value("${apiUrl}")
  private URI url;

  private final Response response;
  private final MemberMstRepository memberMstRepository;
  private final PostMstRepository postMstRepository;
  private final FileService fileService;

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
    httpHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
    HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
    SummaryResponse summaryResponse = new SummaryResponse();
    ClovaResponse summary = restTemplate.postForObject(url, requestEntity, ClovaResponse.class);
    List<String> splitSummary = List.of(summary.getSummary().split("\n"));
    summaryResponse.setSummary(splitSummary);
    summaryResponse.setReferences(request.getReferences());
    summaryResponse.setOriginal(documentObject);

    return response.success(summaryResponse);
  }

  @Transactional
  @Override
  public SavePostResponse savePost(SavePostRequest request,
      List<MultipartFile> files) throws Exception {
    SavePostResponse savePostResponse = new SavePostResponse();
    PostMst postMst = new PostMst();
    String title = request.getPostTitle();
    MemberMst memberMst = memberMstRepository.findByUsrId(request.getUsrId()).orElse(null);
    Long unqUsrId = memberMst.getUnqUsrId();
    if (postMstRepository.existsByPostTitle(title)) {
      savePostResponse.setSuccess("exists");
      return savePostResponse;
    }
    PostMst last = postMstRepository.findTopByOrderByPostIdDesc().orElse(null);
    Long postId = 1L;
    if (last != null) {
      postId = last.getPostId() + 1;
    }
    postMst.setPostId(postId);
    postMst.setPostContent(request.getPostContent());
    postMst.setPostTitle(request.getPostTitle());
    postMst.setUnqUsrId(unqUsrId);
    postMst.setFrstRgstId(memberMst.getUsrId());
    postMst.setLastMdfyId(memberMst.getUsrId());
    postMst.setCategory(request.getCategory());
    List<FileMst> list = fileService.saveFile(FileMst.builder().build(), files);
    //file들을 저장하고 정보를 file.mst table에 추가.
    postMst.setFileId(list.get(0).getFileId());
    postMst.setFileId2(list.get(1).getFileId());
    postMst.setFileId3(list.get(2).getFileId());
    postMstRepository.saveAndFlush(postMst);
    savePostResponse.setSuccess("success");
    return savePostResponse;
  }

  @Transactional
  @Override
  public ResponseEntity<?> saveImage(List<MultipartFile> files) throws Exception {
    List<FileMst> list = fileService.saveFile(FileMst.builder().build(), files);
    return response.success(HttpStatus.OK);
  }
}
