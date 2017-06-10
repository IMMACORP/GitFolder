/**
 * Copyright(C) 2017 Luvina SoftWare
MstJapan.java, Jan 19, 2017 DoVanDung
 */
package manageuser.entities;
/**
 * MstJapan
 * @author dovandung
 *
 */
public class MstJapan {
	private String codeLevel;
	private String nameLevel;

	/**
	 * @param codeLevel
	 * @param nameLevel
	 */
	public MstJapan(String codeLevel, String nameLevel) {
		super();
		this.codeLevel = codeLevel;
		this.nameLevel = nameLevel;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

}
