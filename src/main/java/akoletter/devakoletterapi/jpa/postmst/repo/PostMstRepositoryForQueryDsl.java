package akoletter.devakoletterapi.jpa.postmst.repo;

/*import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;*/
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
