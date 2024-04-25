package com.pcwk.ehr.pj01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VitalInfo {
	private int BPM;// 심박수
	private int SBP;// 수축기 혈압
	private int DBP;// 이완기 혈압
	private int bloodSugar;//혈당
	private String checkTime;
	
	
	
	public VitalInfo() {
		super();
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public VitalInfo(int bpm, int sbp, int dbp, int bloodSugar) {
		super();
		this.BPM = bpm;
		this.SBP = sbp;
		this.DBP = dbp;
		this.bloodSugar = bloodSugar;
		Date now = new Date();
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		this.checkTime =formatter.format(now);
	}
	
	public int getBpm() {
		return BPM;
	}
	public void setBpm(int bpm) {
		this.BPM = bpm;
	}
	public int getSbp() {
		return SBP;
	}
	public void setSbp(int sbp) {
		this.SBP = sbp;
	}
	public int getDbp() {
		return DBP;
	}
	public void setDbp(int dbp) {
		this.DBP = dbp;
	}
	public int getBloodSugar() {
		return bloodSugar;
	}
	public void setBloodSugar(int bloodSugar) {
		this.bloodSugar = bloodSugar;
	}
	

}
