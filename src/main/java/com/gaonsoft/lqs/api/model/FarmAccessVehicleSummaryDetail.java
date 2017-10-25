package com.gaonsoft.lqs.api.model;

import java.util.LinkedList;
import java.util.List;

import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class FarmAccessVehicleSummaryDetail {
	
	private boolean allVisited;
	
	private int total;
	
	private List<FarmAccessVehicle> visitors;
	
	public void addVisitor(FarmAccessVehicle visitor) {
		if(this.visitors == null) {
			this.visitors = new LinkedList<>();
		}
		visitors.add(visitor);
	}
	
	public boolean isAllvisited() {
		for (FarmAccessVehicle farmAccessVehicle : visitors) {
			if(farmAccessVehicle.getVisitPlanDt() != null && farmAccessVehicle.getCapDt() == null) {
				this.allVisited = false;
				return allVisited;
			}
		}
		this.allVisited = true;
		return allVisited;
	}
	
	public int getTotal() {
		this.total = (visitors != null) ? visitors.size() : 0;
		return total;
	}
}
