package com.yg.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yg.service.MemberService;
import com.yg.service.MemberServiceImpl;

public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		WebApplicationContext cxt = ContextLoader.getCurrentWebApplicationContext();
		MemberService ms = cxt.getBean(MemberServiceImpl.class);
		System.out.println(ms.updatePoint()+"명에게 1포인트 증가");
	}

}
