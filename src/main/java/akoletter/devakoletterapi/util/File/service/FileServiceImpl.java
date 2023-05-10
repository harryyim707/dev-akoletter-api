package akoletter.devakoletterapi.util.File.service;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.repo.FileMstRepository;
import akoletter.devakoletterapi.util.File.controller.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileMstRepository fileMstRepository;
    private final FileHandler fileHandler;


    @Autowired
    public FileServiceImpl(FileMstRepository fileMstRepository) {
        this.fileMstRepository = fileMstRepository;
        this.fileHandler = new FileHandler(); //생성자
    }
    //저장해주는 메소드
    public List<FileMst> saveFile(
            FileMst board,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 Board 에 대한 list 를 가지고 있는다

        List<FileMst> list = fileHandler.parseFileInfo(board.getFileId(), files);
        int size = list.size();
        int index = 0;
        if (list.isEmpty()){
            return null;
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            while (size>0) {
                FileMst pictureBeans = list.get(index);
                fileMstRepository.saveAndFlush(pictureBeans);
                size--;
                index++;
                pictureBeans.getFileId();
            }

        }
        return list;
    }

    public List<FileMst> findBoards() {
        return fileMstRepository.findAll();
    }

    public Optional<FileMst> findBoard(int fileId) {

        return fileMstRepository.findByfileId(fileId);
    }
}
