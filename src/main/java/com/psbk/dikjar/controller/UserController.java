/**
 * 
 */
package com.psbk.dikjar.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import com.psbk.dikjar.model.Users;
import com.psbk.dikjar.service.UserServices;

/**
 * @author Apey
 *
 */

@ManagedBean
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * For Internationalization
	 */
	private String localeCode;

	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("Indonesia", Locale.ENGLISH); // label, value
		countries.put("Bahasa Sunda", Locale.SIMPLIFIED_CHINESE);
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {

				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale((Locale) entry.getValue());

			}
		}
	}

	/*
	 * For Spring
	 */

	@ManagedProperty(value = "#{UserService}")
	UserServices userServices;

	List<Users> usersList;

	private String id_dosen;
	private String username;
	private String password;
	private String hak_akses;

	private Pattern pattern;
	private Matcher matcher;

	/**
	 * ^ # start of the line [_A-Za-z0-9-\\+]+ # must start with string in the
	 * bracket [ ], must contains one or more (+) ( # start of group #1
	 * \\.[_A-Za-z0-9-]+ # follow by a dot "." and string in the bracket [ ],
	 * must contains one or more (+) )* # end of group #1, this group is
	 * optional (*) @ # must contains a "@" symbol [A-Za-z0-9-]+ # follow by
	 * string in the bracket [ ], must contains one or more (+) ( # start of
	 * group #2 - first level TLD checking \\.[A-Za-z0-9]+ # follow by a dot "."
	 * and string in the bracket [ ], must contains one or more (+) )* # end of
	 * group #2, this group is optional (*) ( # start of group #3 - second level
	 * TLD checking \\.[A-Za-z]{2,} # follow by a dot "." and string in the
	 * bracket [ ], with minimum length of 2 ) # end of group #3 $ # end of the
	 * line
	 */

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * @return the userServices
	 */
	public UserServices getUserServices() {
		return userServices;
	}

	/**
	 * @param userServices
	 *            the userServices to set
	 */
	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	/**
	 * @return the usersList
	 */
	public List<Users> getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList
	 *            the usersList to set
	 */
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	/**
	 * @return the id_dosen
	 */
	public String getId_dosen() {
		return id_dosen;
	}

	/**
	 * @param id_dosen
	 *            the id_dosen to set
	 */
	public void setId_dosen(String id_dosen) {
		this.id_dosen = id_dosen;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the hak_akses
	 */
	public String getHak_akses() {
		return hak_akses;
	}

	/**
	 * @param hak_akses
	 *            the hak_akses to set
	 */
	public void setHak_akses(String hak_akses) {
		this.hak_akses = hak_akses;
	}

	/**
	 * Logic
	 */

	public String getMD5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0").append(
						Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}

	public boolean isLogin() {
		for (Users user : getUserServices().getUsers()) {
			if (user.getUsername().equalsIgnoreCase(getUsername())
					&& user.getPassword().equalsIgnoreCase(
							getMD5(getPassword()))) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidInput() {

		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(getUsername());
		return matcher.matches();
	}

	public String actionLogin() {
		if (isValidInput()) {
			if (isLogin()) {
				return "admin/home?faces-redirect=true";
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Error", "Username atau Password Salah");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return null;
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Error", "Format username tidak sesuai");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

}