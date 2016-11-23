$(document)
		.ready(
				function() {
					if ($('body').is('#managerPage')) {
						$(load);
					}

					$("#login-btn")
							.click(
									function() {
										if (checkLoginInput()) {
											var data = $("#loginForm")
													.serializeArray();
											var json = convertArrayToJSON(data);
											$
													.ajax({
														url : "http://localhost:8080/fazadaws/account/login",
														type : "POST",
														contentType : "application/json; charset=utf-8",
														dataType : "text",
														data : JSON
																.stringify(json),
														success : function(
																response) {
															showAJAXSuccessMessage(response);
														},
														error : function(
																response) {
															showAJAXErrorMessage(response);
														}
													});
										}
									});

					$("#forget-btn")
							.click(
									function() {
										var data = $("#forgetForm").find(
												"#email").val();
										$
												.ajax({
													url : "http://localhost:8080/fazadaws/account/reset",
													type : "POST",
													contentType : "text/plain; charset=utf-8",
													dataType : "text",
													data : data,
													success : function(response) {
														alert(response);
														showAJAXSuccessMessage(response);
													},
													error : function(response) {
														showAJAXErrorMessage(response);
													}
												});
									})

					$("#user-update-btn").click(function() {
						if (checkUpdateInput()) {
							$("#editForm").submit();
						}
					});

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

					$("#signup-btn")
							.click(
									function() {
										if (checkSignupInput($("#signupForm"))) {
											var data = $("#signupForm")
													.serializeArray();
											var json = convertArrayToJSON(data);
											$
													.ajax({
														url : 'http://localhost:8080/fazadaws/account/add',
														type : "POST",
														contentType : "application/json; charset=utf-8",
														dataType : "text",
														data : JSON
																.stringify(json),
														success : function(
																response) {
															alert(response);
															showAJAXSuccessMessage(response);
														},
														error : function(
																response) {
															alert(JSON
																	.stringify(response));
															showAJAXErrorMessage(response);
														}
													});
											$("#myModal").modal("hide");
										}
									});
					// $("#manager-add-btn")
					// .click(
					// function() {
					// if (checkManagementInput($("#addModal"))) {
					// var data = $("#addModal").find(
					// "#addForm")
					// .serializeArray();
					// var json = {};
					// $
					// .each(
					// data,
					// function(v) {
					// if (this.value != null) {
					// v = this.value;
					// } else {
					// v = '';
					// }
					// if (this.name == "dateOfBirth") {
					// v = parseDate(
					// v,
					// "dd/mm/yyyy");
					// }
					// if (json[this.name] != null) {
					// if (!json[this.name].push) {
					// json[this.name] = [ json[this.name] ];
					// }
					// json[this.name]
					// .push(v);
					// } else {
					// json[this.name] = v;
					// }
					// });
					// $
					// .ajax({
					// url : 'add.htm',
					// type : "POST",
					// contentType : "application/json; charset=utf-8",
					// dataType : "text",
					// data : JSON
					// .stringify(json),
					// success : function(
					// response) {
					// load();
					// showAJAXSuccessMessage(response);
					// },
					// error : function(
					// response) {
					// showAJAXErrorMessage(response);
					// }
					// });
					// $("#addModal").modal("hide");
					// }
					// });
					//
					// $("#manager-edit-btn")
					// .click(
					// function() {
					// if (checkManagementInput($("#editModal"))) {
					// var data = $("#editModal").find(
					// "#editForm")
					// .serializeArray();
					// var json = {};
					// $
					// .each(
					// data,
					// function(v) {
					// if (this.value != null) {
					// v = this.value;
					// } else {
					// v = '';
					// }
					// if (this.name == "dateOfBirth") {
					// v = parseDate(
					// v,
					// "dd/mm/yyyy");
					// }
					// if (json[this.name] != null) {
					// if (!json[this.name].push) {
					// json[this.name] = [ json[this.name] ];
					// }
					// json[this.name]
					// .push(v);
					// } else {
					// json[this.name] = v;
					// }
					// });
					// $
					// .ajax({
					// url : 'edit.htm',
					// type : "POST",
					// contentType : "application/json; charset=utf-8",
					// dataType : "text",
					// data : JSON
					// .stringify(json),
					// success : function(
					// response) {
					// load();
					// showAJAXSuccessMessage(response);
					// },
					// error : function(
					// response) {
					// showAJAXErrorMessage(response);
					// }
					//
					// });
					// $("#editModal").modal("hide");
					// }
					// });

					window.actionEvents = {
						// 'click .reset' : function(e, value, row, index) {
						// $
						// .ajax({
						// url : 'reset.htm',
						// type : "POST",
						// contentType : "application/json; charset=utf-8",
						// dataType : "text",
						// data : JSON.stringify(row),
						// success : function(response) {
						// showAJAXSuccessMessage(response);
						// },
						// error : function(response) {
						// showAJAXErrorMessage(response);
						// }
						// });
						// },
						'click .reset' : function(e, value, row, index) {
							if (row != null) {
								$("#editModal").find("#id").val(row.id);
								$("#editModal").find("#userName").val(
										row.userName);
								$("#editModal").find("#firstName").val(
										row.firstName);
								$("#editModal").find("#lastName").val(
										row.lastName);
								$("#editModal").find("#dateOfBirth").val(
										formatDate(row.dateOfBirth));
								$("#editModal").find("#email").val(row.email);
								if (row.active) {
									$("#editModal").find("input[value='true']")
											.prop('checked', true);
								} else {
									$("#editModal")
											.find("input[value='false']").prop(
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
									$("#editModal").find("#userName").val(
											acc.userName);
									$("#editModal").find("#firstName").val(
											acc.firstName);
									$("#editModal").find("#lastName").val(
											acc.lastName);
									$("#editModal").find("#dateOfBirth").val(
											formatDate(acc.dateOfBirth));
									$("#editModal").find("#email").val(
											acc.email);
									if (acc.active) {
										$("#editModal").find(
												"input[value='true']").prop(
												'checked', true);
									} else {
										$("#editModal").find(
												"input[value='false']").prop(
												'checked', true);
									}
								} else {
									$("#editButton").prop('disabled', true);
								}
							});

				});

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

/* Load table */
function load() {
	$.ajax({
		url : '/fazadaws/account/list',
		type : "GET",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(list) {
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
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

/* Get selected row */
function getSelectedRow() {
	var index = $("#table").find('tr.success').data('index');
	return $("table").bootstrapTable('getData')[index];
}

function showAJAXSuccessMessage(response) {
	$("#ajaxMessage").html(
			"<div class='alert alert-success'>" + response + "</div>");
	$("#ajaxMessage").fadeIn();
	$("#ajaxMessage").fadeOut(2500);
}

function showAJAXErrorMessage(response) {
	$("#ajaxMessage").html(
			"<div class='alert alert-danger'>" + response + "</div>");
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

function actionFormatter(value, row, index) {
	return [
			'<a class="edit ml10" href="javascript:void(0)" title="Edit" data-toggle="modal" data-target="#editModal">',
			'<i class="glyphicon glyphicon-edit"></i>', '</a> ', ].join('');
//	'<a class="reset ml10" href="javascript:void(0)" title="Reset">',
//	'<i class="glyphicon glyphicon-refresh"></i>', '</a> ', 
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