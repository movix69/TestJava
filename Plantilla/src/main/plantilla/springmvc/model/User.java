package main.plantilla.springmvc.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UserID")
	private int userID;
	
	@NotNull(message="No puede estar vacio")
	@Column(name="Email")
	private String email;

	@Column(name="LoginUser")
	private String loginUser;

	@Column(name="Password")
	private String password;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="UsersRoles"
		, joinColumns={
			@JoinColumn(name="Users_UserID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Roles_RoleID")
			}
		)
	private List<Roles> roles;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Roles> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}