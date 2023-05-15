package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.repo.FileMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.post.domain.response.GetImageResponse;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.util.response.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

  private final Response response;
  private final PostMstRepository postMstRepository;
  private final FileMstRepository fileMstRepository;


  @Override
  public ResponseEntity<?> getPostDetail(long postId) {
    PostMst postMst = postMstRepository.findByPostId(postId).orElse(null);
    GetPostDetailResponse postDetailResponse = new GetPostDetailResponse();
    postDetailResponse.setPostId(postMst.getPostId());
    postDetailResponse.setPostTitle(postMst.getPostTitle());
    postDetailResponse.setPostContent(postMst.getPostContent());
    List<Integer> fileIds = new ArrayList<>();
    fileIds.add(postMst.getFileId());
    fileIds.add(postMst.getFileId2());
    fileIds.add(postMst.getFileId3());
    postDetailResponse.setFileId(fileIds);
    return response.success(postDetailResponse, "상세 게시글 불러오기 성공.",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> getPostList(String category) {
    List<PostMst> result = new ArrayList<>();
    if (!"all".equals(category)) {
      result = postMstRepository.findTop10ByCategory(category);
    } else {
      result = postMstRepository.findTop10By();
    }

    return response.success(result, "게시글리스트 불러오기 성공.",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> showImage(int fileId) throws IOException {
    String absolutePath = new File("").getAbsolutePath();
    FileMst fileMst = fileMstRepository.findByfileId(fileId).orElse(null);
    String path = fileMst.getFilePath();
    String name = fileMst.getFileNm();
    InputStream imageStream = new FileInputStream(absolutePath + "/" + path + "/" + name);
    byte[] image = IOUtils.toByteArray(imageStream);
    GetImageResponse resp = new GetImageResponse();
    resp.setImage(image);
    imageStream.close();
    return response.success(resp, "파일 불러오기 성공", HttpStatus.OK);
  }

}