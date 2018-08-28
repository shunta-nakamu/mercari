package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Member;

@Controller
@RequestMapping("/mercari")
public class sample {

	@RequestMapping("/sample")
	public String viewSample(Model model) {

		String helloMessage = "こんにちは";
		model.addAttribute("helloMessage", helloMessage);

		String helloMessageWithTag = "<span>こんにちは</span>";
		model.addAttribute("helloMessageWithTag", helloMessageWithTag);

		Member ichiro = new Member(1, "一郎");
		model.addAttribute("member", ichiro);

		List<Member> members = new ArrayList<>();
		members.add(ichiro);
		members.add(new Member(2, "次郎"));
		members.add(new Member(3, "三郎"));
		model.addAttribute("members", members);

		model.addAttribute("isEven", 50 % 2 == 0);
		model.addAttribute("even", "this is even");
		model.addAttribute("odd", "this is odd");

		model.addAttribute("month", 1);

		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member(1, "a"));
		memberList.add(new Member(2, "b"));
		memberList.add(new Member(3, "c"));
		memberList.add(new Member(4, "d"));

		model.addAttribute("memberList", memberList);

		return "/sample";
	}

}
