package com.potatos.stackoverflow.domain.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column
    private MemberStatus memberStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum MemberStatus{
        ACTIVE("활동",0),
        DORMANCY("휴면",1),
        WITHDRAW("탈퇴",2);


        @Getter
        private String strStatus;
        @Getter
        private int numStatus;

        MemberStatus(String strStatus, int numStatus){
            this.strStatus = strStatus;
            this.numStatus = numStatus;
        }

        // postDto에서 상수 1로만 값을 넣기 위한 메서드
        public static MemberStatus fromInt(int numStatus) {
            for (MemberStatus value : values()) {
                if (value.numStatus == numStatus) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid num: " + numStatus);
        }

        // postDto에서 문자열로 값을 넣기 위한 메서드
        public static MemberStatus fromString(String strStatus) {
            for (MemberStatus value : values()) {
                if (value.strStatus.equals(strStatus)) {

                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid str: " + strStatus);
        }



    }


    public static Member of(String displayName, String email, String password, String memberStatus) {
        Member member = new Member();
        member.displayName=displayName;
        member.email=email;
        member.password=password;
        member.memberStatus = MemberStatus.fromString(memberStatus);

        return member;
    }

    //최소한의 데이터는 뭐가 필요하지?
    //
    public static Member of(String email){
        Member member = new Member();
        member.displayName = "oauth_member";
        member.email = email;
        member.password = "oauth_pwd";
        member.memberStatus = MemberStatus.fromString("활동");

        return member;
    }

}
