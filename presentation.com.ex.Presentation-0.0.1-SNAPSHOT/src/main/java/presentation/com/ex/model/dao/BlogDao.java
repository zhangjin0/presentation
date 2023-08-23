package presentation.com.ex.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import presentation.com.ex.model.entity.BlogEntity;

public interface BlogDao extends JpaRepository<BlogEntity, Long> {
	List<BlogEntity> findByAccountId(Long accountId);

	// ブログ内容を保存する
	BlogEntity save(BlogEntity blogEntity);

	// blogTitle,blogDateを条件として、BlogEntityを取得する
	BlogEntity findByBlogTitleAndBlogDate(String blogTitle, LocalDate blogDate);

	BlogEntity findByBlogId(Long blogId);



}
