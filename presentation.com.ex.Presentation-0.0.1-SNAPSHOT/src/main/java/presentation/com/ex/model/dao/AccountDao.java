package presentation.com.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import presentation.com.ex.model.entity.AccountEntity;

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Long> {
	//データを保存するinsert文に該当するメソッドを書く
	//AccountEntityを引数として受け取ってAccountEntityの内容を保存して保存した結果を返す
	AccountEntity save(AccountEntity accountEntity);	
	
	//accountNameの内容を受け取ってaccountEntityを返すメソッド
	//where account_name = ?
	AccountEntity findByAccountName(String accountName);
	AccountEntity findByAccountEmail(String accountEmail);
	
	AccountEntity findByAccountEmailAndAccountPassword(String accountEmail,String accountPassword);
	
	//一覧取得
	List<AccountEntity> findAll();
	

}
