package presentation.com.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	

}
