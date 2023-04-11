package akoletter.devakoletterapi.post.PostListLoad.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepositoryForQueryDsl;
import akoletter.devakoletterapi.util.jwt.JwtProvider;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import akoletter.devakoletterapi.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostListLoadServiceImpl implements PostListLoadService{
    private final Response response;
    private final PostMstRepository postMstRepository;

    private final PostMstRepositoryForQueryDsl postMstRepositoryForQueryDsl;

    @Override
    public ResponseEntity<List<PostListLoadResponse>> postload(PostListLoadRequest request) {
            List<PostMst> postMst = postMstRepository.findTop10By();
        return (ResponseEntity<List<PostListLoadResponse>>) response.success(postMst, "게시글을 불러오기 성공.", HttpStatus.OK);
    }

}
