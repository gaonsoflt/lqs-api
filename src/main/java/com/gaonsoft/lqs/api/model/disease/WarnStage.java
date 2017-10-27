package com.gaonsoft.lqs.api.model.disease;

import java.util.HashMap;
import java.util.Map;

public enum WarnStage {
	STAGE1(1, "관심"), 
	STAGE2(2, "주의"), 
	STAGE3(3, "경계"), 
	STAGE4(4, "심각");

	private int value;
	private String stageName;

	private static Map<Integer, WarnStage> mapping;

	private WarnStage(int value, String stageName) {
			this.value = value;
			this.stageName= stageName;
		}

	private WarnStage(int value) {
			this.value = value;
		}

	public int getValue() {
		return value;
	}

	public String getStageName() {
		return stageName;
	}

	public static WarnStage valueOf(int value) {
		if (mapping == null) {
			initMapping();
		}
		return mapping.get(value);
	}

	private static void initMapping() {
		mapping = new HashMap<>();
		for (WarnStage ws : WarnStage.values()) {
			mapping.put(ws.getValue(), ws);
		}
	}
}
