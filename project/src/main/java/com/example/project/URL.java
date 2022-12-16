package com.example.project;


import org.hibernate.annotations.DialectOverride.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;


@Builder
@Entity
@Table(name="URL")
public class URL {
	@Id
	public short uniquekey;
	public int count;
	public String url;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public short getUniquekey() {
		return uniquekey;
	}
	public void setUniquekey(short uniquekey) {
		this.uniquekey = uniquekey;
	}
	public URL() {
		// TODO Auto-generated constructor stub
	}
	public URL(short uniquekey, int count, String url) {
		super();
		this.uniquekey = uniquekey;
		this.count = count;
		this.url = url;
	}
	
}
