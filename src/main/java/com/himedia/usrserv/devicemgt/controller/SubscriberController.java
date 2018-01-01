package com.himedia.usrserv.devicemgt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.usrserv.devicemgt.pojo.Subscriber4update;
import com.himedia.usrserv.devicemgt.pojo.SubscriberAudit;
import com.himedia.usrserv.devicemgt.pojo.SubscriberInfo;

@RestController
@RequestMapping(value="/subscriber")
public class SubscriberController {

	/**
	 * 设备注册
	 * @param subscriberInfo
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public Object register(@RequestBody @Valid SubscriberInfo subscriberInfo) {
		
	}
	
	/**
	 * 设备审核结果
	 * @param auditResult
	 * @param description
	 * @return
	 */
	@RequestMapping(value="/audit", method=RequestMethod.POST)
	public Object subscriberAudit(@RequestBody @Valid SubscriberAudit subscriberAudit) {
		
	}
	
	/**
	 * 批量审核设备
	 * @param subscriberAudits
	 * @return
	 */
	@RequestMapping(value="batch_audit", method = RequestMethod.POST)
	public Object batchAudit(@RequestBody @Valid List<SubscriberAudit> subscriberAudits) {
		
	}
	
	/**
	 * 设备查询
	 * @param activeStatus
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.GET)
	public Object querySubscriber(@RequestParam("activeStatus") int activeStatus, 
			@RequestParam("pageNo") int pageNo, 
			@RequestParam("pageSize") int pageSize) {
		
	}
	
	/**
	 * 删除设备
	 * @param subscriberId
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.DELETE)
	public Object delete(@RequestParam("subscriberId") long subscriberId) {
		
	}
	
	/**
	 * 更新设备信息
	 * @param subscriber4update
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Object update(@RequestBody @Valid Subscriber4update subscriber4update) {
		
	}
}
