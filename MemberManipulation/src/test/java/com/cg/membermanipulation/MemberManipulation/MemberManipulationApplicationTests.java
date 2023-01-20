package com.cg.membermanipulation.MemberManipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.cg.membermanipulation.MemberManipulation.controller.MemberController;

import com.cg.membermanipulation.MemberManipulation.exception.AddressNotValidException;
import com.cg.membermanipulation.MemberManipulation.model.Member;
import com.cg.membermanipulation.MemberManipulation.repository.MemberRepository;

@SpringBootTest
class MemberManipulationApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	MemberController memberController;

	@MockBean
	MemberRepository memberRepo;

	@Test
	void viewAllMembersTest() {
		when(memberRepo.findAll())
				.thenReturn(Stream.of(new Member(222, "Ram", "Karnataka"), new Member(221, "Sham", "Mumbai"))
						.collect(Collectors.toList()));
		assertEquals(2, memberController.viewAllMembers().size());
	}

	@Test
	void viewMembersByAddressTest() throws AddressNotValidException {
		String address = "Karnataka";
		when(memberRepo.viewMembersByAddress(address))
				.thenReturn(Stream.of(new Member(222, "Ram", "Karnataka")).collect(Collectors.toList()));
		assertEquals(1, memberController.viewMembersByAddress(address).size());

	}

	@Test
	void addMemberTest() {
		Member member = new Member(333, "Bhuvi", "Kerala");
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(member, memberController.addMember(member));
	}

	@Test
	void memberTest() {
		Member member = new Member();
		Member mem = new Member(440, "jam", "UP");
		member.setMemberId(444);
		member.setMemberName("jim");
		member.setAddress("kashmir");
		member.getMemberId();
		member.getMemberName();
		member.getAddress();
	}

	@Test
	public void addressNotValidExceptionTest() {

		Member member1 = new Member(555, "Dia", "Manipur");
		Member member2 = new Member(556, "Farah", "Bihar");

		memberRepo.save(member1);
		memberRepo.save(member2);

		AddressNotValidException addressNotValidException = assertThrows(AddressNotValidException.class,
				() -> memberController.viewMembersByAddress("Nepal"));

		assertEquals("Nepal Address not found ", addressNotValidException.getMessage());
	}

}
