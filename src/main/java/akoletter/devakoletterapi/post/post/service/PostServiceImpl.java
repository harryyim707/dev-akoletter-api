package akoletter.devakoletterapi.post.post.service;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.post.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final Response response;
    private final PostMstRepository postMstRepository;


    @Override
    public ResponseEntity<GetPostDetailResponse> postdetailload(GetPostDetailRequest request) {
        PostMst postMst = postMstRepository.findByPostId(request.getPostId()).orElse(null);
        return (ResponseEntity<GetPostDetailResponse>) response.success(postMst, "상세 게시글 불러오기 성공.", HttpStatus.OK);
    }

}