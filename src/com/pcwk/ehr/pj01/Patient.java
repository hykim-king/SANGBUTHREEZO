package com.pcwk.ehr.pj01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Patient {
	// 환자를 등록할때 사용하는 기본 정보들
	private String name;// 이름
	private String gender;// 성별
	private String bloodType;// 혈액형
	private int birthDay;// 생년월일 YYYY.MM.DD
	private int height;// 키
	private int weight;// 몸무게
	private String registerdDate;// 등록일
	
	private boolean isNotified = true;
	public PatientStatus status = PatientStatus.Safe;
	
	//환자의 바이탈 인포를 담는 리스트
	public ArrayList<VitalInfo> vitalinfo=new ArrayList<VitalInfo>();
	
	


	public void setRegisterdDate(String registerdDate) {
		this.registerdDate = registerdDate;
	}


	Patient(){
		super();
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Patient) {
			Patient p = (Patient) obj;
			if (this.name.equals(p.getName()) || this.registerdDate.equals(p.getRegisterdDate())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getRegisterdDate() {
		return registerdDate;
	}

	public void setRegisterdDate(Date registerdDate) {
		this.registerdDate = registerdDate.toString();
	}

	

	public boolean isNotified() {
		return isNotified;
	}

	public void setNotified(boolean isNotified) {
		this.isNotified = isNotified;
	}

	public PatientStatus getStatus() {
		return status;
	}

	public void setStatus(PatientStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "환자 정보 [이름=" + name + ", 성별=" + gender + ", 혈액형=" + bloodType + ", 생년월일=" + birthDay
				+ ", 신장(cm)=" + height + ", 체중(Kg)=" + weight + ", 입원일 =" + registerdDate + ", 보고 여부="
				+ isNotified + ", 건강 상태=" + status + ", 최근 심박수 =" + this.vitalinfo.get(this.vitalinfo.size()-1).getBpm() +", 최근 혈당="+this.vitalinfo.get(this.vitalinfo.size()-1).getBloodSugar() +", 최근 수축기혈압"+ this.vitalinfo.get(this.vitalinfo.size()-1).getSbp() + "최근 이완기 혈압" + this.vitalinfo.get(this.vitalinfo.size()-1).getDbp() + "]";
	}

	//환자를 등록할때 필수정보들은 다음과 같다.(Vital은 등록할때 받지 않아도 된다.)
	public Patient(String name, String gender, String bloodType, int birthDay, int height, int weight) {
		super();
		this.name = name;
		this.gender = gender;
		this.bloodType = bloodType;
		this.birthDay = birthDay;
		this.height = height;
		this.weight = weight;
		
		Date now = new Date();
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		this.registerdDate =formatter.format(now);
	}

    public Patient(String name, String gender, String bloodType, int birthDay, String registerdDate, int height,
			int weight) {
		super();
		this.name = name;
		this.gender = gender;
		this.bloodType = bloodType;
		this.birthDay = birthDay;
		this.registerdDate = registerdDate;
		this.height = height;
		this.weight = weight;
	}	
	
    public String toJson() {
        return "{\"name\":\"" + name + "\",\"gender\":\"" + gender + "\",\"bloodType\":\"" + bloodType + "\",\"birthday\":" + birthDay +
                ",\"height\":" + height + ",\"weight\":" + weight + ",\"registerdDate\":\"" + registerdDate + ",\" recentBpm\":"  + this.vitalinfo.get(this.vitalinfo.size()-1).getBpm() +"\"recentBloodsugar\":"+this.vitalinfo.get(this.vitalinfo.size()-1).getBloodSugar() +"\", recentSbp\""+ this.vitalinfo.get(this.vitalinfo.size()-1).getSbp() + "recentDbp\"" + this.vitalinfo.get(this.vitalinfo.size()-1).getDbp() + "]\"}";
    }	
	
	public static Patient fromJson(String json) {
        try {
            json = json.trim();
            if (json.startsWith("{") && json.endsWith("}")) {
                String[] parts = json.substring(1, json.length() - 1).split(",");
                String name = parts[0].split(":")[1].replace("\"", "").trim();
                String gender = parts[1].split(":")[1].replace("\"", "").trim();
                String bloodType = parts[2].split(":")[1].replace("\"", "").trim();
                int birthDay = Integer.parseInt(parts[3].split(":")[1].trim());
                int height = Integer.parseInt(parts[4].split(":")[1].trim());
                int weight = Integer.parseInt(parts[5].split(":")[1].trim());
                String registerDate =parts[6].split(":")[1].replace("\"", "").trim();
                VitalInfo info=new VitalInfo();
                info.setBpm(Integer.parseInt(parts[7].split(":")[1].trim()));
                info.setBloodSugar(Integer.parseInt(parts[8].split(":")[1].trim()));
                info.setSbp(Integer.parseInt(parts[9].split(":")[1].trim()));
                info.setDbp(Integer.parseInt(parts[10].split(":")[1].trim()));
                Patient p = new Patient(name, gender, bloodType, birthDay, registerDate, height, weight);
                p.vitalinfo.add(info);
                return p;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }
	
} // class end

enum PatientStatus {
	Safe, Dangerous, Very_Dangerous
}
