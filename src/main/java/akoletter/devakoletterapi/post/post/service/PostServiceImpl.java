package akoletter.devakoletterapi.post.postdetail.service;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepositoryForQueryDsl;
import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.post.PostListLoad.service.PostListLoadService;
import akoletter.devakoletterapi.post.postdetail.domain.request.PostDetailLoadRequest;
import akoletter.devakoletterapi.post.postdetail.domain.response.PostDetailLoadResponse;
import akoletter.devakoletterapi.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostDetailLoadServiceImpl implements PostDetailLoadService{
    private final Response response;
    private final PostMstRepository postMstRepository;


    @Override
    public ResponseEntity<PostDetailLoadResponse> postdetailload(PostDetailLoadRequest request) {
        PostMst postMst = postMstRepository.findByPostId(request.getPostId()).orElse(null);
        return (ResponseEntity<PostDetailLoadResponse>) response.success(postMst, "상세 게시글 불러오기 성공.", HttpStatus.OK);
    }

}