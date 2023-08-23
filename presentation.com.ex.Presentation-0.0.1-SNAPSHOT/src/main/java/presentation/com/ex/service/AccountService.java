package presentation.com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import presentation.com.ex.model.dao.AccountDao;
import presentation.com.ex.model.entity.AccountEntity;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;

	// 登録処理のメソッド
	public boolean cteateAccount(String accountName, String accountEmail, String accountPassword) {
		// 同じメールアドレスがDBに存在する場合は登録したくない
		AccountEntity account = accountDao.findByAccountName(accountName);
		if (account == null) {
			accountDao.save(new AccountEntity(accountName, accountEmail, accountPassword));
			return true;
		} else {
			return false;
		}
	}

	// ユーザー一覧の取得
	public List<AccountEntity> getAllAccounts() {
		return accountDao.findAll();
	}

//	public boolean loginCheck(String accountEmail,String accountPassword) {
//		if(accountDao.findByAccountEmailAndAccountPassword(accountEmail, accountPassword) == null) {
//			return false;
//		}else {
//			return true;
//		}
//	}
	//修正バージョン
	public AccountEntity loginCheck(String accountEmail,String accountPassword) {
			AccountEntity account = accountDao.findByAccountEmailAndAccountPassword(accountEmail, accountPassword);
		if(account == null) {
			return null;
		}else {
			return account;
		}
	}


}
