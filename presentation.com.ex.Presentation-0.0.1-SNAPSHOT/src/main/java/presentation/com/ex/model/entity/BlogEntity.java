package presentation.com.ex.model.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "blog")

@Data

@AllArgsConstructor
@RequiredArgsConstructor
public class BlogEntity {

	@Id
	@Column(name = "blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long blogId;
	
	
	@Column(name = "blog_detail")
	private String blogDetail;
	
	
	@Column(name = "blog_image")
	private String blogImage;
	
	
	@Column(name = "blog_title")
	private String blogTitle;
	
	@Column(name = "account_id")
	private long accountId;
	
	@Column(name = "blog_category")
	private String blogCategory;
	

	@Column(name = "blog_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate blogDate;


	public BlogEntity(String blogDetail, String blogImage, String blogTitle, long accountId, String blogCategory,
			LocalDate blogDate) {
		this.blogDetail = blogDetail;
		this.blogImage = blogImage;
		this.blogTitle = blogTitle;
		this.accountId = accountId;
		this.blogCategory = blogCategory;
		this.blogDate = blogDate;
	}



	
	


	

}