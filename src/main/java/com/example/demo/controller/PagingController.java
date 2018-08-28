package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.ListStatus;
import com.example.demo.repository.listStatusRepository;

/**
 * ページングを行うコントローラー.
 * @author Shunta
 *
 */
@Controller
@RequestMapping("/mercari")
public class PagingController {

	@Autowired
	private listStatusRepository statusRepository;
	/**
	 * ページングをするメソッド.
	 * @param pageStatus
	 * @return
	 */
	@RequestMapping("/paging")
	public String paging(@RequestParam ("page") String pageStatus) {
		int page = Integer.parseInt(pageStatus);
		statusRepository.updateStatus(page);
		
		return "redirect:/mercari/viewAll";
		
	}
}
