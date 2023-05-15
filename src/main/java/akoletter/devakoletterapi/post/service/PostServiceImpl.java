package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import akoletter.devakoletterapi.post.domain.request.SavePostRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.post.domain.response.GetPostListResponse;
import akoletter.devakoletterapi.post.domain.response.SavePostResponse;
import akoletter.devakoletterapi.util.File.service.FileService;
import akoletter.devakoletterapi.util.response.Response;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

  private final Response response;
  private final PostMstRepository postMstRepository;
  private final MemberMstRepository memberMstRepository;

  private final FileService fileService;


  @Override
  public ResponseEntity<GetPostDetailResponse> getPostDetail(GetPostDetailRequest request) {
    PostMst postMst = postMstRepository.findByPostId(request.getPostId()).orElse(null);
    GetPostDetailResponse postDetailResponse = new GetPostDetailResponse();
    postDetailResponse.setPostId(postMst.getPostId());
    postDetailResponse.setPostTitle(postMst.getPostTitle());
    postDetailResponse.setPostContent(postMst.getPostContent());
    List<Integer> fileIds = new ArrayList<>();
    fileIds.add(postMst.getFileId());
    fileIds.add(postMst.getFileId2());
    fileIds.add(postMst.getFileId3());
    postDetailResponse.setFileId(fileIds);
    return (ResponseEntity<GetPostDetailResponse>) response.success(postDetailResponse, "상세 게시글 불러오기 성공.",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> getPostList(GetPostListRequest request) {
    List<PostMst> result = new ArrayList<>();
    String category = request.getCategory();
    if (!"all".equals(category)) {
      result = postMstRepository.findTop10ByCategory(category);
    } else {
      result = postMstRepository.findTop10By();
    }

    return response.success(result, "게시글리스트 불러오기 성공.",
        HttpStatus.OK);
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
    if(postMstRepository.existsByPostTitle(title)){
      savePostResponse.setSuccess("exists");
      return savePostResponse;
    }
    postMst.setPostContent(request.getPostContent());
    postMst.setPostTitle(request.getPostTitle());
    postMst.setUnqUsrId(unqUsrId);
    postMst.setCategory(request.getCategory());
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

}