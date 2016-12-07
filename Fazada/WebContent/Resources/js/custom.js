$(function() {
	/* Side bar functions */
	var trigger = $('.hamburger'), overlay = $('.overlay'), isClosed = false;

	trigger.click(function() {
		hamburger_cross();
	});

	function hamburger_cross() {

		if (isClosed == true) {
			overlay.hide();
			trigger.removeClass('is-open');
			trigger.addClass('is-closed');
			isClosed = false;
		} else {
			overlay.show();
			trigger.removeClass('is-closed');
			trigger.addClass('is-open');
			isClosed = true;
		}
	}

	$('[data-toggle="offcanvas"]').click(function() {
		$('#wrapper').toggleClass('toggled');
	});

	/* Page functions */
	if ($('body').is('#user-management-page')) {
		$(loadUserList);
	}
	if ($('body').is('#order-management-page')) {
		$(loadOrderList);
		var currentYear = new Date().getFullYear();
		$('#order-select').append($('<option>', {
			value : currentYear,
			text : 'In ' + currentYear
		}));
	}

	if ($('body').is('#my-order-page')) {
		$(loadMyOrderList);
		var currentYear = new Date().getFullYear();
		$('#my-order-select').append($('<option>', {
			value : currentYear,
			text : 'In ' + currentYear
		}));
	}

	if ($('body').is('#account-info-page')) {
		$(loadInfo());
	}

	$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		$("ul.nav-tabs > li.active").removeClass("active");
		$("a[href='" + e.target.hash + "']").closest("li").addClass("active");
	});

	/* Client functions */
	$("#order-select").change(function() {
		var today = new Date();
		var priorDate = new Date();
		switch ($("#order-select").val()) {
		case "All":
			loadOrderList();
			break;
		case "In 15 days": {
			priorDate.setDate(today.getDate() - 15);
			loadOrderListWithRange(priorDate, today);
			break;
		}
		case "In 30 days": {
			priorDate.setDate(today.getDate() - 30);
			loadOrderListWithRange(priorDate, today);
			break;
		}
		case "In 3 months": {
			priorDate.setMonth(today.getMonth() - 3);
			loadOrderListWithRange(priorDate, today);
			break;
		}
		case "In 6 months": {
			priorDate.setMonth(today.getMonth() - 6);
			loadOrderListWithRange(priorDate, today);
			break;
		}
		default: {
			if (/^\d+$/.test($("#order-select").val())) {
				priorDate = new Date($("#order-select").val(), 0, 2);
				loadOrderListWithRange(priorDate, today);
			}
			loadMyOrderListWithRange(priorDate, today);
			break;
		}
		}
	});

	$("#my-order-select").change(function() {
		var today = new Date();
		var priorDate = new Date();
		switch ($("#my-order-select").val()) {
		case "All":
			loadMyOrderList();
			break;
		case "In 15 days": {
			priorDate.setDate(today.getDate() - 15);
			loadMyOrderListWithRange(priorDate, today);
			break;
		}
		case "In 30 days": {
			priorDate.setDate(today.getDate() - 30);
			loadMyOrderListWithRange(priorDate, today);
			break;
		}
		case "In 3 months": {
			priorDate.setMonth(today.getMonth() - 3);
			loadMyOrderListWithRange(priorDate, today);
			break;
		}
		case "In 6 months": {
			priorDate.setMonth(today.getMonth() - 6);
			loadMyOrderListWithRange(priorDate, today);
			break;
		}
		default: {
			if (/^\d+$/.test($("#my-order-select").val())) {
				priorDate = new Date($("#my-order-select").val(), 0, 2);
				loadMyOrderListWithRange(priorDate, today);
			}
			break;
		}
		}
	});

	var timer;

	$("#searchForm").keyup(function() {
		clearTimeout(timer);
		timer = setTimeout(function(event) {
			if ($("#search").val() == "") {
				loadOrderList();
			} else {
				searchOrderByUserNameOrOrderId($("#search").val());
			}
			$("#search").focus();
		}, 500);

	});

	$("#loginForm").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			requestLogin();
		}
	});

	$("#login-btn").click(requestLogin);

	$("#signupForm").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			requestSignUp();
		}
	});

	$("#signup-btn").click(requestSignUp);

	$("#forgetForm").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			requestResetPassword();
		}
	});

	$("#forget-btn").click(requestResetPassword)

	$("#user-update-btn").click(function() {
		if (checkUpdateInput()) {
			$("#editForm").submit();
		}
	});

	window.actionEvents = {
		'click .edit' : function(e, value, row, index) {
			if (row != null) {
				$("#editModal").find("#id").val(row.id);
				$("#editModal").find("#userName").val(row.userName);
				$("#editModal").find("#firstName").val(row.firstName);
				$("#editModal").find("#lastName").val(row.lastName);
				$("#editModal").find("#dateOfBirth").val(
						formatDate(row.dateOfBirth));
				$("#editModal").find("#email").val(row.email);
				if (row.active) {
					$("#editModal").find("input[value='true']").prop('checked',
							true);
				} else {
					$("#editModal").find("input[value='false']").prop(
							'checked', true);
				}
			}
		}
	};

	$("#editButton").click(
			function() {
				var acc = getSelectedRow();
				if (acc != null) {
					$("#editModal").find("#id").val(acc.id);
					$("#editModal").find("#userName").val(acc.userName);
					$("#editModal").find("#firstName").val(acc.firstName);
					$("#editModal").find("#lastName").val(acc.lastName);
					$("#editModal").find("#dateOfBirth").val(
							formatDate(acc.dateOfBirth));
					$("#editModal").find("#email").val(acc.email);
					if (acc.active) {
						$("#editModal").find("input[value='true']").prop(
								'checked', true);
					} else {
						$("#editModal").find("input[value='false']").prop(
								'checked', true);
					}
				} else {
					$("#editButton").prop('disabled', true);
				}
			});

	$("#manager-add-btn").click(function() {
		if (checkManagementInput($("#addModal"))) {
			var data = $("#addModal").find("#addForm").serializeArray();
			var json = convertArrayToJSON(data);
			$.ajax({
				url : 'http://localhost:8080/fazadaws/account/add',
				type : "POST",
				contentType : "application/json; charset=utf-8",
				dataType : "text",
				data : JSON.stringify(json),
				success : function(response) {
					loadUser();
					$("#addModal").find("#addForm").trigger("reset");
					showAJAXSuccessMessage(response);
				},
				error : function(data, message, xhr) {
					showAJAXErrorMessage(data.responseText);
				}
			});
			$("#addModal").modal("hide");
		}
	});

	$("#manager-edit-btn").click(function() {
		if (checkUpdateUser($("#editModal"))) {
			var data = $("#editModal").find("#editForm").serializeArray();
			var json = convertArrayToJSON(data);
			$.ajax({
				url : 'http://localhost:8080/fazadaws/account/edit',
				type : "POST",
				contentType : "application/json; charset=utf-8",
				dataType : "text",
				data : JSON.stringify(json),
				success : function(response) {
					loadUser();
					showAJAXSuccessMessage(response);
				},
				error : function(data, message, xhr) {
					showAJAXErrorMessage(data.responseText);
				}

			});
			$("#editModal").modal("hide");
		}
	});

	$("#password-change-btn").click(function() {
		if (checkChangePassword()) {
			var data = $("#changePassForm").serializeArray();
			var json = convertArrayToJSON(data);
			$.ajax({
				url : "http://localhost:8080/fazadaws/account/changePassword",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				dataType : "text",
				data : JSON.stringify(json),
				success : function(response) {
					showAJAXSuccessMessage(response);
				},
				error : function(data, message, xhr) {
					showAJAXErrorMessage(data.responseText);
				}
			});
			$("#changePassForm").trigger("reset");
		}
	});

	$(".submenu").click(function() {
		$("#submenu").find(".submenu").removeClass("active");
		$(this).addClass("active");
	})
});

