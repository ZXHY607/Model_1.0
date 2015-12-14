package net.gslab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
@Id
private String fileDir;
private String name;

private int tId;
private String intro;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getIntro() {
	return intro;
}
public void setIntro(String intro) {
	this.intro = intro;
}

public int gettId() {
	return tId;
}
public void settId(int tId) {
	this.tId = tId;
}
public String getFileDir() {
	return fileDir;
}
public void setFileDir(String fileDir) {
	this.fileDir = fileDir;
}



}
