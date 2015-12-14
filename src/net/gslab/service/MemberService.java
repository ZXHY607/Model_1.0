package net.gslab.service;

import java.util.List;

import net.gslab.entity.Member;


public interface MemberService extends BaseService<Member>{

	List<Member> listMembers(String groupName);
	
	List<Member> findByType(String type);

	Member getMemberByName(String memberName);

	void loginSuccess(Member dbMember);

	Member getMemberByLoadName(String loadname);
	
    public Member getByID(int id);//获取学生
}
