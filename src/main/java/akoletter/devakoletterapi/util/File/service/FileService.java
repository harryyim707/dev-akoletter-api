package akoletter.devakoletterapi.util.File.service;

import akoletter.devakoletterapi.editor.domain.request.SummaryRequest;
import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.util.File.domain.request.FileUploadRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {
    List<FileMst> addBoard(FileMst board, List<MultipartFile> files) throws Exception;
    Optional<FileMst> findBoard(Long fileId);
    List<FileMst> findBoards();
}
