package com.fullstack.gateway.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import com.fullstack.gateway.mapper.GatewayLogsMapper;
import com.fullstack.gateway.model.GatewayAccessLogs;
import com.fullstack.gateway.utils.BeanConvertUtils;

/**
 * mq消息接收者
 *
 */
@Configuration

public class MessageHandler {

	@Autowired
	private GatewayLogsMapper gatewayLogsMapper;

	/**
	 * 接收访问日志, 保存到MySQL数据库中
	 *
	 * @param access
	 */
	@RabbitListener(queues = "rexdedao.access.logs")
	public void accessLogsQueue(@Payload Map access) {
		try {
			if (access != null) {
				@SuppressWarnings("unchecked")
				GatewayAccessLogs logs = BeanConvertUtils.mapToObject(access, GatewayAccessLogs.class);
				if (logs != null && logs.getAccess_id() != null) {
					logs.setResponse_time(caldate(logs.getResponse_time()));
					logs.setRequest_time(caldate(logs.getRequest_time()));
					logs.setUse_time(logs.getResponse_time().getTime() - logs.getRequest_time().getTime());
					gatewayLogsMapper.insertLog(logs);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Date caldate(Date currentdate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(currentdate);
		cal.add(Calendar.HOUR_OF_DAY, 13);
		return cal.getTime();

	}
}