/* Normal AJAX functions */
function requestLogin() {
	if (checkLoginInput()) {
		var data = $("#loginForm").serializeArray();
		var json = convertArrayToJSON(data);
		$.ajax({
			url : "/fazadaws/account/login",
			type : "POST",
			contentType : "application/json; charset=utf-8",
			dataType : "text",
			data : JSON.stringify(json),
			success : function(response) {
				switch (response) {
				case "admin":
				case "user":
				case "staff": {
					var input = $("<input>").attr("type", "hidden").attr(
							"name", "role").val(response);
					$('#loginForm').append($(input));
					$("#loginForm").submit();
					break;
				}
				default: {
					showAJAXErrorMessage(response);
					break;
				}
				}
			},
			error : function(data, message, xhr) {
				showAJAXErrorMessage(data.responseText);
			}
		});
	}
}

function requestSignUp() {
	if (checkSignupInput($("#signupForm"))) {
		var data = $("#signupForm").serializeArray();
		var json = convertArrayToJSON(data);
		$.ajax({
			url : 'http://localhost:8080/fazadaws/account/signup',
			type : "POST",
			contentType : "application/json; charset=utf-8",
			dataType : "text",
			data : JSON.stringify(json),
			success : function(response) {
				$('#signupForm').trigger("reset");
				showAJAXSuccessMessage(response);
			},
			error : function(data, message, xhr) {
				showAJAXErrorMessage(data.responseText);
			}
		});
	}
}

