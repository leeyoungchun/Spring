package kr.or.ddit.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TimeCheckJob extends QuartzJobBean {

	// 스케줄링에따라 executeInternal() 콜백
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd kk:mm:ss");
		System.out.println("timeCheckJob : " + sdf.format(new Date()));
		
	}

}
