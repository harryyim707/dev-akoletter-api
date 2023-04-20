package akoletter.devakoletterapi.util.File.service;

import org.springframework.beans.factory.annotation.Autowired;

public class FileServiceImpl {

   /* private final BoardRepository boardRepository;

    private final FileHandler fileHandler;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.fileHandler = new FileHandler();
    }

    public Board addBoard(
            Board board,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 Board 에 대한 list 를 가지고 있는다
        List<Board> list = fileHandler.parseFileInfo(board.getId(), files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<Board> pictureBeans = new ArrayList<>();
            for (Board boards : list) {
                pictureBeans.add(boardRepository.save(boards));
            }
        }

        return boardRepository.save(board);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> findBoard(Long id) {
        return boardRepository.findById(id);
    }*/
}
