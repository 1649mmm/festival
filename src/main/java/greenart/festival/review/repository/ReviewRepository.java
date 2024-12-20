package greenart.festival.review.repository;

import greenart.festival.board.Board;
import greenart.festival.member.entity.Member;
import greenart.festival.review.dto.CommentDTO;
import greenart.festival.review.dto.ReplyDTO;
import greenart.festival.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {



    @Query("SELECT new greenart.festival.review.dto.ReplyDTO(r.reviewId, r.board.boardId, r.rootNum, r.depth, r.content, r.email, r.regDate) from Review r where r.rootNum = :rootNum and r.depth = 1")
    List<ReplyDTO> findReplyByRootNum(@Param("rootNum") Long rootNum);

    @Query("SELECT r FROM Review r WHERE r.board = :board")
    List<Review> findByBoard(Board board);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.board = :board and r.depth = :depth")
    Long countByBoard(Board board, Long depth);



}
