package com.yg.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.yg.dto.MemberDto;

public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void register(MemberDto dto) {
		sqlSession.insert("com.yg.mapper.MemberMapper.regist",dto);
	}

	@Override
	public boolean login(String id, String pw) {
		String pw2 = sqlSession.selectOne("com.yg.mapper.MemberMapper.login", id);
		if(pw.equals(pw2)) {
			return true;
		}
		return false;
	}

	@Override
	public MemberDto getInfo(String id) {
		return sqlSession.selectOne("com.yg.mapper.MemberMapper.getInfo", id);
	}

	@Override
	public List<MemberDto> getAll() {
		return sqlSession.selectList("com.yg.mapper.MemberMapper.getAll");
	}

	@Override
	public int updatePoint() {
		return sqlSession.update("com.yg.mapper.MemberMapper.pointUp");
	}

	@Override
	public void deleteMember(String id) {
		sqlSession.delete("com.yg.mapper.MemberMapper.deleteMember",id);
	}

	@Override
	public void watchAD(String id) {
		sqlSession.update("com.yg.mapper.MemberMapper.watchAD",id);
	}

	@Override
	public void purchase(String id, int price) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("price", price);
		sqlSession.update("com.yg.mapper.MemberMapper.purchase", map);
	}

	@Override
	public void updateMember(MemberDto dto) {
		sqlSession.update("com.yg.mapper.MemberMapper.updateMember",dto);
	}

}
