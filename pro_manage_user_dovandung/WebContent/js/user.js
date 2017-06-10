function getInputForm() {
	var frm = document.inputform;
	if (frm == undefined)
		frm = document.mainform;
	return frm;
}

function putToInput(currentPage, sortFlag, sortFullName, sortCodeLevel,sortEndDate, accessType) {
	var frm = getInputForm();
	frm.currentPage.value = currentPage;
	frm.sortFlag.value = sortFlag;
	frm.sortFullName.value = sortFullName;
	frm.sortCodeLevel.value = sortCodeLevel;
	frm.sortEndDate.value = sortEndDate;
	frm.accessType.value = accessType;
	frm.submit();

}
function putSortFullNameToInput(sortFullName, currentPage, accessType) {
	var frm = getInputForm();
	frm.sortFullName.value = sortFullName;
	frm.sortFlag.value = "sortFullName";
	frm.currentPage.value = currentPage;
	frm.accessType.value = accessType;
	frm.submit();
}
function putSortCodeLevelToInput(sortCodeLevel, currentPage, accessType) {
	var frm = getInputForm();
	frm.sortCodeLevel.value = sortCodeLevel;
	frm.sortFlag.value = "sortCodeLevel";
	frm.currentPage.value = currentPage;
	frm.accessType.value = accessType;
	frm.submit();
}
function putSortEndDateToInput(sortEndDate, currentPage, accessType) {
	var frm = getInputForm();
	frm.sortEndDate.value = sortEndDate;
	frm.sortFlag.value = "sortEndDate";
	frm.currentPage.value = currentPage;
	frm.accessType.value = accessType;
	frm.submit();
}
/**
 * Hàm ẩn hiện vùng trình độ tiếng Nhật
 */
function toggleDetailUserJapan() {
	$(".detailUserJapan").toggle();
}
/**
 * Hàm ẩn hiện vùng trình độ tiếng Nhật
 */
function toggleJapanInfor() {
	$(".japanInfor").toggle(350);
}
/**
 * Hàm bắt sự kiện khi click nút Xóa trong ADM005
 */
function confirmDeleteUser(userId) {
	var retVal = confirm("削除しますが、よろしいでしょうか。");
	if (retVal) {
		window.location.href = 'DeleteUser.do?userId=' + userId;
	}
}
