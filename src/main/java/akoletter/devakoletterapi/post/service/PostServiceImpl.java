package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.repo.FileMstRepository;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.post.domain.request.DeletePostRequest;
import akoletter.devakoletterapi.post.domain.response.GetImageResponse;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.post.domain.response.PostListDomain;
import akoletter.devakoletterapi.util.response.Response;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

  private final Response response;
  private final PostMstRepository postMstRepository;
  private final FileMstRepository fileMstRepository;
  private final MemberMstRepository memberMstRepository;
  @Value("${img.defaultid}")
  int defaultImageId;
  @Autowired
  BlobContainerClient blobContainerClient;


  @Override
  public ResponseEntity<?> getPostDetail(long postId) {
    PostMst postMst = postMstRepository.findByPostId(postId).orElse(null);

    if(postMst.getUseYn().equals("Y")){
    GetPostDetailResponse postDetailResponse = new GetPostDetailResponse();
    postDetailResponse.setPostId(postMst.getPostId());
    postDetailResponse.setPostTitle(postMst.getPostTitle());
    postDetailResponse.setPostContent(postMst.getPostContent());
    postDetailResponse.setCategory(postMst.getCategory());
    postDetailResponse.setUrlList(postMst.getUrlList());
    String date = postMst.getFrstRgstDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    postDetailResponse.setDate(date);
    MemberMst memberMst = memberMstRepository.findByUnqUsrId(postMst.getUnqUsrId()).orElse(null);
    assert memberMst != null;
    postDetailResponse.setUsrId(memberMst.getUsrId());
    postDetailResponse.setFileId(postMst.getFileId());
    return response.success(postDetailResponse, "상세 게시글 불러오기 성공.",
        HttpStatus.OK);
    }
    else{
      return response.fail("삭제된 게시글입니다.",HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> getPostList(int size, String category) {
    Pageable pageable;
    pageable = PageRequest.of(0, size, Sort.by("postId").descending());
    Slice<PostMst> res = null;
    if (!"all".equals(category)) {
      res = postMstRepository.findByCategory(category, pageable);
    } else {
      res = postMstRepository.findBy(pageable);
    }
    List<PostListDomain> postList = new ArrayList<>();
    for(PostMst o: res.getContent()){
      PostListDomain domain = new PostListDomain();
      if(o.getUseYn().equals("Y")){
      domain.setPostId(o.getPostId());
      domain.setPostTitle(o.getPostTitle());
      MemberMst memberMst = memberMstRepository.findByUnqUsrId(o.getUnqUsrId()).orElse(null);
      assert memberMst != null;
      String usrId = memberMst.getUsrId();
      domain.setUsrId(usrId);
      domain.setFileId(o.getFileId());
      String date = o.getFrstRgstDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      domain.setCategory(o.getCategory());
      domain.setFrstRgsDt(date);
      postList.add(domain);}
    }
    return response.success(postList, "게시글리스트 불러오기 성공.",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> showImage(int fileId) throws IOException {
    FileMst defaultImage = fileMstRepository.findByfileId(defaultImageId).orElse(null);
    FileMst fileMst = fileMstRepository.findByfileId(fileId).orElse(defaultImage);
    String name = defaultImage.getFileNm();
    if(fileMst.getFileNm() != null){
      name = fileMst.getFileNm();
    }
    BlobClient blob = blobContainerClient.getBlobClient(name);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    blob.downloadStream(outputStream);
    byte[] image = outputStream.toByteArray();
    GetImageResponse resp = new GetImageResponse();
    resp.setImage(image);
    outputStream.close();
    return response.success(resp, "파일 불러오기 성공", HttpStatus.OK);
  }

  @Override
  public  ResponseEntity<?> deletePost(DeletePostRequest request){
    PostMst postMst = postMstRepository.findByPostId(request.getPostId()).orElse(null);
    postMst.setUseYn("N");
    int fileno = postMst.getFileId();
    FileMst fileMst = fileMstRepository.findByfileIdOrderByFileIdDesc(fileno);
    FileMst fileMst2 = fileMstRepository.findByfileIdOrderByFileIdDesc(fileno+1);
    FileMst fileMst3 = fileMstRepository.findByfileIdOrderByFileIdDesc(fileno+2);
    fileMst.setUseYn("N");
    fileMst2.setUseYn("N");
    fileMst3.setUseYn("N");
    postMstRepository.save(postMst);
    fileMstRepository.save(fileMst);
    fileMstRepository.save(fileMst2);
    fileMstRepository.save(fileMst3);
    return response.success("게시글 삭제가 완료되었습니다.");
  }
}