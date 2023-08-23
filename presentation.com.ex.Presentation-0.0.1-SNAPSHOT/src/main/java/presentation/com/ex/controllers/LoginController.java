package presentation.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import presentation.com.ex.model.entity.AccountEntity;
import presentation.com.ex.service.AccountService;


@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;
//ログイン画面を表示させる
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	@PostMapping("/login/process")
	public String login(@RequestParam String accountEmail,@RequestParam String accountPassword, Model model) {
		/*セッションをセットしていないせいで、ログイン後のAccountNameがnullになっているのでセッションを使用します。*/
		AccountEntity account = accountService.loginCheck(accountEmail, accountPassword);
		if(account == null) {
			return "login.html";
		}else {
			session.setAttribute("account", account);
			return "redirect:/account/blog/all";
		}
		
		
		
//		if(accountService.loginCheck(accountEmail, accountPassword)) {
//			model.addAttribute("email",accountEmail);
//			model.addAttribute("password",accountPassword);
//			return "blog.html";
//		}else {
//			//メールアドレスかパスワードのどちらか間違えていた場合には
//			//ログイン画面にとどまるようにする
//			return "login.html";
//		}
		
	}
}
