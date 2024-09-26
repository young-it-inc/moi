package com.youngit.office.api.member.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.log.mapper.LogMapper;
import com.youngit.office.api.log.model.LogModel;
import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.dto.MemberSearchDto;
import com.youngit.office.api.member.service.MemberService;
import com.youngit.office.api.token.mapper.TokenMapper;
import com.youngit.office.api.token.model.TokenModel;
import com.youngit.office.configuration.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

@Tag(name = "회원 관리")
@RestController
@RequiredArgsConstructor
public class MemberController {
    //직원, 협력사원, 관리자: 조회, 검색, 등록, 수정, 탈퇴
    //탈퇴회원: 조회(회원유형, 아이디, 이름, 전화번호, 이메일, 거래처, 직책, 업무), 검색, 복구

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberService memberService;

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider)
    {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "회원 리스트 조회 및 검색")
    @GetMapping("/api/member")
    public ApiResponse<List<MemberDto>> getOrSearchListMember(MemberSearchDto memberSearchDto) {
        logger.info("회원 리스트 조회 및 검색");
        int count = memberService.countGetOrSearchListMember(memberSearchDto);
        List<MemberDto> result = memberService.getOrSearchListMember(memberSearchDto);
        return new ApiResponse<>(result, 0, "회원 리스트 조회 완료", count);
    }


    @Operation(summary = "회원 개별 조회")
    @GetMapping("/api/member/{memberId}")
    public ApiResponse<MemberDto> getOneMember(@PathVariable String memberId)
    {
        logger.info("회원 개별 조회");
        MemberDto result = memberService.getOneMember(memberId);
        return new ApiResponse<>(result, 0, "회원 개별 조회 완료");
    }

    @Operation(summary = "회원 등록")
    @PostMapping("/api/member")
    public ApiResponse<String> registerMember(@RequestBody MemberDto memberDto) {
        logger.info("회원 등록");
        int result = memberService.registerMember(memberDto);
        return new ApiResponse<>("회원 생성 성공");
    }

    @Operation(summary = "아이디 중복 체크", description = "")
    @GetMapping("api/member/idCheck/{id}")
    public ApiResponse<String> checkMemberId(@PathVariable String id) {
        logger.info("아이디 중복 체크");
        boolean isExist = memberService.checkMemberId(id);
        if (isExist)
        {
            return new ApiResponse<>("이미 사용 중인 id입니다. ");
        }
        else
        {
            return new ApiResponse<>("사용 가능한 id입니다. ");
        }
    }


    @Operation(summary = "회원정보 수정")
    @PutMapping("/api/member")
    public ApiResponse<String> updateMember(@RequestBody MemberDto memberDto) {
        logger.info("회원정보 수정");
        int result = memberService.updateMember(memberDto);
        return new ApiResponse<>("회원정보 수정 성공");
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping("/api/member/changepassword")
    public ApiResponse<String> changePassword()
    {
        logger.info("비밀번호 변경");
        return new ApiResponse<>("비밀번호 변경 성공");
    }



    @Operation(summary = "회원 삭제")
    @DeleteMapping("/api/member/{memberId}")
    public ApiResponse<String> deleteMember(@PathVariable String memberId) {
        logger.info("회원 삭제");
        int result = memberService.deleteMember(memberId);
        return new ApiResponse<>("회원 삭제 성공");
    }

    @Operation(summary = "로그인")
    @PostMapping("/api/login")
    public ApiResponse<MemberDto> login(@RequestBody MemberDto memberDto) {
        logger.info("로그인");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String clientIp = (String) Objects.requireNonNull(attributes).getAttribute("clientIp", RequestAttributes.SCOPE_REQUEST);
        MemberDto result = memberService.login(memberDto);
        System.out.println("result: " + result);
        if(result != null)
        {
            //로그
            LogModel logModel = new LogModel("login Success", "POST", "/api/login", result.getMemberId(), clientIp, "");
            logMapper.insertLog(logModel);

            //토큰
            String token = jwtTokenProvider.makeJwtToken(result);
            result.setToken(token);
            result.setExpriredAtToken(jwtTokenProvider.parseJwtToken(token).getExpiration());

            TokenModel tokenModel = new TokenModel();
            tokenModel.setId(result.getMemberId());
            tokenModel.setEmail(result.getEmail());
            tokenModel.setToken(token);
            tokenModel.setExpiredAtToken(result.getExpriredAtToken());
            System.out.println("tokenModel : " + tokenModel);
            if(tokenMapper.isTokenExist(memberDto.getMemberId()))
            {
                tokenMapper.updateToken(tokenModel);
            }
            else
            {
                tokenMapper.insertToken(tokenModel);
            }
            return new ApiResponse<>(result, 0, "로그인 성공");
        }
        else
        {
            LogModel logModel = new LogModel("Login Fail", "POST", "/api/login", memberDto.getMemberId(), clientIp, "");
            logMapper.insertLog(logModel);
            return new ApiResponse<>(null, 99, "로그인 실패");
        }
    }


    @Operation(summary = "권한 설정")
    @PostMapping("/api/authority/{memberId}")
    public ApiResponse<String> authority(@PathVariable String memberId) {
        logger.info("권한 설정");

        return new ApiResponse<>("권한설정 성공");
    }

}
