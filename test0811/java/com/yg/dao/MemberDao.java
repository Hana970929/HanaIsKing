package com.yg.dao;

import java.util.List;

import com.yg.dto.MemberDto;

public interface MemberDao {
	void register(MemberDto dto);
	boolean login(String id, String pw);
	MemberDto getInfo(String id);
	List<MemberDto> getAll();
	int updatePoint();
	void deleteMember(String id);
	void watchAD(String id);
	void purchase(String id, int price);
	void updateMember(MemberDto dto);
}
