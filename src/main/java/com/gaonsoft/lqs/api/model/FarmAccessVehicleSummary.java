package com.gaonsoft.lqs.api.model;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gaonsoft.lqs.api.common.util.DateUtil;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class FarmAccessVehicleSummary {
	
	@ApiModelProperty(readOnly = true, notes = "{date} : {allVisited}, {total}, [visitors]")
	private Map<String, FarmAccessVehicleSummaryDetail> summary;
	
	public FarmAccessVehicleSummary(List<FarmAccessVehicle> data) {
		this.summary = new TreeMap<String, FarmAccessVehicleSummaryDetail>(); 
		
		for (FarmAccessVehicle fav : data) {
//			System.out.println(fav.getVisitPlanDt() + " / " + fav.getCapDt());
			String key = null;
			if(fav.getCapDt() != null) {
				try {
					key = DateUtil.timestampToDateString(fav.getCapDt());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				key = fav.getVisitPlanDt().toString();
			}
			
			FarmAccessVehicleSummaryDetail detail;
			if(this.summary.containsKey(key)) {
				detail = this.summary.get(key);
			} else {
				detail = new FarmAccessVehicleSummaryDetail();
			}
			detail.addVisitor(fav);
			this.summary.put(key, detail);
		}
	}
	
	public void removeAll() {
		this.summary.clear();
	}
	
	public void removeSummary(String key) {
		if(summary.containsKey(key)) {
			summary.remove(key);
		}
	}
	
	public void addSummary(String key, FarmAccessVehicleSummaryDetail value) {
		this.summary.put(key, value);
	}
}