function requestResetPassword() {
	var email = $("#forgetForm").find("#email").val();
	if (email == "") {
		$("#forgetForm").find("#email_error").html("Please input email.");
	} else if (!email
			.match("^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$")) {
		$("#forgetForm").find("#email_error").html(
				"Email format is not correct.");
	} else {
		$("#forgetForm").find("#email_error").html("");
		var data = $("#forgetForm").find("#email").val();
		$.ajax({
			url : "http://localhost:8080/fazadaws/account/reset",
			type : "POST",
			contentType : "text/plain; charset=utf-8",
			dataType : "text",
			data : data,
			success : function(response) {
				$('#forgetForm').trigger("reset");
				showAJAXSuccessMessage(response);
			},
			error : function(data, message, xhr) {
				showAJAXErrorMessage(data.responseText);
			}
		});
	}
}

function searchOrderByUserNameOrOrderId(value) {
	$.ajax({
		url : '/fazadaws/order/list/search/' + value,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
			});
			$('#items').text(list.length);
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

/* Convert Array To JSON */
function convertArrayToJSON(data) {
	var json = {};
	$.each(data, function(v) {
		if (this.value != null) {
			v = this.value;
		} else {
			v = '';
		}
		if (this.name == "dateOfBirth") {
			v = parseDate(v, "dd/mm/yyyy");
		}
		if (json[this.name] != null) {
			if (!json[this.name].push) {
				json[this.name] = [ json[this.name] ];
			}
			json[this.name].push(v);
		} else {
			json[this.name] = v;
		}
	});
	return json;
}

/* Load user table */
function loadUserList() {
	$.ajax({
		url : '/fazadaws/account/list',
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').bootstrapTable({
				formatNoMatches : function() {
					return "No user found";
				},
				data : list
			});
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
				$('#editButton').prop('disabled', false);
			});
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

/* Load order table */
function loadOrderList() {
	$.ajax({
		url : '/fazadaws/order/list',
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').bootstrapTable({
				formatNoMatches : function() {
					return "No order found";
				},
				data : list
			});
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
			});
			$('#items').text(list.length);
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

function loadMyOrderList() {
	$.ajax({
		url : '/fazadaws/order/list/user/'
				+ $("#session_userName").text().trim(),
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').bootstrapTable({
				formatNoMatches : function() {
					return "No order found";
				},
				data : list
			});
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
			});
			$('#items').text(list.length);
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

