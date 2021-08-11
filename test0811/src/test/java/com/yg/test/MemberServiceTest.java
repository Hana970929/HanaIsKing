package com.yg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yg.dto.MemberDto;
import com.yg.service.MemberServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberServiceTest {
	@Autowired
	MemberServiceImpl service;
	
	@Test
	public void testLogin() throws Exception {
		System.out.println(service.login("admin", "1234"));
		System.out.println(service.login("admin", "1233"));
	}
	@Test
	public void testGetInfo() throws Exception {
		MemberDto dto = service.getInfo("test");
		System.out.println(dto);
	}
	@Test
	public void testUpdateInfo() throws Exception {
		MemberDto dto = new MemberDto("test","test","test",10000);
		service.updateMember(dto);
	}
}
