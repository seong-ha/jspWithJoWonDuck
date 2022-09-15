package co.micol.prj.member.service;

public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	private String memberAuthor;

	public MemberVO() {
		
	}
	
	public MemberVO(String memberId, String memberPassword, String memberName, String memberTel, String memberAuthor) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberAuthor = memberAuthor;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberAuthor() {
		return memberAuthor;
	}

	public void setMemberAuthor(String memberAuthor) {
		this.memberAuthor = memberAuthor;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPassword=" + memberPassword + ", memberName=" + memberName
				+ ", memberTel=" + memberTel + ", memberAuthor=" + memberAuthor + "]";
	}

}
