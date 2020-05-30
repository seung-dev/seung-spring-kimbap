package seung.spring.kimbap.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.test.service.STestS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "테스트 API", value = "STestC")
@Slf4j
@Controller
public class STestC {

	@Resource(name = "sTestS")
	private STestS sTestS;
	
	@ApiOperation(response = SResponse.class, value = "JPA 조회 테스트", notes = "JAP findAll")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = "{\"error_code\": \"0000\",\"error_message\": \"\",\"request\": {\"request_code\": \"81967e1b-82b6-452e-808c-3bf544c3e10c\"},\"data\": {\"test0000\": [{\"id\": 25,\"col01\": \"컬럼1\",\"col02\": \"컬럼2\",\"date_c\": 1590862789863,\"date_u\": 1590862789863}]}}"
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = "{\"error_code\": \"0000\",\"error_message\": \"\",\"request\": {\"request_code\": \"81967e1b-82b6-452e-808c-3bf544c3e10c\"},\"data\": {\"test0000\": [{\"id\": 25,\"col01\": \"컬럼1\",\"col02\": \"컬럼2\",\"date_c\": 1590862789863,\"date_u\": 1590862789863}]}}"
									)})
					)
	})
	@RequestMapping(value = {"/rest/test/test0000"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String test0000(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "요청코드"
					, type = "string"
					, allowEmptyValue = true
					, allowMultiple = false
					, example = "81967e1b-82b6-452e-808c-3bf544c3e10c"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sTestS.test0000(sRequest));
		
		return "jsonView";
		
	}
	
	@ApiOperation(response = SResponse.class, value = "JPA 등록 테스트", notes = "JPA saveAndFlush")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@RequestMapping(value = {"/rest/test/test0010"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String test0010(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "컬럼01"
					, type = "string"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "컬럼1"
					) @RequestParam String col01
			, @ApiParam(
					value = "컬럼02"
					, type = "string"
					, allowEmptyValue = true
					, allowMultiple = false
					, example = "컬럼2"
					) @RequestParam String col02
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sTestS.test0010(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JPA 수정 테스트", notes = "JPA saveAndFlush")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@RequestMapping(value = {"/rest/test/test0020"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String test0020(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "아이디"
					, type = "long"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "1"
					) @RequestParam String id
			, @ApiParam(
					value = "컬럼01"
					, type = "string"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "컬럼1 수정"
					) @RequestParam String col01
			, @ApiParam(
					value = "컬럼02"
					, type = "string"
					, allowEmptyValue = true
					, allowMultiple = false
					, example = "컬럼2 수정"
					) @RequestParam String col02
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sTestS.test0020(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JPA 삭제 테스트", notes = "JPA delete")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@RequestMapping(value = {"/rest/test/test0030"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String test0030(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "아이디"
					, type = "long"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "1"
					) @RequestParam String id
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sTestS.test0030(sRequest));
		
		return "jsonView";
	}
	
}
