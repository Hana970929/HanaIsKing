package com.yg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.dao.MemberDao;
import com.yg.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	
	@Override
	public void regist(MemberDto dto) {
		dao.register(dto);
	}

	@Override
	public boolean login(String id, String pw) {
		return dao.login(id, pw);
	}

	@Override
	public MemberDto getInfo(String id) {
		return dao.getInfo(id);
	}

	@Override
	public List<MemberDto> getAll() {
		return dao.getAll();
	}

	@Override
	public int updatePoint() {
		return dao.updatePoint();
	}

	@Override
	public void delete(String id) {
		dao.deleteMember(id);
	}

	@Override
	public void watchAD(String id) {
		dao.watchAD(id);
	}

	@Override
	public void purchase(String id, int price) {
		dao.purchase(id, price);
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.updateMember(dto);
	}
	
}
