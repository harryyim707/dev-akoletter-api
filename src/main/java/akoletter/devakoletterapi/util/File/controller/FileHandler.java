package akoletter.devakoletterapi.util.File.controller;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.repo.FileMstRepository;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class FileHandler {

    private final FileMstRepository fileMstRepository;
    @Autowired
    BlobContainerClient blobContainerClient;

    public List<FileMst>  parseFileInfo(
            int fileId,
            List<MultipartFile> multipartFiles
    ) throws Exception {


        // 반환을 할 파일 리스트
        List<FileMst> fileList = new ArrayList<>();
        int id = fileId;
        // 일이 빈 것이 들어오면 빈 것을 반환
        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        // 경로를 지정하고 그곳에다가 저장
        String path = "azure-blob://akoletterimages/";

        // 파일 직접다루는 코드
        for (MultipartFile multipartFile : multipartFiles) {

            // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
            if (!multipartFile.isEmpty()) {
                // jpeg, png 파일들만 받아서 처리할 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif"; //혹시몰라 노재가 gif도 줄지..
                    }

                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        break;
                    }
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String new_file_name = System.nanoTime() + originalFileExtension;
                BlobClient client = blobContainerClient.getBlobClient(new_file_name);
                client.upload(multipartFile.getInputStream(), multipartFile.getSize(), false);

                // 생성 후 리스트에 추가
                FileMst board = new FileMst();
                board.setFileId(id);
                board.setFileNm(new_file_name);
                board.setOrgFileNm(multipartFile.getOriginalFilename());
                board.setFileSize(multipartFile.getSize());
                board.setFilePath(path);
                board.setFileExt(originalFileExtension);
                board.setFileType(originalFileExtension);
                board.setRefTbl("post_mst");

                fileList.add(board);
                id++;
            }
        }
        fileMstRepository.saveAllAndFlush(fileList);
        return fileList;
    }

}
