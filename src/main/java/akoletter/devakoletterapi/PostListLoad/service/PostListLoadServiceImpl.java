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

        /*List<PostListLoadResponse> resultList = postMstRepositoryForQueryDsl.

        PostMst post = new PostMst();
        LocalDateTime now = LocalDateTime.now();

        if(memberMst == null){
            member.setUnqUsrId(passwordEncoder.encode(request.getUsrId()));
            member.setUsrId(request.getUsrId());
            member.setUsrPwd(passwordEncoder.encode(request.getUsrPwd()));
            member.setUsrNm(request.getUsrNm());
            member.setUsrEmail(request.getUsrEmail());
            member.setUsrTelNo(request.getUsrTelNo());
            memberMstRepository.save(member);
            result.setSuccess(true);
        }
        else result.setSuccess(false);
        return result;*/
        List<PostListLoadResponse> resultList = new ArrayList<>();
        return resultList;
    }

}
