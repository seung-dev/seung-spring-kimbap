package seung.spring.boot.conf.web;

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
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "공통 API", value = "SApplicationC")
@Slf4j
@Controller
public class SController {

	@ApiOperation(response = SRequest.class, value = "접속 테스트", notes = "요청한 네트워크, 헤더, 세션, 쿼리 정보를 응답.")
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
	@RequestMapping(value = {"/reflect"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String reflect(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "요청코드"
					, type = "string"
					, allowEmptyValue = true
					, allowMultiple = false
					, example = "1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sRequest);
		
		return "jsonView";
	}
	
}
