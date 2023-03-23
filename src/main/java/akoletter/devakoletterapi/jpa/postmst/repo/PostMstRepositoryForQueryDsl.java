package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
/*import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;*/
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository

public class PostMstRepositoryForQueryDsl {


/*    private final JPAQueryFactory queryFactory;
    private final PostMst postMst;*/

/*    public List<PostListLoadResponse> findAllList(String postId) {
        Q
        List<PostListLoadResponse> result = queryFactory.select(
                        Projections.fields(
                                PostListLoadResponse.class,
                               postMst.getPostId()
                        )
                )
                .from(postMst)
                .where(qEfsUserMst.unqUserId.eq(unqUserId))
                .fetch();
        return result;
    }*/

}
