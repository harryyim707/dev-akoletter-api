package akoletter.devakoletterapi.util.File.controller;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.post.PostListLoad.service.PostListLoadService;
import akoletter.devakoletterapi.util.File.domain.request.FileUploadRequest;
import akoletter.devakoletterapi.util.File.domain.request.GetFileRequest;
import akoletter.devakoletterapi.util.File.service.FileService;
import akoletter.devakoletterapi.util.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Tag(name = "FileUpload", description = "파일 업로드 콘트롤러")
@Validated
@Slf4j
public class FileController {


    private final FileService fileService;
    private final Response response;


    @PostMapping("/board")//post는 저장하기
    public ResponseEntity<?> createBoard(
            @Validated @RequestParam(value ="files", required=false) List<MultipartFile> files
    ) throws Exception {
        fileService.addBoard(FileMst.builder().build(), files);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/board")//get은 불러오기
    public String getBoard(GetFileRequest request) {
        FileMst board = fileService.findBoard(request.getFileId()).orElseThrow(RuntimeException::new);
        String imgPath = board.getOrgFileNm();
        log.info(imgPath);
        return "<img src=" + imgPath + ">"; //따로 response body없이..?
    }


}
