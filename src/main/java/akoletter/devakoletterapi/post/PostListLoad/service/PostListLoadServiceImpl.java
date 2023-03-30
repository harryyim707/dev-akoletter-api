package akoletter.devakoletterapi.post.PostListLoad.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepositoryForQueryDsl;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostListLoadServiceImpl implements PostListLoadService{

    private final PostMstRepository postMstRepository;
    private final PostMstRepositoryForQueryDsl postMstRepositoryForQueryDsl;


    public PostListLoadResponse postload(PostListLoadRequest request) throws Exception{
       // PostMst postMst = postMstRepository.findTop10ByOrderByfrstRgstDt();
        PostMst postMst = postMstRepository.findTop10By();

        return PostListLoadResponse.builder()
                .postId(postMst.getPostId())
                .postTitle(postMst.getPostTitle())
                .unqUsrId(postMst.getUnqUsrId())
                .fileId(postMst.getFileId())
                .postContent(postMst.getPostContent())
                .build();
    }

}
