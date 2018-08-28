package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.domain.User;
import com.example.demo.form.UserLoginForm;

@Controller
@SessionAttributes(types= {User.class})
@RequestMapping(value = "/mercari")
public class UserLoginController {	
	/**
	 * ログインフォームを初期化する.
	 * 
	 * @return UserLoginForm
	 */
	@ModelAttribute
	public UserLoginForm setUpForm() {
		return new UserLoginForm();
	}
	

	/**
	 * 利用者ログイン画面を表示する.
	 * 
	 * @param form　フォーム
	 * @param result　リザルト
	 * @param model　モデル
	 * @param error　エラー
	 * @return　利用者ログイン画面
	 */
	@RequestMapping(value = "/loginForm")
	public String index(UserLoginForm form, BindingResult result
			,Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		
		if(error != null) {
			System.err.println("user: login failed");
			result.addError(new ObjectError("loginError", "入力情報が不正です"));
		}

		return "/login";
	}
}
