package com.devcom.entity;

import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

	@Entity
	public class Feed {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column
		int feedid;
		@Column
		String query;
		@JsonFormat(pattern="dd-MM-yyyy")
		private Date feedDate;
		@Column
		String topic;
		@Column
		int relevance;
		
		@ManyToOne(cascade =CascadeType.MERGE)
		@JoinColumn(name="fd_fk")
		private Developer developer;
		
		
		public Developer getDeveloper() {
			return developer;
		}
		public void setDeveloper(Developer developer) {
			this.developer = developer;
		}
		public int getFeedid() {
			return feedid;
		}
		public void setFeedid(int feedid) {
			this.feedid = feedid;
		}
		public Date getFeedDate() {
			return feedDate;
		}
		public void setFeedDate(Date feedDate) {
			this.feedDate = feedDate;
		}
		public String getQuery() {
			return query;
		}
		public void setQuery(String query) {
			this.query = query;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public int getRelevance() {
			return relevance;
		}
		public void setRelevance(int relevance) {
			this.relevance = relevance;
		}
}