package akoletter.devakoletterapi.util.File.service;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<FileMst> saveFile(FileMst board, List<MultipartFile> files) throws Exception;
}
