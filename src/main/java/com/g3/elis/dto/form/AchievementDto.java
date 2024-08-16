package com.g3.elis.dto.form;

import java.sql.Timestamp;

public class AchievementDto {
	    private String title;
	    private String badgeName;
	    private String certificateName;
	    private Timestamp receivedAt;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBadgeName() {
			return badgeName;
		}
		public void setBadgeName(String badgeName) {
			this.badgeName = badgeName;
		}
		public String getCertificateName() {
			return certificateName;
		}
		public void setCertificateName(String certificateName) {
			this.certificateName = certificateName;
		}
		public Timestamp getReceivedAt() {
			return receivedAt;
		}
		public void setReceivedAt(Timestamp receivedAt) {
			this.receivedAt = receivedAt;
		}
		public AchievementDto(String title, String badgeName, String certificateName, Timestamp receivedAt) {
			super();
			this.title = title;
			this.badgeName = badgeName;
			this.certificateName = certificateName;
			this.receivedAt = receivedAt;
		}
		public AchievementDto() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}