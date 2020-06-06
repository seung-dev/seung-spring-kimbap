package seung.spring.kimbap.quartz;

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
import seung.spring.kimbap.quartz.service.SQuartzS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "QUARTZ API SAMPLE", value = "SQuartzC")
@Slf4j
@Controller
public class SQuartzC {

    @Resource(name = "sQuartzS")
    private SQuartzS sQuartzS;
    
    @ApiOperation(response = SResponse.class, value = "SIMPLE JOB CREATE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0010"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0010(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            , @ApiParam(
                    value = "클래스명"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "seung.spring.kimbap.quartz.job.SSimpleJob"
                    ) @RequestParam String job_class
            , @ApiParam(
                    value = "시작일자(DEFAULT: now)"
                    , type = "string"
                    , allowEmptyValue = true
                    , allowMultiple = false
                    , example = "2020-01-01 00:00:00"
                    ) @RequestParam String start_time
            , @ApiParam(
                    value = "시작대기시간(ms)"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1000"
                    ) @RequestParam long start_delay
            , @ApiParam(
                    value = "반복횟수"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "100"
                    ) @RequestParam int repeat_count
            , @ApiParam(
                    value = "반복간격(ms)"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1"
                    ) @RequestParam long repeat_interval
            , @ApiParam(
                    value = "실패처리"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1"
                    ) @RequestParam int misfire_instruction
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0010(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "CRON JOB CREATE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0011"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0011(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job2"
                    ) @RequestParam String job_name
            , @ApiParam(
                    value = "클래스명"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "seung.spring.kimbap.quartz.job.SCronJob"
                    ) @RequestParam String job_class
            , @ApiParam(
                    value = "시작일자(DEFAULT: now)"
                    , type = "string"
                    , allowEmptyValue = true
                    , allowMultiple = false
                    , example = "2020-01-01 00:00:00"
                    ) @RequestParam String start_time
            , @ApiParam(
                    value = "시작대기시간(ms)"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1000"
                    ) @RequestParam long start_delay
            , @ApiParam(
                    value = "CRON EXPRESSION"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "0,10,20,30,40,50 * * ? * * *"
                    ) @RequestParam String cron_expr
            , @ApiParam(
                    value = "실패처리"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1"
                    ) @RequestParam int misfire_instruction
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0011(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB RECORDS", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0020"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0020(
            Model model
            , @ApiIgnore SRequest sRequest
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0020(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB STATE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0021"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0021(
            Model model
            , @ApiIgnore SRequest sRequest
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0021(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB UNSCHEDULE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0030"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0030(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0030(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "SIMPLE JOB RESCHEDULE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0031"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0031(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            , @ApiParam(
                    value = "시작일자(yyyy-MM-dd HH:mm:ss)"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "yyyy-MM-dd HH:mm:ss"
                    ) @RequestParam String start_time
            , @ApiParam(
                    value = "반복횟수"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "0"
                    ) @RequestParam int repeat_count
            , @ApiParam(
                    value = "반복간격"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "5000"
                    ) @RequestParam long repeat_interval
            , @ApiParam(
                    value = "실패처리"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1"
                    ) @RequestParam int misfire_instruction
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0031(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "CRON JOB RESCHEDULE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0032"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0032(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "name2"
                    ) @RequestParam String job_name
            , @ApiParam(
                    value = "시작일자(yyyy-MM-dd HH:mm:ss)"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "yyyy-MM-dd HH:mm:ss"
                    ) @RequestParam String start_time
            , @ApiParam(
                    value = "CRON EXPRESSION"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "0,5,10,15,20,25,30,35,40,45,50,55 * * ? * * *"
                    ) @RequestParam String cron_expr
            , @ApiParam(
                    value = "실패처리"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "1"
                    ) @RequestParam int misfire_instruction
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0032(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB DELETE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0040"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0040(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0040(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB START", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0050"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0050(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0050(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB PAUSE", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0051"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0051(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0051(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(response = SResponse.class, value = "JOB RESUME", notes = "QUARTZ")
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
    @RequestMapping(value = {"/rest/quartz0052"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    public String quartz0052(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "그룹"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "group1"
                    ) @RequestParam String job_group
            , @ApiParam(
                    value = "이름"
                    , type = "string"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "job1"
                    ) @RequestParam String job_name
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sQuartzS.quartz0052(sRequest));
        
        return "jsonView";
    }
    
}
