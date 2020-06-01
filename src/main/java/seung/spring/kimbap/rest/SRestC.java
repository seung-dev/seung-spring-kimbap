package seung.spring.kimbap.rest;

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
import seung.spring.kimbap.rest.service.SRestS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "REST API SAMPLE", value = "SRestC")
@Slf4j
@Controller
public class SRestC {

	@Resource(name = "sRestS")
	private SRestS sRestS;
	
	@ApiOperation(response = SResponse.class, value = "CREATE", notes = "JPA")
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
	@RequestMapping(value = {"/rest/rest0010"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String rest0010(
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
		
		model.addAttribute("no-wrap", sRestS.rest0010(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "RECORD", notes = "JAP")
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
	@RequestMapping(value = {"/rest/rest0020"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String rest0020(
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
		
		model.addAttribute("no-wrap", sRestS.rest0020(sRequest));
		
		return "jsonView";
		
	}
	
	@ApiOperation(response = SResponse.class, value = "UPDATE", notes = "JPA")
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
	@RequestMapping(value = {"/rest/rest0030"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String rest0030(
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
		
		model.addAttribute("no-wrap", sRestS.rest0030(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "DELETE", notes = "JPA")
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
	@RequestMapping(value = {"/rest/rest0040"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String rest0040(
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
		
		model.addAttribute("no-wrap", sRestS.rest0040(sRequest));
		
		return "jsonView";
	}
	
}