function loadMyOrderListWithRange(priorDate, today) {
	$.ajax({
		url : '/fazadaws/order/list/user/time/',
		type : "POST",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : JSON.stringify({
			"userName" : $("#session_userName").text().trim(),
			"date1" : priorDate.toDateString(),
			"date2" : today.toDateString()
		}),
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
			});
			$('#items').text(list.length);
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

function loadOrderListWithRange(priorDate, today) {
	$.ajax({
		url : '/fazadaws/order/list/time/' + priorDate.toDateString() + ","
				+ today.toDateString(),
		type : "GET",
		contentType : "application/json; charset=utf-8",
		success : function(list) {
			$('#table').bootstrapTable("load", list);
			$('#table').on('click-row.bs.table', function(e, row, $element) {
				$('.success').removeClass('success');
				$($element).addClass('success');
			});
			$('#items').text(list.length);
		},
		error : function() {
			showAJAXErrorMessage('Error in loading data');
		}
	});
}

function loadInfo() {
	$
			.ajax({
				url : '/fazadaws/account/info/' + $("#accountInfo").text(),
				type : "GET",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					$("#infoDiv").find("#id").html(data.id);
					$("#infoDiv").find("#userName").html(data.userName);
					$("#infoDiv").find("#firstName").html(data.firstName);
					$("#infoDiv").find("#firstName").editable('setValue',
							data.firstName);
					$("#infoDiv").find("#lastName").html(data.lastName);
					$("#infoDiv").find("#lastName").editable('setValue',
							data.lastName);
					$("#infoDiv").find("#dateOfBirth").html(data.dateOfBirth);
					$("#infoDiv").find("#dateOfBirth").editable('setValue',
							data.dateOfBirth, true);
					$("#infoDiv").find("#email").html(data.email);
					$("#infoDiv").find("#email").editable('setValue',
							data.email);
				},
				error : function(response) {
					showAJAXErrorMessage(response);
				}
			});
}
/* Get selected row */
function getSelectedRow() {
	var index = $("#table").find('tr.success').data('index');
	return $("table").bootstrapTable('getData')[index];
}

function showAJAXSuccessMessage(response) {
	$("#ajaxMessage").removeClass();
	$("#ajaxMessage").addClass("alert");
	$("#ajaxMessage").addClass("alert-success");
	$("#ajaxMessage").html(response);
	$("#ajaxMessage").fadeIn();
	$("#ajaxMessage").fadeOut(2500);
}

function showAJAXErrorMessage(response) {
	$("#ajaxMessage").removeClass();
	$("#ajaxMessage").addClass("alert");
	$("#ajaxMessage").addClass("alert-danger");
	$("#ajaxMessage").html(response);
	$("#ajaxMessage").fadeIn();
	$("#ajaxMessage").fadeOut(2500);
}

/* Bootstrap table formatters */
function dateFormatter(value, row, index) {
	var date = new Date(value);
	var mm = date.getMonth() + 1;
	var dd = date.getDate();
	var yyyy = new String(date.getFullYear());
	if (mm < 10) {
		mm = "0" + mm;
	}
	if (dd < 10) {
		dd = "0" + dd;
	}
	return dd + "/" + mm + "/" + yyyy;
}

function activeFormatter(value, row, index) {
	if (value == true) {
		return "Active";
	} else {
		return "Inactive";
	}
}

function priceFormatter(value, row, index) {
	return "$" + value;
}

function statusFormatter(value, row, index) {
	switch (value) {
	case -1:
		return "Cancelled";
	case 0:
		return "Processing";
	case 1:
		return "Shipping";
	case 2:
		return "Done";
	default:
		return "Invalid status";
	}
}

