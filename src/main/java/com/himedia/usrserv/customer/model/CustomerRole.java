package com.himedia.usrserv.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_role")
public class CustomerRole {

	@Id
	@Column(name="role_id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long roleId;
	
	@Column(name="role_name", nullable=false, unique=true, length=100)
	String roleName;
	
	@Column(name="role_type")
	Integer roleType;
	
	@Column(name="role_desc", length=255)
	String roleDesc;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
