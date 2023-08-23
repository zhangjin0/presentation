package presentation.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import presentation.com.ex.service.AccountService;

@Controller

public class RegisterController {
	@Autowired
	private AccountService accountService;
	
//登録画面表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
}
	//登録処理をする
	@PostMapping("/register/process")
	public String register(@RequestParam String accountName,
			@RequestParam String accountEmail,
			@RequestParam String accountPassword) {
		//もし、accountテーブル内に登録した名前が存在しなかった場合、テーブルに保存処理をする
		if (accountService.cteateAccount(accountName, accountEmail, accountPassword)) {
			//ソース修正
			//return "login.html";
			return "redirect:/login";
		}else {
			return "register.html";
		}
		
	}
	

	}

