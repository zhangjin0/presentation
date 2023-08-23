package presentation.com.ex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import presentation.com.ex.model.dao.BlogDao;
import presentation.com.ex.model.entity.BlogEntity;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;

	// 内容を保存する
	public boolean createBlogPost(String blogTitle, String fileName, LocalDate blogDate, String blogDetail,
			String blogCategory, Long accountId) {
		// すでに同じタイトルと登録日のブログが存在するかを検索する
		BlogEntity blogList = blogDao.findByBlogTitleAndBlogDate(blogTitle, blogDate);
		if (blogList == null) {
			blogDao.save(new BlogEntity(blogDetail, fileName, blogTitle, accountId, blogCategory, blogDate));
			return true;
		} else {
			return false;
		}
	}

	// ブログ一覧
	public List<BlogEntity> findAllBlogPost(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findByAccountId(accountId);
		}
	}

	// 内容の更新
	public boolean editBlogPost(String blogTitle, String blogDetail, String blogCategory, Long accountId, Long blogId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (accountId == null) {
			return false;
		} else {
			blogList.setAccountId(accountId);
			blogList.setBlogId(blogId);
			blogList.setBlogTitle(blogTitle);
			blogList.setBlogDetail(blogDetail);
			blogList.setBlogCategory(blogCategory);
			blogDao.save(blogList);
			return true;
		}
	}

	public boolean editBlogImage(Long blogId, String fileName, Long accountId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (fileName == null || blogList.getBlogImage().equals(fileName)) {
			return false;
		} else {
			blogList.setAccountId(accountId);
			blogList.setBlogImage(fileName);
			blogList.setBlogId(blogId);
			blogDao.save(blogList);
			return true;
		}
	}

	// ユーザーブログ一覧
	public List<BlogEntity> selectByAll() {
		return blogDao.findAll();
	}

	public BlogEntity getBlogPost(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 削除
	@Transactional
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteById(blogId);
			return true;
		}

	}
}
