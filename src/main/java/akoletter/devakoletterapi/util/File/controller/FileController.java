package akoletter.devakoletterapi.util.File.controller;

import akoletter.devakoletterapi.post.PostListLoad.service.PostListLoadService;
import akoletter.devakoletterapi.util.File.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fileupload")
@Tag(name = "FileUpload", description = "파일 업로드 콘트롤러")
@Validated
public class FileController {

/*
    private final FileService fileService;

    @PostMapping("/board")
    public ResponseEntity<?> createBoard(@Validated @RequestParam("files") List<MultipartFile> files
    ) throws Exception {
        boardService.addBoard(Board.builder()
                .build(), files);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/board")
    public String getBoard(@RequestParam long id) {
        Board board = boardService.findBoard(id).orElseThrow(RuntimeException::new);
        String imgPath = board.getStoredFileName();
        log.info(imgPath);
        return "<img src=" + imgPath + ">";
    }
*/

}
