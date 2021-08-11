package com.yg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.dto.MemberDto;
import com.yg.job.MyJob;
import com.yg.service.MemberServiceImpl;

@Controller
public class HomeController {
	@Autowired
	MemberServiceImpl ms;
	
	
	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}
	@RequestMapping(value = "/home")
	public String home2() {
		return "home";
	}
	@RequestMapping(value="/main")
	public String login(String id, String pw,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		if(ms.login(id, pw)) {
			session.setAttribute("id", id);
			if(id.equals("admin")) {
				List<MemberDto> list = ms.getAll();
				model.addAttribute("list",list);
				return "admin";
			}
			MemberDto dto = ms.getInfo(id);
			model.addAttribute("name",dto.getName());
			model.addAttribute("id",dto.getId());
			model.addAttribute("point",dto.getPoint());
			return "main";
		} else {
			return "login_false";
		}
	}
	@RequestMapping(value="/main_")
	public String login(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDto dto = ms.getInfo(id);
		model.addAttribute("name",dto.getName());
		model.addAttribute("id",dto.getId());
		model.addAttribute("point",dto.getPoint());
		return "main";
		
	}
	@RequestMapping(value="/regist")
	public String regist() {
		return "regist";
	}
	@RequestMapping(value="/regist_action")
	public String regist(MemberDto dto) {
		ms.regist(dto);
		return "regist_action";
	}
	@RequestMapping(value="/delete")
	public String delete(String id) {
		ms.delete(id);
		return "delete";
	}
	@RequestMapping(value = "/start")  // 스케줄러 시작할게요 (= 10초마다 MyJob 실행할게요).
	public String homeStart(Model model) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		  // define the job and tie it to our HelloJob class
		  JobDetail job = JobBuilder.newJob(MyJob.class)
		      .withIdentity("job1", "group1")
		      .build();

		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.newTrigger()
		      .withIdentity("trigger1", "group1")
		      .startNow()
		      .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
		      .build();

		  // Tell quartz to schedule the job using our trigger
		  scheduler.scheduleJob(job, trigger);
		  scheduler.start();
		  List<MemberDto> list = ms.getAll();
		  model.addAttribute("list",list);
		  System.out.println("스케줄러가 시작됨.");
		return "admin";
	}
	
	@RequestMapping(value = "/end")    // 스케줄러 끝낼게요 (= MyJob 그만 할게요).
	public String homeEnd(Model model) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		List<MemberDto> list = ms.getAll();
		model.addAttribute("list",list);
		scheduler.shutdown();
		System.out.println("스케줄러가 종료됨.");
		return "admin";
	}
	@RequestMapping(value = "/pointup")
	public String watchAD(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ms.watchAD(id);
		return "watch_ad";
	}
	@RequestMapping(value="/purchase")
	public String purchase(int price, String subject, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDto dto = ms.getInfo(id);
		if(dto.getPoint()>=price) {
			ms.purchase(id, price);
			model.addAttribute("subject",subject);
			return "purchase";
		} else {
			return "purchase_fail";
		}
	}
	@RequestMapping(value="/update")
	public String updateMember(String id, Model model) {
		MemberDto dto = ms.getInfo(id);
		model.addAttribute("id",dto.getId());
		model.addAttribute("name",dto.getName());
		model.addAttribute("point",dto.getPoint());
		model.addAttribute("pw",dto.getPw());
		return "update";
	}
	@RequestMapping(value="/updateAction")
	public String updateMember(MemberDto dto) {
		System.out.println(dto);
		ms.updateMember(dto);
		return "update_action";
	} 
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "logout";
	}
}
