/**
 * Copyright(C) 2017 Luvina SoftWare
TblDetailUserJapan.java, Jan 19, 2017 DoVanDung
 */
package manageuser.entities;
/**
 * TblDetailUserJapan
 * @author dovandung
 *
 */
public class TblDetailUserJapan {
	private int detailUserJapanId;
	private int userId;
	private String codeLevel;
	private YearMonthDay startDate;
	private YearMonthDay endDate;
	private int total;

	public int getDetailUserJapanId() {
		return detailUserJapanId;
	}

	public void setDetailUserJapanId(int detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public YearMonthDay getStartDate() {
		return startDate;
	}

	public void setStartDate(YearMonthDay startDate) {
		this.startDate = startDate;
	}

	public YearMonthDay getEndDate() {
		return endDate;
	}

	public void setEndDate(YearMonthDay endDate) {
		this.endDate = endDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @param detailUserJapanId
	 * @param userId
	 * @param codeLevel
	 * @param startDate
	 * @param endDate
	 * @param total
	 */
	public TblDetailUserJapan(int detailUserJapanId, int userId, String codeLevel, YearMonthDay startDate, YearMonthDay endDate,
			int total) {
		super();
		this.detailUserJapanId = detailUserJapanId;
		this.userId = userId;
		this.codeLevel = codeLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}
	

}
