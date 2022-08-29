package com.ooweat.springBootOoweat.controller;

import com.ooweat.springBootOoweat.service.OoweatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author ooweat
 * @version 1.0.0
 * @class OoweatController
 * @Created 2022-08-29
 * @Desc : Spring Batch Scheduled
 * @Method
 * #1. halfOf6to9Check
 * #2. halfHoursCheck
 * #3. hoursCheck
 **/

@Slf4j
@CrossOrigin("*")
@EnableAutoConfiguration
@RestController
public class OoweatController {

	@Autowired
	private OoweatService ooweatService;

	@Value("${use.flag}")
	private String useFlag;

	/**
	 * @Scheduled
	 *     1: sec(0-59)
	 *     2: min(0-59)
	 *     3: hours(0-23)
	 *     4: day(1-31)
	 *     5: month(1-12)
	 *     6: weekend(0-7) 0, 7 : Sun / 1: Mon / 6:Sat
	* */

	@Scheduled(cron="${batch.hoursInMorning.schedule}") // every 1 hours 06:00 AM ~ 09:00 AM
	public void halfOf6to9Check() {
        log.info(com.ooweat.library.common.Util.pathUtil().classWithMethodName());
        Boolean bl = useFlag.equalsIgnoreCase("y") ? useFlag.equalsIgnoreCase("y") : useFlag.equalsIgnoreCase("y");
		log.info(com.ooweat.library.common.Util.logUtil().now()+":{}", bl);
	}

	@Scheduled(cron="${batch.halfHoursInWorkTime.schedule}") // every 30 minutes in 09:00 AM ~ 18:00 PM
	public void halfHoursCheck() {
		log.info(com.ooweat.library.common.Util.pathUtil().classWithMethodName());
        Boolean bl = useFlag.equalsIgnoreCase("y") ? useFlag.equalsIgnoreCase("y") : useFlag.equalsIgnoreCase("y");
        log.info(com.ooweat.library.common.Util.logUtil().now()+":{}", bl);
	}

	@Scheduled(cron="${batch.hours.schedule}") // every 1 hours
	public void hoursCheck(){
		log.info(com.ooweat.library.common.Util.pathUtil().classWithMethodName());
        Boolean bl = useFlag.equalsIgnoreCase("y") ? useFlag.equalsIgnoreCase("y") : useFlag.equalsIgnoreCase("y");
        log.info(com.ooweat.library.common.Util.logUtil().now()+":{}", bl);
	}

}
