package akoletter.devakoletterapi.post.PostListLoad.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;

public interface PostListLoadService {

  PostListLoadResponse postload(PostListLoadRequest request) throws Exception;

}
