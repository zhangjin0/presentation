package presentation.com.ex.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import presentation.com.ex.model.entity.AccountEntity;
import presentation.com.ex.model.entity.BlogEntity;
import presentation.com.ex.service.BlogService;

@RequestMapping("/account/blog")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@GetMapping("/all")
	public String getBlogPage(Model model) {
		//現在のユーザー情報を取得するため
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		// accountListから現在ログインしている人のユーザー名を取得する
		String accountName = accountList.getAccountName();
		
		//現在のユーザーに関連するブログ投稿を取得する
		List<BlogEntity> blogList = blogService.findAllBlogPost(accountId);
		model.addAttribute("accountName", accountName);
		model.addAttribute("blogList", blogList);
		return "blog.html";
	}

	@GetMapping("/register")
	public String getBlogRegisterPage(Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// accountListから現在ログインしている人のユーザー名を取得する
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		model.addAttribute("registerMessage", "新規記事登録");
		return "blog_register.html";
	}
	
	//画像ファイル名を取得する
	//画像ファイル名をDBに保存する
	@PostMapping("/register/process")
	public String blogRegister(@RequestParam String blogTitle, @RequestParam LocalDate blogDate,
			@RequestParam String blogCategory, @RequestParam MultipartFile blogImage, @RequestParam String blogDetail,
			Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
				+ blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resource/static/blog-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 入力したデータDB保存する
		// trueが返された場合は、blogページが表示されます。
		// falseが返された場合、blog_registerページが表示されます。
		if (blogService.createBlogPost(blogTitle, fileName, blogDetail, blogDetail, blogCategory, accountId)) {
			return "blog_register_finish.html";
		} else {
			model.addAttribute("registerMessage", "既に登録完成です");
			return "blog_register.html";
		}
	}

	@GetMapping("/edit/{blogId}")
		public String getBlogEditPage(@PathVariable Long blogId,Model model) {
			AccountEntity accountList = (AccountEntity) session.getAttribute("account");
			String accountName = accountList.getAccountName();
			model.addAttribute("accountName", accountName);
			BlogEntity blogList = blogService.getBlogPost(blogId);
			if(blogList == null) {
				return "redirecr:/account/blog";
			}else {
				model.addAttribute("blogList", blogList);
				model.addAttribute("editMeassage", "記事編集");
				return "blog_edit.html";
			}
	
	}


	@GetMapping("/delete/list")
	public String getBlogDeletePage(Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		String accountName = accountList.getAccountName();
		List<BlogEntity> blogList = blogService.findAllBlogPost(accountId);
		model.addAttribute("accountName", accountName);
		model.addAttribute("blogList", blogList);
		model.addAttribute("deleteMessage", "削除一覧");
		return "blog-delete.html";
	}

	@PostMapping("/delete")
	public String blogDelete(@RequestParam Long blogId, Model model) {
		if (blogService.deleteBlog(blogId)) {
			return "blog_delete_finish.html";
		} else {
			model.addAttribute("DeleteDetailMessage", "記事削除に失敗しました");
			return "blog_delete.html";
		}
	}
		
		@GetMapping("delete/detail/{blogId}")
		public String getBlogDeleteDetailPage(@PathVariable Long blogId,Model model) {
			AccountEntity accountList = (AccountEntity) session.getAttribute("account");
			Long accountId = accountList.getAccountId();
			String accountName = accountList.getAccountName();
			model.addAttribute("accountName", accountName);
			BlogEntity blogList = blogService.getBlogPost(blogId);
			if(blogList == null) {
				return "redirecr:account/blog";
			}else {
				model.addAttribute("blogList",blogList);
				model.addAttribute("DeleteDetailMessage", "削除記事詳細");
				return "blog_delete_detail.html";
			}
		
	}

	// logout
	@GetMapping("/logout")
	public String Logout() {
		session.invalidate();
		return "redirect:/account/login";

	}
}