function detailFormatter(index, row) {
	var html = [];
	html.push('<div class="container" style="font-size:20px">Order detail:');
	$.each(row.orderdetails, function(key, value) {
		html.push('<span style="font-size:18px">' + value.product.productName
				+ '</span><br/><span style="font-size:14px">Quantity: '
				+ value.quantity + '</span>');
	});
	html.push("</div>");
	return html.join('<br/><br/>');
}

function actionFormatter(value, row, index) {
	return [
			'<a class="edit ml10" href="#" title="Edit" data-toggle="modal" data-target="#editModal">',
			'<i class="glyphicon glyphicon-edit"></i>', '</a> ', ].join('');
}

/* Date operations */
function formatDate(input) {
	var date = new Date(input);
	var mm = date.getMonth() + 1;
	var dd = date.getDate();
	var yyyy = new String(date.getFullYear());
	if (mm < 10) {
		mm = "0" + mm;
	}
	if (dd < 10) {
		dd = "0" + dd;
	}
	return dd + "/" + mm + "/" + yyyy;
}

function parseDate(input, format) {
	if (input.match("\\d{1,2}[/]\\d{1,2}[/]\\d{1,4}")) {
		format = format || 'dd/mm/yyyy';
		var parts = input.match(/(\d+)/g), i = 0, fmt = {};
		format.replace(/(yyyy|dd|mm)/g, function(part) {
			fmt[part] = i++;
		});
		return new Date(parts[fmt['yyyy']], parts[fmt['mm']] - 1,
				parts[fmt['dd']]);
	} else {
		return null;
	}
}

/* Input validation */
function checkLoginInput() {
	var username = $("#username").val();
	var password = $("#password").val();
	var result = true;
	if (username == null || username == "") {
		$("#username_error").html("Please enter username.");
		result = false;
	} else if (username.length > 20) {
		$("#username_error").html("Username is too long.");
		result = false;
	} else {
		$("#username_error").html("");
	}
	if (password == null || password == "") {
		$("#password_error").html("Please enter password.");
		result = false;
	} else if (password.length > 20) {
		$("#password_error").html("Password is too long.");
		result = false;
	} else {
		$("#password_error").html("");
	}
	return result;
}

function checkUpdateInput() {
	var firstname = $("#firstName").val();
	var lastname = $("#lastName").val();
	var dob = $("#dateOfBirth").val();
	var email = $("#email").val();
	var result = true;
	if (firstname == null || firstname == "") {
		$("#firstname_error").html("Please enter first name.");
		result = false;
	} else if (firstname.length > 30) {
		$("#firstname_error").html("First name is too long.");
		result = false;
	} else {
		$("#firstname_error").html("");
	}
	if (lastname == null || lastname == "") {
		$("#lastname_error").html("Please enter last name.");
		result = false;
	} else if (lastname.length > 30) {
		$("#lastname_error").html("Last name is too long.");
		result = false;
	} else {
		$("#lastname_error").html("");
	}

	if (dob == null || dob == "") {
		$("#dob_error").html("Please enter date of birth.");
		result = false;
	} else {
		var date = parseDate(dob, "dd/mm/yyyy");
		if (date == null) {
			$("#dob_error").html("Date of birth is not in correct format.");
			result = false;
		} else {
			var today = new Date();
			today.setDate(today.getDate() - 1);
			if (date >= today) {
				$("#dob_error").html(
						"Date of birth cannot be later than today.");
				result = false;
			} else {
				$("#dob_error").html("");
			}
		}
	}

	if (email == null || email == "") {
		$("#email_error").html("Please enter email.");
		result = false;
	} else if (email.length > 50) {
		$("#email_error").html("Email is too long.");
		result = false;
	} else if (!email
			.match("^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$")) {
		$("#email_error").html("Email's format is not valid");
		result = false;
	} else {
		$("#email_error").html("");
	}
	return result;
}

