package akoletter.devakoletterapi.PostListLoad.service;

import akoletter.devakoletterapi.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepositoryForQueryDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostListLoadServiceImpl implements PostListLoadService{

    private final PostMstRepository postMstRepository;
    private final PostMstRepositoryForQueryDsl postMstRepositoryForQueryDsl;


    public List<PostListLoadResponse> postload(PostListLoadRequest request){
        PostMst post = new PostMst();
        LocalDateTime now = LocalDateTime.now();
        List<PostListLoadResponse> resultList = new ArrayList<>();
        //LocalDateTime now = LocalDateTime.now();
        //        List<PostListLoadResponse> resultList = postMstRepository.findByPostId(request.getPostNm());
        //
        return resultList;
    }

}
