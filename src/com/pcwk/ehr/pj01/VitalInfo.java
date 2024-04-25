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

	public VitalInfo(int bPM, int sBP, int dBP, int bloodSugar, String checkTime) {
		super();
		BPM = bPM;
		SBP = sBP;
		DBP = dBP;
		this.bloodSugar = bloodSugar;
		this.checkTime = checkTime;
	}
    
	@Override
	public String toString() {
		return "환자 생체정보 [BPM=" + BPM + ", SBP=" + SBP + ", DBP=" + DBP + ", bloodSugar=" + bloodSugar + ", checkTime="
				+ checkTime + "]";
	}
	
	public String vitalToJson() {
        return "{\"checkTime\":\"" + checkTime + "\",\"BPM\":\"" + BPM + "\",\"SBP\":\"" + SBP + "\",\"DBP\":" + DBP +
                ",\"bloodSugar\":" + bloodSugar + ",\"}";
    }	
	
	public static VitalInfo fromJson(String json) {
        try {
            json = json.trim();
            if (json.startsWith("{") && json.endsWith("}")) {
                String[] parts = json.substring(1, json.length() - 1).split(",");
                String checkTime = parts[0].split(":")[1].replace("\"", "").trim();
                int BPM = Integer.parseInt(parts[1].split(":")[1].trim());
                int SBP = Integer.parseInt(parts[2].split(":")[1].trim());
                int DBP = Integer.parseInt(parts[3].split(":")[1].trim());
                int BloodSugar = Integer.parseInt(parts[4].split(":")[1].trim());
                return new VitalInfo(BPM,SBP,DBP,BloodSugar,checkTime);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }
	
} // class end