function checkSignupInput(parent) {
	var username = parent.find("#userName").val();
	var password = parent.find("#password").val();
	var confirm = parent.find("#confirm").val();
	var firstname = parent.find("#firstName").val();
	var lastname = parent.find("#lastName").val();
	var dob = parent.find("#dateOfBirth").val();
	var email = parent.find("#email").val();
	var result = true;
	if (username == null || username == "") {
		parent.find("#username_error").html("Please enter username.");
		result = false;
	} else if (username.length > 20) {
		parent.find("#username_error").html("Username is too long.");
		result = false;
	} else {
		parent.find("#username_error").html("");
	}
	if (password == null || password == "") {
		parent.find("#password_error").html("Please enter password.");
		result = false;
	} else if (password.length > 20) {
		parent.find("#password_error").html("Password is too long.");
		result = false;
	} else {
		parent.find("#password_error").html("");
		if (confirm == null || confirm == "") {
			parent.find("#password_confirm_error").html(
					"Please re-type password.");
			result = false;
		} else if (confirm.length > 20) {
			parent.find("#password_confirm_error")
					.html("Password is too long.");
			result = false;
		} else if (confirm != password) {
			parent.find("#password_confirm_error").html(
					"Password does not match.");
			result = false;
		} else {
			parent.find("#password_confirm_error").html("");
		}
	}
	if (firstname == null || firstname == "") {
		parent.find("#firstname_error").html("Please enter first name.");
		result = false;
	} else if (firstname.length > 30) {
		parent.find("#firstname_error").html("First name is too long.");
		result = false;
	} else {
		parent.find("#firstname_error").html("");
	}
	if (lastname == null || lastname == "") {
		parent.find("#lastname_error").html("Please enter last name.");
		result = false;
	} else if (lastname.length > 30) {
		parent.find("#lastname_error").html("Last name is too long.");
		result = false;
	} else {
		parent.find("#lastname_error").html("");
	}

	if (dob == null || dob == "") {
		parent.find("#dob_error").html("Please enter date of birth.");
		result = false;
	} else {
		var date = parseDate(dob, "dd/mm/yyyy");
		if (date == null) {
			parent.find("#dob_error").html(
					"Date of birth is not in correct format.");
			result = false;
		} else {
			var today = new Date();
			today.setDate(today.getDate() - 1);
			if (date >= today) {
				parent.find("#dob_error").html(
						"Date of birth cannot be later than today.");
				result = false;
			} else {
				parent.find("#dob_error").html("");
			}
		}
	}

	if (email == null || email == "") {
		parent.find("#email_error").html("Please enter email.");
		result = false;
	} else if (email.length > 50) {
		parent.find("#email_error").html("Email is too long.");
		result = false;
	} else if (!email
			.match("^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$")) {
		parent.find("#email_error").html("Email's format is not valid");
		result = false;
	} else {
		parent.find("#email_error").html("");
	}
	return result;
}

function checkManagementInput(parent) {
	var username = parent.find("#userName").val();
	var firstname = parent.find("#firstName").val();
	var lastname = parent.find("#lastName").val();
	var dob = parent.find("#dateOfBirth").val();
	var email = parent.find("#email").val();
	var result = true;
	if (username == null || username == "") {
		parent.find("#username_error").html("Please enter username.");
		result = false;
	} else if (username.length > 20) {
		parent.find("#username_error").html("Username is too long.");
		result = false;
	} else {
		parent.find("#username_error").html("");
	}
	if (firstname == null || firstname == "") {
		parent.find("#firstname_error").html("Please enter first name.");
		result = false;
	} else if (firstname.length > 30) {
		parent.find("#firstname_error").html("First name is too long.");
		result = false;
	} else {
		parent.find("#firstname_error").html("");
	}
	if (lastname == null || lastname == "") {
		parent.find("#lastname_error").html("Please enter last name.");
		result = false;
	} else if (lastname.length > 30) {
		parent.find("#lastname_error").html("Last name is too long.");
		result = false;
	} else {
		parent.find("#lastname_error").html("");
	}

	if (dob == null || dob == "") {
		parent.find("#dob_error").html("Please enter date of birth.");
		result = false;
	} else {
		var date = parseDate(dob, "dd/mm/yyyy");
		if (date == null) {
			parent.find("#dob_error").html(
					"Date of birth is not in correct format.");
			result = false;
		} else {
			var today = new Date();
			today.setDate(today.getDate() - 1);
			if (date >= today) {
				parent.find("#dob_error").html(
						"Date of birth cannot be later than today.");
				result = false;
			} else {
				parent.find("#dob_error").html("");
			}
		}
	}

	if (email == null || email == "") {
		parent.find("#email_error").html("Please enter email.");
		result = false;
	} else if (email.length > 50) {
		parent.find("#email_error").html("Email is too long.");
		result = false;
	} else if (!email
			.match("^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$")) {
		parent.find("#email_error").html("Email's format is not valid");
		result = false;
	} else {
		parent.find("#email_error").html("");
	}
	return result;
}

