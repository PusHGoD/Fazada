$(function() {
	$.fn.editable.defaults.mode = 'inline';
	$('#firstName').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update',
		validate : function(value) {
			if (value === null || value === '') {
				return 'Please input first name';
			} else if (value.length > 30) {
				return 'First name cannot be more than 30 characters';
			}
		}
	});
	$('#lastName').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update',
		validate : function(value) {
			if (value === null || value === '') {
				return 'Please input last name';
			} else if (value.length > 30) {
				return 'Last name cannot be more than 30 characters';
			}
		}
	});
	$('#dateOfBirth').editable({
		type : 'combodate',
		combodate : {
			maxYear : new Date().getFullYear()
		},
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update',
		validate : function(value) {
			if (value === null || value === '') {
				return 'Please input date';
			}
		}
	});
	$('#email').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update',
		validate : function(value) {
			if (value === null || value === '') {
				return 'Please input email';
			} else if (value.length > 30) {
				return 'Email cannot be more than 30 characters';
			}
		}
	});
});

function convertXEditableToJSON(params) {
	var json = {};
	json["userName"] = params.pk;
	if (params.value != null) {
		v = params.value;
	} else {
		v = '';
	}
	if (json[params.name] != null) {
		if (!json[params.name].push) {
			json[params.name] = [ json[params.name] ];
		}
		json[params.name].push(v);
	} else {
		json[params.name] = v;
	}
	return json;
}