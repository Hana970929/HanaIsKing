package com.yg.service;

import java.util.List;

import com.yg.dto.MemberDto;

public interface MemberService {
	void regist(MemberDto dto);
	boolean login(String id, String pw);
	MemberDto getInfo(String id);
	List<MemberDto> getAll();
	int updatePoint();
	void delete(String id);
	void watchAD(String id);
	void purchase(String id, int price);
	void updateMember(MemberDto dto);
}