function checkUpdateUser(parent) {
	var firstname = parent.find("#firstName").val();
	var lastname = parent.find("#lastName").val();
	var dob = parent.find("#dateOfBirth").val();
	var email = parent.find("#email").val();
	var result = true;
	if (firstname == null || firstname == "") {
		parent.find("#firstname_error").html("Please enter first name.");
		result = false;
	} else if (firstname.length > 30) {
		parent.find("#firstname_error").html("First name is too long.");
		result = false;
	} else {
		parent.find("#firstname_error").html("");
	}
	if (lastname == null || lastname == "") {
		parent.find("#lastname_error").html("Please enter last name.");
		result = false;
	} else if (lastname.length > 30) {
		parent.find("#lastname_error").html("Last name is too long.");
		result = false;
	} else {
		parent.find("#lastname_error").html("");
	}

	if (dob == null || dob == "") {
		parent.find("#dob_error").html("Please enter date of birth.");
		result = false;
	} else {
		var date = parseDate(dob, "dd/mm/yyyy");
		if (date == null) {
			parent.find("#dob_error").html(
					"Date of birth is not in correct format.");
			result = false;
		} else {
			var today = new Date();
			today.setDate(today.getDate() - 1);
			if (date >= today) {
				parent.find("#dob_error").html(
						"Date of birth cannot be later than today.");
				result = false;
			} else {
				parent.find("#dob_error").html("");
			}
		}
	}

	if (email == null || email == "") {
		parent.find("#email_error").html("Please enter email.");
		result = false;
	} else if (email.length > 50) {
		parent.find("#email_error").html("Email is too long.");
		result = false;
	} else if (!email
			.match("^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$")) {
		parent.find("#email_error").html("Email's format is not valid");
		result = false;
	} else {
		parent.find("#email_error").html("");
	}
	return result;
}

function checkChangePassword() {
	var oldPassword = $("#old_password").val();
	var newPassword = $("#new_password").val();
	var confirm = $("#confirm").val();
	var result = true;
	if (oldPassword == null || oldPassword == "") {
		$("#old_password_error").html("Please enter password.");
		result = false;
	} else if (oldPassword.length > 20) {
		$("#old_password_error").html("Password is too long.");
		result = false;
	} else {
		$("#old_password_error").html("");
	}
	if (newPassword == null || newPassword == "") {
		$("#new_password_error").html("Please enter password.");
		result = false;
	} else if (newPassword.length > 20) {
		$("#new_password_error").html("Password is too long.");
		result = false;
	} else {
		$("#new_password_error").html("");
		if (confirm == null || confirm == "") {
			$("#password_confirm_error").html("Please re-type password.");
			result = false;
		} else if (confirm.length > 20) {
			$("#password_confirm_error").html("Password is too long.");
			result = false;
		} else if (confirm != newPassword) {
			$("#password_confirm_error").html("Password does not match.");
			result = false;
		} else {
			$("#password_confirm_error").html("");
		}
	}
	return result;
}