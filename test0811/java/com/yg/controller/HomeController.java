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
	
	
	@RequestMapping(value = "/home")
	public String home(MemberDto dto) {
		if(dto.getId()!=null) {
			ms.regist(dto);
		}
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
	@RequestMapping(value="/delete")
	public String delete(String id) {
		ms.delete(id);
		return "delete";
	}
	@RequestMapping(value = "/start")  // �����ٷ� �����ҰԿ� (= 10�ʸ��� MyJob �����ҰԿ�).
	public String homeStart() throws SchedulerException {
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
		  System.out.println("�����ٷ��� ���۵�.");
		return "home";
	}
	
	@RequestMapping(value = "/end")    // �����ٷ� �����Կ� (= MyJob �׸� �ҰԿ�).
	public String homeEnd() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.shutdown();
		System.out.println("�����ٷ��� �����.");
		return "home";
	}
	@RequestMapping(value = "/pointup")    // �����ٷ� �����Կ� (= MyJob �׸� �ҰԿ�).
	public String watchAD(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ms.watchAD(id);
		return "watch_ad";
	}
	@RequestMapping(value="/purchase")
	public String purchase(int price, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDto dto = ms.getInfo(id);
		if(dto.getPoint()>=price) {
			ms.purchase(id, price);
			return "purchase";
		} else {
			return "purchase_fail";
		}
	}
	@RequestMapping(value="update")
	public String updateMember(String id, Model model) {
		MemberDto dto = ms.getInfo(id);
		model.addAttribute("id",dto.getId());
		model.addAttribute("name",dto.getName());
		model.addAttribute("point",dto.getPoint());
		model.addAttribute("pw",dto.getPw());
		return "update";
	}
	@RequestMapping(value="updateAction")
	public String updateMember(MemberDto dto) {
		System.out.println(dto);
		ms.updateMember(dto);
		return "update_action";
	} 
	
}
