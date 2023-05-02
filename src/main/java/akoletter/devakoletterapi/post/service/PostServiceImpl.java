package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.post.domain.request.SavePostRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostListResponse;
import akoletter.devakoletterapi.post.domain.response.SavePostResponse;
import akoletter.devakoletterapi.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import akoletter.devakoletterapi.util.File.service.FileService;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final Response response;
    private final PostMstRepository postMstRepository;

    private final FileService fileService;


    @Override
    public ResponseEntity<GetPostDetailResponse> getPostDetail(GetPostDetailRequest request) {
        PostMst postMst = postMstRepository.findByPostId(request.getPostId()).orElse(null);
        return (ResponseEntity<GetPostDetailResponse>) response.success(postMst, "상세 게시글 불러오기 성공.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<GetPostListResponse>> getPostList(GetPostListRequest request) {
        List<PostMst> postMst = postMstRepository.findTop10By();
        return (ResponseEntity<List<GetPostListResponse>>) response.success(postMst, "게시글리스트 불러오기 성공.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SavePostResponse> savePost(SavePostRequest request, List<MultipartFile> files) throws Exception {

            PostMst postMst = new PostMst();
            postMst.setPostId(request.getPostId());
            postMst.setPostContent(request.getPostContent());
            postMst.setPostTitle(request.getPostTitle());
            postMst.setUnqUsrId(request.getUnqUsrId());
            postMst.setFrstRgstDt(LocalDateTime.now());
            List<FileMst> list = fileService.addBoard(FileMst.builder().build(), files);
            //file들을 저장하고 정보를 file.mst table에 추가.
            postMst.setFileId(list.get(0).getFileId());
            postMst.setFileId2(list.get(1).getFileId());
            postMst.setFileId3(list.get(2).getFileId());
            postMstRepository.saveAndFlush(postMst);
        return (ResponseEntity<SavePostResponse>) response.success(postMst, "게시글 저장 성공.", HttpStatus.OK);
    }

}