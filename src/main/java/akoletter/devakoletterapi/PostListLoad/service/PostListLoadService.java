package akoletter.devakoletterapi.PostListLoad.service;

import akoletter.devakoletterapi.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.PostListLoad.domain.response.PostListLoadResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostListLoadService {

    List<PostListLoadResponse> postload(@Valid @RequestBody PostListLoadRequest request);
}
