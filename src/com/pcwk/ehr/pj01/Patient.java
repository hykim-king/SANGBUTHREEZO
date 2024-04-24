package com.pcwk.ehr.pj01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Patient {
	// 환자를 등록할때 사용하는 기본 정보들
	private String name;// 이름
	private String gender;// 성별
	private String bloodType;// 혈액형
	private String birthDay;// 생년월일 YYYY.MM.DD
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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
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


	//환자를 등록할때 필수정보들은 다음과 같다.(Vital은 등록할때 받지 않아도 된다.)
	public Patient(String name, String gender, String bloodType, String birthDay, int height, int weight) {
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

}

enum PatientStatus {
	Safe, Dangerous, Very_Dangerous
}
