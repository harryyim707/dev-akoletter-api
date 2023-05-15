package akoletter.devakoletterapi.editor.controller;

import akoletter.devakoletterapi.editor.domain.request.SavePostRequest;
import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.editor.domain.response.SavePostResponse;
import akoletter.devakoletterapi.editor.service.EditorService;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/editor")
@RequiredArgsConstructor
public class EditorController {

  private final EditorService editorService;
  private final Response response;
  private final MemberMstRepository memberMstRepository;
  private final PostMstRepository postMstRepository;

  @PostMapping("/summary")
  public ResponseEntity<?> summary(@RequestBody SummaryRequest request, Errors errors)
      throws JSONException {
    if (errors.hasErrors()) {
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return editorService.summary(request);
  }

  @PostMapping("/savepost")
  public ResponseEntity<?> savePost(@RequestPart(value = "request") SavePostRequest request,
      @RequestPart(value = "files", required = false) List<MultipartFile> files, Errors errors)
      throws Exception {
    if (errors.hasErrors()) {
      return response.invalidFields(Helper.refineErrors(errors));
    }
    SavePostResponse result = editorService.savePost(request, files);
    if ("exists".equals(result.getSuccess())) {
      return response.fail("이미 존재하는 제목입니다.", HttpStatus.BAD_REQUEST);
    }
    MemberMst member = memberMstRepository.findByUsrId(request.getUsrId()).orElse(null);
    PostMst res = postMstRepository.findByPostTitleAndUnqUsrId(request.getPostTitle(),
        member.getUnqUsrId()).orElse(null);
    return response.success(res, "저장에 성공했습니다.", HttpStatus.OK);
  }

}
