package presentation.com.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name = "account")
public class AccountEntity {
	
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
	
	@Column(name = "account_name")
	private String accountName;
	
	
	@Column(name = "account_email")
	private String accountEmail;
	
	
	@Column(name = "account_password")
	private String accountPassword;
	
	
	public AccountEntity(String accountName, String accountEmail,
			 String accountPassword) {
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.accountPassword = accountPassword;
	}


	public AccountEntity() {
	
		// TODO Auto-generated constructor stub
	}


	public Long getAccountId() {
		return accountId;
	}


	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getAccountEmail() {
		return accountEmail;
	}


	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}


	public String getAccountPassword() {
		return accountPassword;
	}


	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	
	
	

}
